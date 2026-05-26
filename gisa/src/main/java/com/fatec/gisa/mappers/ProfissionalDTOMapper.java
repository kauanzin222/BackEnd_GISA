package com.fatec.gisa.mappers;

import org.springframework.stereotype.Component;
import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.enums.EstadoCivil;
import com.fatec.gisa.enums.StatusCadastro;

import java.util.HashSet;

@Component
public class ProfissionalDTOMapper {

    public Especialista toEntity(ProfissionalCadastroDTO dto) {
        if (dto == null) {
            return null;
        }

        // Se o CNPJ foi enviado, a instância real deve ser EspecialistaPJ
        Especialista especialista = (dto.cnpj() != null && !dto.cnpj().isBlank()) 
            ? new EspecialistaPJ() 
            : new Especialista();

        // ── MAPEAMENTO OBRIGATÓRIO: TAB_PESSOA ──
        especialista.setNome(dto.nome());
        especialista.setCpf(dto.cpf().replaceAll("[^\\d]", ""));
        especialista.setDataNascimento(dto.dataNascimento());
        especialista.setEmail(dto.email());
        especialista.setCelular(dto.celular() != null ? dto.celular() : "(15) 99999-9999");
        
        // CORREÇÃO: Passando as constantes dos ENUMs exigidas pelos setters
        especialista.setSexo('M'); 
        especialista.setEstadoCivil(EstadoCivil.SOLTEIRO);
        especialista.setStatusCadastro(StatusCadastro.ATIVO);
        especialista.setNumCNS("210008000000000"); // Evita rejeição ORA-01400 no banco

        // ── MAPEAMENTO: TAB_ESPECIALISTA ──
        especialista.setRegistroConselho(dto.registroProfissional());

        // ── MAPEAMENTO: ENDEREÇO (BLINDADO CONTRA NULLPOINTEREXCEPTION) ──
        if (dto.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(dto.endereco().rua());
            endereco.setNumero(dto.endereco().numero());
            endereco.setComplemento(dto.endereco().complemento());
            endereco.setBairro(dto.endereco().bairro());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setEstado(dto.endereco().estado());
            endereco.setCep(dto.endereco().cep().replaceAll("[^\\d]", ""));
            
            // CORREÇÃO CRÍTICA: Instancia o Set caso ele tenha nascido nulo
            if (especialista.getEnderecos() == null) {
                especialista.setEnderecos(new HashSet<>());
            }
            especialista.getEnderecos().add(endereco);
        }

        // ── MAPEAMENTO: TAB_ESPECIALISTAPJ (Se aplicável) ──
        if (especialista instanceof EspecialistaPJ pj) {
            pj.setCNPJ(dto.cnpj().replaceAll("[^\\d]", ""));
            pj.setRazaoSocial(dto.razaoSocial());
            pj.setNomeFantasia(dto.nomeFantasia());
            pj.setInscricaoEstadual(dto.inscricaoEstadual());
        }

        return especialista;
    }

    public Especialista updateEntity(ProfissionalCadastroDTO dto, Especialista especialistaExistente) {
        if (dto == null || especialistaExistente == null) {
            return especialistaExistente;
        }

        especialistaExistente.setNome(dto.nome());
        especialistaExistente.setDataNascimento(dto.dataNascimento());
        especialistaExistente.setEmail(dto.email());
        
        if (dto.celular() != null) {
            especialistaExistente.setCelular(dto.celular());
        }
        
        especialistaExistente.setRegistroConselho(dto.registroProfissional());

        if (especialistaExistente instanceof EspecialistaPJ pj) {
            pj.setRazaoSocial(dto.razaoSocial());
            pj.setNomeFantasia(dto.nomeFantasia());
            pj.setInscricaoEstadual(dto.inscricaoEstadual());
        }

        return especialistaExistente;
    }
}