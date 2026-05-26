package com.fatec.gisa.dtos;

import java.time.LocalDate;

/**
 * DTO Summary para Pacientes - Utilizado em listagens (GET /api/pacientes).
 * 
 * Traz dados clínicos específicos da classe Paciente, mas inclui OBRIGATORIAMENTE 
 * os dados essenciais de identidade da classe mãe (idCadastro e nome), 
 * para que o front saiba quem é o paciente sem carregar o objeto inteiro.
 * 
 * Campos de Pessoa:
 * - idCadastro, nome (essenciais)
 * 
 * Campos de Paciente:
 * - statusPaciente
 * - tipoEntrada
 * - statusCadastro (de Pessoa)
 */
public record PacienteSummaryDTO(
    // ── IDENTIDADE (Pessoa) ──
    Integer idCadastro,
    String nome,
    
    // ── DADOS CLÍNICOS (Paciente) ──
    String statusPaciente,
    String tipoEntrada,
    String statusCadastro,
    LocalDate dataCadastroPaciente
) {}
