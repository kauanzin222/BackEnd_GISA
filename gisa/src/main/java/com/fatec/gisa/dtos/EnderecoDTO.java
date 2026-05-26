package com.fatec.gisa.dtos;

/**
 * DTO para endereço completo.
 * Traz todos os campos necessários para exibição e persistência de endereco.
 */
public record EnderecoDTO(
    Integer idEndereco,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado,
    String cep
) {}
