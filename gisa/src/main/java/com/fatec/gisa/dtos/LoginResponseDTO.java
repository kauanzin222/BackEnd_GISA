package com.fatec.gisa.dtos;

/**
 * DTO enviado ao front-end após a confirmação de credenciais válidas.
 */
public record LoginResponseDTO(
        Integer id,
        String nome) {
}