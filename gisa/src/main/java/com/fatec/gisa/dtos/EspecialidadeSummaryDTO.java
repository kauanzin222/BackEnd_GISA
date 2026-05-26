package com.fatec.gisa.dtos;

/**
 * DTO para representar uma Especialidade em resumo.
 * Utilizado em listas e referencias de especialidades.
 */
public record EspecialidadeSummaryDTO(
    Integer idEspecialidade,
    String nome
) {}
