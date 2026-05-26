package com.fatec.gisa.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fatec.gisa.models.CID;
import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.models.Especialidade;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.models.Paciente;
import com.fatec.gisa.models.Pessoa;
import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.models.Prontuario;
import com.fatec.gisa.dtos.CIDSummaryDTO;
import com.fatec.gisa.dtos.EspecialidadeSummaryDTO;
import com.fatec.gisa.dtos.EnderecoDTO;
import com.fatec.gisa.dtos.PacienteDetailDTO;
import com.fatec.gisa.dtos.PacienteSummaryDTO;
import com.fatec.gisa.dtos.PessoaDetailDTO;
import com.fatec.gisa.dtos.PessoaSummaryDTO;

/**
 * Mapper para conversão de Entidades Pessoa/Paciente ↔ DTOs.
 * 
 * Responsabilidades:
 * - Converter Pessoa para PessoaSummaryDTO (listagens planas)
 * - Converter Pessoa/Subclasses para PessoaDetailDTO (detalhes polimórficos)
 * - Converter Paciente para PacienteSummaryDTO (listagens clínicas)
 * - Converter Paciente para PacienteDetailDTO (detalhes clínicos)
 * 
 * NUNCA expõe a entidade JPA diretamente no Controller.
 */
@Component
public class PessoaMapper {

    /**
     * Converte Pessoa para PessoaSummaryDTO (para listagens gerais).
     * 
     * Operação: Pessoa → PessoaSummaryDTO
     * 
     * Extrai APENAS campos nativos de Pessoa, sem joins de subclasses.
     * Apropriado para listagens gerais de pessoas (staff, etc).
     */
    public PessoaSummaryDTO toSummaryDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        return new PessoaSummaryDTO(
                pessoa.getIdCadastro(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getCelular(),
                pessoa.getEstadoCivil() != null ? pessoa.getEstadoCivil().toString() : null,
                pessoa.getStatusCadastro() != null ? pessoa.getStatusCadastro().toString() : null);
    }

    /**
     * Converte Pessoa (polimorficamente) para PessoaDetailDTO (para detalhes por
     * ID).
     * 
     * Operação: Pessoa/Paciente/Profissional/... → PessoaDetailDTO
     * 
     * Identifica a subclasse da Pessoa e engloba dinamicamente seus atributos.
     * O cliente utiliza o campo "tipo" para saber qual subobjeto popular.
     */
    public PessoaDetailDTO toDetailDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        // Converte endereços
        List<EnderecoDTO> enderecosDTO = converterEnderecos(pessoa.getEnderecos());

        // Inicializa campos específicos (todos null por padrão)
        String tipo = "PESSOA";
        String statusPaciente = null;
        String tipoEntrada = null;
        java.time.LocalDate dataCadastroPaciente = null;
        Boolean convenio = null;
        List<CIDSummaryDTO> cids = null;
        String cidPrincipalId = null;
        String cidPrincipalDescricao = null;
        Integer idEscola = null;
        String nomeEscola = null;
        Integer idCargo = null;
        String nomeCargo = null;
        String registroProfissional = null;
        String estadoRegistro = null;
        String cargaHorariaSemanal = null;
        List<EspecialidadeSummaryDTO> especialidades = null;
        String cnpj = null;
        String razaoSocial = null;
        String nomeFantasia = null;
        String inscricaoEstadual = null;

        // Verifica o tipo real e popula os campos correspondentes
        if (pessoa instanceof Paciente) {
            tipo = "PACIENTE";
            Paciente paciente = (Paciente) pessoa;

            statusPaciente = paciente.getStatusPaciente() != null
                    ? paciente.getStatusPaciente().toString()
                    : null;
            tipoEntrada = paciente.getTipoEntrada() != null
                    ? paciente.getTipoEntrada().toString()
                    : null;
            dataCadastroPaciente = paciente.getDataCadastro();
            convenio = paciente.isConvenio();

            // CIDs
            cids = paciente.getCids() != null
                    ? paciente.getCids().stream()
                            .map(this::toCIDDTO)
                            .collect(Collectors.toList())
                    : null;

            // CID Principal
            if (paciente.getCidPrincipal() != null) {
                cidPrincipalId = paciente.getCidPrincipal().getCodigoCID();
                cidPrincipalDescricao = paciente.getCidPrincipal().getDescricao();
            }

            // Escola
            if (paciente.getEscola() != null) {
                idEscola = paciente.getEscola().getIdEscola();
                nomeEscola = paciente.getEscola().getNome();
            }

        } else if (pessoa instanceof EspecialistaPJ) {
            tipo = "ESPECIALISTA_PJ";
            EspecialistaPJ pj = (EspecialistaPJ) pessoa;

            registroProfissional = pj.getRegistroConselho();
            especialidades = pj.getEspecialidades() != null
                    ? pj.getEspecialidades().stream()
                            .map(this::toEspecialidadeDTO)
                            .collect(Collectors.toList())
                    : null;

            cnpj = pj.getCNPJ();
            razaoSocial = pj.getRazaoSocial();
            nomeFantasia = pj.getNomeFantasia();
            inscricaoEstadual = pj.getInscricaoEstadual();

            if (pj.getCargo() != null) {
                idCargo = pj.getCargo().getIdCargo();
                nomeCargo = pj.getCargo().getNomeCargo();
            }

        } else if (pessoa instanceof Especialista) {
            tipo = "ESPECIALISTA";
            Especialista especialista = (Especialista) pessoa;

            registroProfissional = especialista.getRegistroConselho();
            especialidades = especialista.getEspecialidades() != null
                    ? especialista.getEspecialidades().stream()
                            .map(this::toEspecialidadeDTO)
                            .collect(Collectors.toList())
                    : null;

            if (especialista.getCargo() != null) {
                idCargo = especialista.getCargo().getIdCargo();
                nomeCargo = especialista.getCargo().getNomeCargo();
            }

        } else if (pessoa instanceof Profissional) {
            tipo = "PROFISSIONAL";
            Profissional profissional = (Profissional) pessoa;

            if (profissional.getCargo() != null) {
                idCargo = profissional.getCargo().getIdCargo();
                nomeCargo = profissional.getCargo().getNomeCargo();
            }
        }

        return new PessoaDetailDTO(
              // ── DADOS BASE (Pessoa) ──
            pessoa.getIdCadastro(),
            pessoa.getNome(),
            pessoa.getCpf(),
            pessoa.getDataNascimento(),
            pessoa.getSexo(),
            pessoa.getCelular(),
            pessoa.getEstadoCivil() != null ? pessoa.getEstadoCivil().toString() : null,
            pessoa.getStatusCadastro() != null ? pessoa.getStatusCadastro().toString() : null,
            pessoa.getNumCNS(),
            enderecosDTO, // <-- Aqui entra a variável traduzida na linha 67
            
            // ── TIPO DA SUBCLASSE ──
            tipo,
            
            // ── CAMPOS ESPECÍFICOS: PACIENTE ──
            statusPaciente,
            tipoEntrada,
            dataCadastroPaciente,
            convenio,
            cids,
            cidPrincipalId,
            cidPrincipalDescricao,
            idEscola,
            nomeEscola,
            
            // ── CAMPOS ESPECÍFICOS: PROFISSIONAL/ESPECIALISTA ──
            idCargo,
            nomeCargo,
            registroProfissional,
            estadoRegistro,
            cargaHorariaSemanal,
            especialidades,
            
            // ── CAMPOS ESPECÍFICOS: ESPECIALISTA_PJ ──
            cnpj,
            razaoSocial,
            nomeFantasia,
            inscricaoEstadual);
    }

    /**
     * Converte Paciente para PacienteSummaryDTO (para listagens clínicas).
     * 
     * Operação: Paciente → PacienteSummaryDTO
     * 
     * Traz dados clínicos específicos + essenciais de identidade (idCadastro,
     * nome).
     */
    public PacienteSummaryDTO toSummaryDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        return new PacienteSummaryDTO(
                paciente.getIdCadastro(),
                paciente.getNome(),
                paciente.getStatusPaciente() != null ? paciente.getStatusPaciente().toString() : null,
                paciente.getTipoEntrada() != null ? paciente.getTipoEntrada().toString() : null,
                paciente.getStatusCadastro() != null ? paciente.getStatusCadastro().toString() : null,
                paciente.getDataCadastro());
    }

    /**
     * Converte Paciente para PacienteDetailDTO (para detalhes clínicos completos).
     * 
     * Operação: Paciente → PacienteDetailDTO
     * 
     * Carga total: todos os atributos de Pessoa + Paciente + relacionamentos.
     */
    public PacienteDetailDTO toDetailDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        // Converte endereços
        List<EnderecoDTO> enderecosDTO = converterEnderecos(paciente.getEnderecos());

        // CIDs
        List<CIDSummaryDTO> cidsDTO = paciente.getCids() != null
                ? paciente.getCids().stream()
                        .map(this::toCIDDTO)
                        .collect(Collectors.toList())
                : List.of();

        // CID Principal
        String cidPrincipalId = null;
        String cidPrincipalDescricao = null;
        if (paciente.getCidPrincipal() != null) {
            cidPrincipalId = paciente.getCidPrincipal().getCodigoCID();
            cidPrincipalDescricao = paciente.getCidPrincipal().getDescricao();
        }

        // Escola
        Integer idEscola = null;
        String nomeEscola = null;
        String tipoEscola = null;
        if (paciente.getEscola() != null) {
            idEscola = paciente.getEscola().getIdEscola();
            nomeEscola = paciente.getEscola().getNome();
            tipoEscola = paciente.getEscola().getTipoEscola() != null
                    ? paciente.getEscola().getTipoEscola().toString()
                    : null;
        }

        // Prontuario (resumido)
        String alergias = null;
        String comorbidades = null;
        if (paciente.getProntuario() != null) {
            Prontuario prontuario = paciente.getProntuario();
            alergias = prontuario.getAlergias();
            comorbidades = prontuario.getComorbidade();
        }

        return new PacienteDetailDTO(
                paciente.getIdCadastro(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getDataNascimento(),
                paciente.getSexo(),
                paciente.getCelular(),
                paciente.getEstadoCivil() != null ? paciente.getEstadoCivil().toString() : null,
                paciente.getStatusCadastro() != null ? paciente.getStatusCadastro().toString() : null,
                paciente.getNumCNS(),
                enderecosDTO,

                paciente.getStatusPaciente() != null ? paciente.getStatusPaciente().toString() : null,
                paciente.getTipoEntrada() != null ? paciente.getTipoEntrada().toString() : null,
                paciente.getDataCadastro(),
                paciente.isConvenio(),

                idEscola,
                nomeEscola,
                tipoEscola,

                cidPrincipalId,
                cidPrincipalDescricao,

                cidsDTO,

                alergias,
                comorbidades);
    }

    /**
     * Converte CID para DTO resumido.
     */
    private CIDSummaryDTO toCIDDTO(CID cid) {
        if (cid == null) {
            return null;
        }
        return new CIDSummaryDTO(
                cid.getCodigoCID(),
                cid.getDescricao());
    }

    /**
     * Converte Especialidade para DTO resumido.
     */
    private EspecialidadeSummaryDTO toEspecialidadeDTO(Especialidade especialidade) {
        if (especialidade == null) {
            return null;
        }
        return new EspecialidadeSummaryDTO(
                especialidade.getIdEspecialidade(),
                especialidade.getNome());
    }

    /**
     * Converte Endereco para DTO.
     */
    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDTO(
                endereco.getIdEndereco(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep());
    }

    /**
     * Converte Set de Enderecos para List de DTOs.
     */
    private List<EnderecoDTO> converterEnderecos(java.util.Set<Endereco> enderecos) {
        if (enderecos == null || enderecos.isEmpty()) {
            return List.of();
        }
        return enderecos.stream()
                .map(this::toEnderecoDTO)
                .collect(Collectors.toList());
    }
}
