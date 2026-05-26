package com.fatec.gisa.dtos;

import java.util.List;

/**
 * DTO Summary para Profissionais - Utilizado em listagens (GET /api/profissionais).
 * 
 * Baseia-se exatamente nas colunas da tabela em gestao_profissionais.html:
 * - ID do profissional
 * - Nome do Profissional
 * - Especialidades (lista de nomes)
 * - Registro (CRM/CREFITO)
 * - Contato (E-mail)
 * - Status
 * 
 * Não traz dados pesados como Prontuario, Endereco completo ou detalhes sensíveis.
 */
public record ProfissionalSummaryDTO(
    Integer idProfissional,
    String nome,
    List<String> especialidades,  // Lista de nomes das especialidades
    String registroProfissional,   // CRM/CREFITO
    String email,
    String status  // "Ativo", "Inativo", etc.
) {}
