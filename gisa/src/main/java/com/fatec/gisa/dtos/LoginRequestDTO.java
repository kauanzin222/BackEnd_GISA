package com.fatec.gisa.dtos;

/**
 * DTO que recebe as credenciais digitadas na tela de login.
 */
public record LoginRequestDTO(
        Integer id,
        String senha) {
}