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

    // ── POST: CRIAÇÃO COM DTO E USUÁRIO (CORRIGIDO) ──
    public ProfissionalDetailDTO criar(ProfissionalCadastroDTO cadastroDTO) {
        if (cadastroDTO == null || cadastroDTO.nome() == null || cadastroDTO.nome().isBlank()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }

        // Validação preventiva de CPF duplicado
        String cpfLimpo = cadastroDTO.cpf().replaceAll("[^\\d]", "");
        boolean cpfExiste = profissionalRepository.findAll().stream()
                .anyMatch(p -> p.getCpf().equals(cpfLimpo));
        if (cpfExiste) {
            throw new IllegalArgumentException("Profissional com este CPF já existe");
        }

        // Converte DTO para Entidade Especialista e salva no Oracle
        Especialista novaEspecialista = dtoMapper.toEntity(cadastroDTO);
        Especialista especialistaSalva = profissionalRepository.save(novaEspecialista);

        // Cria a conta de acesso ao sistema automaticamente vinculada
        Usuario usuario = new Usuario();
        usuario.setId(especialistaSalva.getIdCadastro());
        usuario.setPessoa(especialistaSalva);
        usuario.setSenha(cadastroDTO.senhaProvisoria()); // Em produção, aplicar criptografia aqui
        usuarioRepository.save(usuario);

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