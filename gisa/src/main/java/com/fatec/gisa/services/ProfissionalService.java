package com.fatec.gisa.services;

import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.dtos.ProfissionalDetailDTO;
import com.fatec.gisa.dtos.ProfissionalSummaryDTO;
import com.fatec.gisa.enums.StatusCadastro;
import com.fatec.gisa.mappers.ProfissionalDTOMapper;
import com.fatec.gisa.mappers.ProfissionalMapper;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.models.Usuario;
import com.fatec.gisa.repositories.ProfissionalRepository;
import com.fatec.gisa.repositories.UsuarioRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional // Garante a segurança das operações combinadas de banco
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profesionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @Transactional // Mantém a sessão do Hibernate ativa para o @MapsId funcionar
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

    // ── PUT: ATUALIZAÇÃO POLIMÓRFICA (CORRIGIDO) ──
    public ProfissionalDetailDTO atualizar(Integer id, ProfissionalCadastroDTO cadastroDTO) {
        // 1. Buscamos a entidade usando a classe base genérica para evitar ClassCastException
        Profissional profissionalExistente = profesionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional com ID " + id + " não encontrado"));

        if (cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        Profissional salva;

        // 2. Ramificação lógica baseada na instância real do objeto vindo do banco
        if (profissionalExistente instanceof Especialista) {
            Especialista especialistaExistente = (Especialista) profissionalExistente;
            
            // Se for Especialista, usamos o mapper para atualizar todos os dados (básicos + clínicos)
            Especialista especialistaAtualizada = dtoMapper.updateEntity(cadastroDTO, especialistaExistente);
            salva = profesionalRepository.save(especialistaAtualizada);
        } else {
            // Se for um Profissional comum (não-especialista), atualizamos apenas os campos gerais permitidos
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
            
            // Salva as alterações da classe base mantendo-o como Profissional genérico
            salva = profesionalRepository.save(profissionalExistente);
        }

        // 3. Atualiza a senha do usuário se uma nova tiver sido enviada (fora do fluxo restrito)
        if (cadastroDTO.senhaProvisoria() != null && !cadastroDTO.senhaProvisoria().isBlank()) {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario != null) {
                usuario.setSenha(cadastroDTO.senhaProvisoria());
                usuarioRepository.save(usuario);
            }
        }

        return profissionalMapper.toDetailDTO(salva);
    }

    // ── DELETE: INATIVAÇÃO LÓGICA / SOFT DELETE ──
    public void deletar(Integer id) {
        Profissional profissional = profesionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional com ID " + id + " não encontrado"));
        
        // Altera a propriedade usando o enum oficial do seu sistema
        profissional.setStatusCadastro(StatusCadastro.INATIVO);
        profesionalRepository.save(profissional);
    }
}