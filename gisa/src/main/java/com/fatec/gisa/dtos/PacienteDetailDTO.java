package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Detail para Pacientes - Utilizado em GET /api/pacientes/{id}.
 * 
 * Carga total para visualização profunda. Deve unir TODOS os atributos 
 * de Pessoa com TODOS os atributos e relacionamentos de Paciente.
 * 
 * Inclui:
 * - Dados de identidade (Pessoa)
 * - Dados clínicos (Paciente)
 * - Relacionamentos: Escola, lista de CID, CID Principal, Prontuario
 * - Endereços
 */
public record PacienteDetailDTO(
    // ── IDENTIDADE (Pessoa) ──
    Integer idCadastro,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    Character sexo,
    String celular,
    String estadoCivil,
    String statusCadastro,
    String numCNS,
    List<EnderecoDTO> enderecos,
    
    // ── DADOS CLÍNICOS (Paciente) ──
    String statusPaciente,
    String tipoEntrada,
    LocalDate dataCadastroPaciente,
    Boolean convenio,
    
    // ── RELACIONAMENTOS (Paciente) ──
    Integer idEscola,
    String nomeEscola,
    String tipoEscola,
    
    String cidPrincipalId,
    String cidPrincipalDescricao,
    
    List<CIDSummaryDTO> cids,
    
    // Prontuario - apenas informações resumidas para não sobrecarregar
    String alergias,
    String comorbidade
) {}
