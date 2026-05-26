package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Detail para Profissionais - Utilizado em GET /api/profissionais/{id}.
 * 
 * Retorna todos os dados do profissional para preenchimento do formulário 
 * de visualização/edição (GET traz este DTO, PUT recebe ProfissionalCadastroDTO).
 * 
 * Inclui informações de identidade (Pessoa) + profissionais (Especialista).
 * Se for PJ, os campos de PJ serão preenchidos; senão serão null.
 */
public record ProfissionalDetailDTO(
    // ── IDENTIDADE (Pessoa) ──
    Integer idCadastro,
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String celular,
    String estadoCivil,
    String statusCadastro,
    List<EnderecoDTO> enderecos,
    
    // ── DADOS DE ACESSO (Usuario) ──
    String email,
    // Nota: senha não é retornada por segurança
    
    // ── INFORMAÇÕES PROFISSIONAIS (Especialista) ──
    String registroProfissional,
    String estadoRegistro,
    String cargaHorariaSemanal,
    List<EspecialidadeSummaryDTO> especialidades,
    
    // ── DADOS PJ (se EspecialistaPJ) ──
    String cnpj,
    String razaoSocial,
    String nomeFantasia,
    String inscricaoEstadual,
    
    // ── FLAG: Indica se é PJ ──
    Boolean isPJ
) {}
