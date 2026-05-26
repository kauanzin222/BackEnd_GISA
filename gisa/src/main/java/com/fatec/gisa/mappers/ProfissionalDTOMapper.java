package com.fatec.gisa.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.models.Especialidade;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.repositories.EspecialidadeRepository;
import com.fatec.gisa.dtos.EnderecoDTO;
import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.enums.StatusCadastro;

/**
 * MAPPER INVERSO: DTO → Entidade (para POST/PUT)
 * 
 * Responsabilidade: Converter ProfissionalCadastroDTO (do formulário)
 * para Especialista/EspecialistaPJ (Entidade JPA)
 * 
 * PADRÃO:
 * 1. POST (Criação):    Controller recebe DTO → toEntity() → save → toDetailDTO()
 * 2. PUT (Atualização): Controller recebe DTO → merge com entidade existente → save → toDetailDTO()
 * 
 * CUIDADOS:
 * - Nunca copie IDs (gerados pelo BD)
 * - Valide dados antes de converter
 * - Trate relacionamentos (especialidades, endereços)
 * - Persista subentidades (Usuario, Endereco) corretamente
 */
@Component
public class ProfissionalDTOMapper {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    /**
     * Converte ProfissionalCadastroDTO → Especialista (para POST - criação).
     * 
     * Operação: DTO → Nova Entidade Especialista
     * 
     * Passos:
     * 1. Cria nova instância de Especialista
     * 2. Preenche campos de Pessoa (nome, cpf, dataNascimento, etc)
     * 3. Preenche campos de Especialista (registroConselho, especialidades)
     * 4. Se tem CNPJ, cria EspecialistaPJ em vez disso
     * 5. Cria Usuario associado (será feito no Service após persistência)
     * 
     * @param cadastroDTO Dados do formulário
     * @return Especialista pronta para persistir
     */
    public Especialista toEntity(ProfissionalCadastroDTO cadastroDTO) {
        if (cadastroDTO == null) {
            return null;
        }

        // Determina se é PJ
        boolean isPJ = cadastroDTO.cnpj() != null && !cadastroDTO.cnpj().isBlank();

        Especialista especialista;

        if (isPJ) {
            // Criar EspecialistaPJ
            EspecialistaPJ pj = new EspecialistaPJ();
            pj.setCNPJ(limparDocumento(cadastroDTO.cnpj()));
            pj.setRazaoSocial(cadastroDTO.razaoSocial());
            pj.setNomeFantasia(cadastroDTO.nomeFantasia());
            pj.setInscricaoEstadual(cadastroDTO.inscricaoEstadual());
            especialista = pj;
        } else {
            // Criar Especialista comum
            especialista = new Especialista();
        }

        // ── CAMPOS DE PESSOA ──
        especialista.setNome(cadastroDTO.nome());
        especialista.setCpf(limparDocumento(cadastroDTO.cpf()));
        especialista.setDataNascimento(cadastroDTO.dataNascimento());
        // Nota: RG não está na entidade Pessoa, você pode adicionar
        
        // Status padrão para novo cadastro
        especialista.setStatusCadastro(StatusCadastro.ATIVO);

        // ── CAMPOS DE ESPECIALISTA ──
        especialista.setRegistroConselho(cadastroDTO.registroProfissional());

        // Carregar especialidades do banco por IDs
        if (cadastroDTO.idEspecialidades() != null && !cadastroDTO.idEspecialidades().isEmpty()) {
            List<Especialidade> especialidades = cadastroDTO.idEspecialidades()
                .stream()
                .map(id -> especialidadeRepository.findById(id).orElse(null))
                .filter(e -> e != null)
                .collect(Collectors.toList());
            especialista.setEspecialidades(especialidades);
        }

        // ── ENDEREÇO ──
        if (cadastroDTO.endereco() != null) {
            Endereco endereco = toEnderecoEntity(cadastroDTO.endereco());
            if (endereco != null) {
                // Note: Endereço é ManyToMany, geralmente gerenciado via Service
                // especialista.getEnderecos().add(endereco);
            }
        }

        // ── CAMPOS DE CONTATO ──
        // Email é armazenado em Usuario (será criado no Service)
        // Telefone pode ser armazenado em Endereco ou em campo específico
        // Você pode adicionar campos telefone/email em Pessoa se necessário

        return especialista;
    }

    /**
     * Converte ProfissionalCadastroDTO → Especialista (para PUT - atualização).
     * 
     * Operação: DTO + Entidade existente → Entidade atualizada
     * 
     * DIFERENÇA DO toEntity():
     * - Recebe entidade EXISTENTE
     * - Apenas ATUALIZA campos (não cria nova)
     * - NÃO toca em ID, Cargo (gerenciados separadamente)
     * 
     * @param cadastroDTO Dados atualizados do formulário
     * @param especialistaExistente Entidade atual no BD
     * @return Especialista atualizada
     */
    public Especialista updateEntity(
            ProfissionalCadastroDTO cadastroDTO,
            Especialista especialistaExistente) {
        
        if (cadastroDTO == null || especialistaExistente == null) {
            return especialistaExistente;
        }

        // ── ATUALIZAR CAMPOS DE PESSOA ──
        especialistaExistente.setNome(cadastroDTO.nome());
        especialistaExistente.setCpf(limparDocumento(cadastroDTO.cpf()));
        especialistaExistente.setDataNascimento(cadastroDTO.dataNascimento());

        // ── ATUALIZAR CAMPOS DE ESPECIALISTA ──
        especialistaExistente.setRegistroConselho(cadastroDTO.registroProfissional());

        // Atualizar especialidades
        if (cadastroDTO.idEspecialidades() != null) {
            List<Especialidade> novasEspecialidades = cadastroDTO.idEspecialidades()
                .stream()
                .map(id -> especialidadeRepository.findById(id).orElse(null))
                .filter(e -> e != null)
                .collect(Collectors.toList());
            especialistaExistente.setEspecialidades(novasEspecialidades);
        }

        // ── ATUALIZAR DADOS PJ (se for EspecialistaPJ) ──
        if (especialistaExistente instanceof EspecialistaPJ) {
            EspecialistaPJ pj = (EspecialistaPJ) especialistaExistente;
            pj.setCNPJ(limparDocumento(cadastroDTO.cnpj()));
            pj.setRazaoSocial(cadastroDTO.razaoSocial());
            pj.setNomeFantasia(cadastroDTO.nomeFantasia());
            pj.setInscricaoEstadual(cadastroDTO.inscricaoEstadual());
        }

        return especialistaExistente;
    }

    /**
     * Converte EnderecoDTO → Endereco (Entidade JPA).
     * 
     * @param enderecoDTO DTO do formulário
     * @return Endereco entidade
     */
    private Endereco toEnderecoEntity(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());
        endereco.setCep(enderecoDTO.cep());

        return endereco;
    }

    /**
     * Remove caracteres especiais de documentos (CPF, CNPJ, RG).
     * 
     * "123.456.789-00" → "12345678900"
     * "12.345.678/0001-90" → "12345678000190"
     */
    private String limparDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            return null;
        }
        return documento.replaceAll("[^\\d]", "");
    }
}
