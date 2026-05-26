package com.fatec.gisa.dtos;

/**
 * DTO Summary para Pessoa - Utilizado em listagens (GET /api/pessoas).
 * 
 * Retorna apenas os campos nativos da classe base Pessoa, sem fazer joins 
 * ou trazer dados das subclasses (Paciente, Profissional, etc.).
 * 
 * Campos:
 * - ID do cadastro
 * - Nome
 * - CPF
 * - Celular
 * - Estado Civil
 * - Status do Cadastro
 */
public record PessoaSummaryDTO(
    Integer idCadastro,
    String nome,
    String cpf,
    String celular,
    String estadoCivil,
    String statusCadastro
) {}
