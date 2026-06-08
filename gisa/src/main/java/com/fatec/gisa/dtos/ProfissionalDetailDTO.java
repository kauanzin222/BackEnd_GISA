package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Detail para Profissionais - Utilizado em GET /api/profissionais/{id}.
 * 
 * Retorna os dados purificados do profissional para renderização visual do formulário.
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
    
    // ── INFORMAÇÕES PROFISSIONAIS (Especialista) ──
    String registroProfissional,
    List<EspecialidadeSummaryDTO> especialidades,
    
    // ── DADOS PJ (se EspecialistaPJ) ──
    String cnpj,
    String razaoSocial,
    String nomeFantasia,
    String inscricaoEstadual,
    
    // ── FLAG: Indica se é PJ ──
    Boolean isPJ
) {}