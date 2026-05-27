package com.fatec.gisa.services;

import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.dtos.ProfissionalDetailDTO;
import com.fatec.gisa.dtos.ProfissionalSummaryDTO;
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
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProfissionalMapper profissionalMapper;

    @Autowired
    private ProfissionalDTOMapper dtoMapper;

    // ── GET: LISTAGEM PAGINADA ──
    public Page<ProfissionalSummaryDTO> listarTodos(Pageable pageable) {
        return profissionalRepository.findAll(pageable)
                .map(profissionalMapper::toSummaryDTO); // Totalmente seguro e limpo!
    }

    // ── GET: BUSCA POR ID ──
    public ProfissionalDetailDTO buscarPorId(Integer id) {
        return profissionalRepository.findById(id)
                .map(profissionalMapper::toDetailDTO) // Totalmente seguro e limpo!
                .orElse(null);
    }

    // ── GET: FILTRO POR NOME ──
    public List<ProfissionalSummaryDTO> buscarPorNome(String nome) {
        return profissionalRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(profissionalMapper::toSummaryDTO) // Totalmente seguro e limpo!
                .collect(Collectors.toList());
    }

    public Profissional buscarPorCpf(String cpf) {
        return profissionalRepository.findByCpf(cpf);
    }

    @Transactional // CORREÇÃO CRÍTICA: Mantém a sessão do Hibernate ativa para o @MapsId funcionar
    public ProfissionalDetailDTO criar(ProfissionalCadastroDTO cadastroDTO) {
        if (cadastroDTO == null || cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }

        // Validação de CPF duplicado
        String cpfLimpo = cadastroDTO.cpf().replaceAll("[^\\d]", "");
        boolean cpfExiste = profissionalRepository.findAll().stream()
                .anyMatch(p -> p.getCpf().equals(cpfLimpo));
        if (cpfExiste) {
            throw new IllegalArgumentException("Profissional com este CPF já existe");
        }

        // 1. Converte o DTO para a Entidade Especialista
        Especialista novaEspecialista = dtoMapper.toEntity(cadastroDTO);

        // 2. Salva o especialista e garante que o ID do join inheritance esteja
        // disponível
        Especialista especialistaSalva = profissionalRepository.saveAndFlush(novaEspecialista);
        if (especialistaSalva.getIdCadastro() == null) {
            throw new IllegalStateException("Não foi possível gerar o ID do profissional antes de criar o usuário");
        }

        // 3. Cria a conta de Usuário injetando a entidade recém-salva e gerenciada
        Usuario usuario = new Usuario();
        usuario.setPessoa(especialistaSalva); // O @MapsId cuidará de vincular os IDs automaticamente no insert
        usuario.setSenha(cadastroDTO.senhaProvisoria());

        // 4. Salva o Usuário sob o mesmo contexto transacional
        usuarioRepository.save(usuario);

        // 5. Retorna o DTO de detalhe
        return profissionalMapper.toDetailDTO(especialistaSalva);
    }

    // ── PUT: ATUALIZAÇÃO COM DTO (CORRIGIDO) ──
    public ProfissionalDetailDTO atualizar(Integer id, ProfissionalCadastroDTO cadastroDTO) {
        Especialista especialistaExistente = (Especialista) profissionalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional com ID " + id + " não encontrado"));

        if (cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }

        // Atualiza os dados mesclando a entidade com o DTO
        Especialista especialistaAtualizada = dtoMapper.updateEntity(cadastroDTO, especialistaExistente);
        Especialista salva = profissionalRepository.save(especialistaAtualizada);

        // Atualiza a senha do usuário se uma nova tiver sido digitada no formulário
        if (cadastroDTO.senhaProvisoria() != null && !cadastroDTO.senhaProvisoria().isBlank()) {
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            if (usuario != null) {
                usuario.setSenha(cadastroDTO.senhaProvisoria());
                usuarioRepository.save(usuario);
            }
        }

        return profissionalMapper.toDetailDTO(salva);
    }

    // ── DELETE: DELEÇÃO LIMPA (CORRIGIDO) ──
    public void deletar(Integer id) {
        // Remove primeiro o Usuário para manter a integridade referencial do Oracle
        usuarioRepository.deleteById(id);
        profissionalRepository.deleteById(id);
    }
}