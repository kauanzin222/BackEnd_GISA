package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Cadastro para Profissionais - Utilizado em POST/PUT.
 * 
 * Estrutura limpa sem os campos descontinuados de Carga Horária e Estado do Registro.
 */
public record ProfissionalCadastroDTO(
    // ── DADOS PESSOAIS ──
    String nome,
    String cpf,
    LocalDate dataNascimento,
    
    // ── DADOS DE ACESSO ──
    String senhaProvisoria,  // Será armazenada em Usuario
    
    // ── INFORMAÇÕES PROFISSIONAIS ──
    List<Integer> idEspecialidades,      // IDs das especialidades selecionadas
    String registroProfissional,         // CRM/CREFITO
    
    // ── CONTATO ──
    String email,
    String celular,
    EnderecoDTO endereco,               // Endereço completo
    
    // ── PESSOA JURÍDICA (opcional/condicional) ──
    String cnpj,                         // Null se não for PJ
    String razaoSocial,                 // Null se não for PJ
    String nomeFantasia,                // Null se não for PJ
    String inscricaoEstadual            // Null se não for PJ
) {}