package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Detail para Pessoa - Utilizado em GET /api/pessoas/{id}.
 * 
 * Retorno polimórfico detalhado por ID. Identifica o tipo da subclasse
 * (Paciente, Profissional, etc.) e engloba dinamicamente seus atributos específicos.
 * 
 * Estratégia: Inclui TODOS os campos possíveis das subclasses.
 * O cliente utiliza o campo "tipo" para saber qual subobjeto popular.
 * 
 * Tipos possíveis: "PACIENTE", "PROFISSIONAL", "ESPECIALISTA", "ESPECIALISTA_PJ"
 */
public record PessoaDetailDTO(
    // ── DADOS BASE (Pessoa) ──
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
    
    // ── TIPO DA SUBCLASSE ──
    String tipo,  // "PACIENTE", "PROFISSIONAL", "ESPECIALISTA", "ESPECIALISTA_PJ"
    
    // ── CAMPOS ESPECÍFICOS: PACIENTE ──
    String statusPaciente,
    String tipoEntrada,
    LocalDate dataCadastroPaciente,
    Boolean convenio,
    List<CIDSummaryDTO> cids,
    String cidPrincipalId,
    String cidPrincipalDescricao,
    Integer idEscola,
    String nomeEscola,
    
    // ── CAMPOS ESPECÍFICOS: PROFISSIONAL/ESPECIALISTA ──
    Integer idCargo,
    String nomeCargo,
    String registroProfissional,
    String estadoRegistro,
    String cargaHorariaSemanal,
    List<EspecialidadeSummaryDTO> especialidades,
    
    // ── CAMPOS ESPECÍFICOS: ESPECIALISTA_PJ ──
    String cnpj,
    String razaoSocial,
    String nomeFantasia,
    String inscricaoEstadual
) {}
