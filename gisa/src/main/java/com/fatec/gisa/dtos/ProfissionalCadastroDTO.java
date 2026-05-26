package com.fatec.gisa.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO Cadastro para Profissionais - Utilizado em POST/PUT (formulário de cadastro_profissional.html).
 * 
 * Unifica todos os dados coletados no formulário HTML em seções:
 * 1. Dados Pessoais (nome, CPF, data de nascimento, RG)
 * 2. Acesso (senha)
 * 3. Informações Profissionais (especialidades, registro, estado, carga horária)
 * 4. Contato (email, telefone, endereço)
 * 5. Dados PJ (condicionais - quando aplicável)
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
    String estadoRegistro,               // Estado onde o registro é válido
    String cargaHorariaSemanal,          // Ex: "40 horas"
    
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
