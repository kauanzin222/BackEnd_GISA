package com.fatec.gisa.services;

import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.dtos.ProfissionalDetailDTO;
import com.fatec.gisa.dtos.ProfissionalSummaryDTO;
import com.fatec.gisa.enums.StatusCadastro;
import com.fatec.gisa.mappers.ProfissionalDTOMapper;
import com.fatec.gisa.mappers.ProfissionalMapper;
import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.models.Usuario;
import com.fatec.gisa.repositories.ProfissionalRepository;
import com.fatec.gisa.repositories.UsuarioRepository;
import com.fatec.gisa.repositories.EspecialidadeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList; // Importado para garantir a inicialização segura das coleções
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Garante a segurança e atomicidade das operações combinadas de banco
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profesionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspecialidadeRepository BlackespecialidadeRepository; // Injetado para gerenciar o relacionamento ManyToMany

    @Autowired
    private ProfissionalMapper profissionalMapper;

    @Autowired
    private ProfissionalDTOMapper dtoMapper;

    // ── GET: LISTAGEM PAGINADA (APENAS PROFISSIONAIS ATIVOS) ──
    public Page<ProfissionalSummaryDTO> listarTodos(Pageable pageable) {
        return profesionalRepository.findByStatusCadastro(StatusCadastro.ATIVO, pageable)
                .map(profissionalMapper::toSummaryDTO);
    }

    // ── GET: BUSCA POR ID ──
    public ProfissionalDetailDTO buscarPorId(Integer id) {
        return profesionalRepository.findById(id)
                .map(profissionalMapper::toDetailDTO)
                .orElse(null);
    }

    // ── GET: FILTRO POR NOME ──
    public List<ProfissionalSummaryDTO> buscarPorNome(String nome) {
        return profesionalRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(profissionalMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    public Profissional buscarPorCpf(String cpf) {
        return profesionalRepository.findByCpf(cpf);
    }

    @Transactional
    public ProfissionalDetailDTO criar(ProfissionalCadastroDTO cadastroDTO) {
        if (cadastroDTO == null || cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }

        String cpfLimpo = cadastroDTO.cpf().replaceAll("[^\\d]", "");
        boolean cpfExiste = profesionalRepository.findAll().stream()
                .anyMatch(p -> p.getCpf().equals(cpfLimpo));
        if (cpfExiste) {
            throw new IllegalArgumentException("Profissional com este CPF já existe");
        }

        Especialista novaEspecialista = dtoMapper.toEntity(cadastroDTO);
        novaEspecialista.setStatusCadastro(StatusCadastro.ATIVO);

        // CORREÇÃO: Inicialização de salvaguarda caso a lista nasça nula
        if (novaEspecialista.getEspecialidades() == null) {
            novaEspecialista.setEspecialidades(new ArrayList<>());
        }

        // Associa as especialidades enviadas no cadastro inicial
        if (cadastroDTO.idEspecialidades() != null && !cadastroDTO.idEspecialidades().isEmpty()) {
            cadastroDTO.idEspecialidades().forEach(idSpec -> {
                BlackespecialidadeRepository.findById(idSpec).ifPresent(spec -> {
                    novaEspecialista.getEspecialidades().add(spec);
                });
            });
        }

        Especialista especialistaSalva = profesionalRepository.saveAndFlush(novaEspecialista);
        if (especialistaSalva.getIdCadastro() == null) {
            throw new IllegalStateException("Não foi possível gerar o ID do profissional antes de criar o usuário");
        }

        Usuario usuario = new Usuario();
        usuario.setPessoa(especialistaSalva);
        usuario.setSenha(cadastroDTO.senhaProvisoria());

        usuarioRepository.save(usuario);

        return profesionalRepository.findById(especialistaSalva.getIdCadastro())
                .map(profissionalMapper::toDetailDTO)
                .orElse(null);
    }

    // ── PUT: ATUALIZAÇÃO POLIMÓRFICA CORRIGIDA (ESPECIALIDADES SINCRONIZADAS) ──
    public ProfissionalDetailDTO atualizar(Integer id, ProfissionalCadastroDTO cadastroDTO) {
        Profissional profissionalExistente = profesionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional com ID " + id + " não encontrado"));

        if (cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        Profissional salva;

        if (profissionalExistente instanceof Especialista) {
            Especialista especialistaExistente = (Especialista) profissionalExistente;

            // 1. Atualiza propriedades textuais básicas e clínicas via Mapper
            Especialista especialistaAtualizada = dtoMapper.updateEntity(cadastroDTO, especialistaExistente);
            
            // 2. CORREÇÃO CRÍTICA: Tratamento robusto contra NullPointerException e reinicialização da lista
            if (especialistaAtualizada.getEspecialidades() == null) {
                especialistaAtualizada.setEspecialidades(new ArrayList<>());
            } else {
                especialistaAtualizada.getEspecialidades().clear();
            }
            
            // Mapeia os novos IDs enviados pelo Front-end e reinsere as especialidades ativas
            if (cadastroDTO.idEspecialidades() != null) {
                for (Integer idSpec : cadastroDTO.idEspecialidades()) {
                    BlackespecialidadeRepository.findById(idSpec).ifPresent(spec -> {
                        especialistaAtualizada.getEspecialidades().add(spec);
                    });
                }
            }

            // CORREÇÃO CRÍTICA: Mudança de 'save' para 'saveAndFlush' para obrigar o Hibernate a sincronizar o relacionamento ManyToMany agora
            salva = profesionalRepository.saveAndFlush(especialistaAtualizada);
        } else {
            // Se for um Profissional comum (não-especialista)
            profissionalExistente.setNome(cadastroDTO.nome());

            if (cadastroDTO.cpf() != null) {
                profissionalExistente.setCpf(cadastroDTO.cpf().replaceAll("[^\\d]", ""));
            }
            if (cadastroDTO.dataNascimento() != null) {
                profissionalExistente.setDataNascimento(cadastroDTO.dataNascimento());
            }
            if (cadastroDTO.email() != null) {
                profissionalExistente.setEmail(cadastroDTO.email());
            }
            if (cadastroDTO.celular() != null) {
                profissionalExistente.setCelular(cadastroDTO.celular().replaceAll("[^\\d]", ""));
            }

            // Mapeamento Manual do Endereço do Funcionário Comum
            if (cadastroDTO.endereco() != null &&
                    profissionalExistente.getEnderecos() != null &&
                    !profissionalExistente.getEnderecos().isEmpty()) {

                Endereco enderecoExistente = profissionalExistente.getEnderecos().iterator().next();
                var enderecoDto = cadastroDTO.endereco();

                if (enderecoDto.rua() != null) enderecoExistente.setRua(enderecoDto.rua());
                if (enderecoDto.numero() != null) enderecoExistente.setNumero(enderecoDto.numero());
                if (enderecoDto.complemento() != null) enderecoExistente.setComplemento(enderecoDto.complemento());
                if (enderecoDto.bairro() != null) enderecoExistente.setBairro(enderecoDto.bairro());
                if (enderecoDto.cidade() != null) enderecoExistente.setCidade(enderecoDto.cidade());
                if (enderecoDto.estado() != null) enderecoExistente.setEstado(enderecoDto.estado());
                if (enderecoDto.cep() != null) {
                    enderecoExistente.setCep(enderecoDto.cep().replaceAll("[^\\d]", ""));
                }
            }

            salva = profesionalRepository.saveAndFlush(profissionalExistente);
        }

        // Atualiza a senha do usuário se aplicável
        if (cadastroDTO.senhaProvisoria() != null && !cadastroDTO.senhaProvisoria().isBlank()) {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario != null) {
                usuario.setSenha(cadastroDTO.senhaProvisoria());
                usuarioRepository.save(usuario);
            }
        }

        return profissionalMapper.toDetailDTO(salva);
    }

    // ── DELETE: INATIVAÇÃO LÓGICA ──
    public void deletar(Integer id) {
        Profissional profissional = profesionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional com ID " + id + " não encontrado"));

        profissional.setStatusCadastro(StatusCadastro.INATIVO);
        profesionalRepository.save(profissional);
    }
}