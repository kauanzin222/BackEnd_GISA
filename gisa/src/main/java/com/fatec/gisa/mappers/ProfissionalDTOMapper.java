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

        Especialista especialista = (dto.cnpj() != null && !dto.cnpj().isBlank()) 
            ? new EspecialistaPJ() 
            : new Especialista();

        // ── MAPEAMENTO OBRIGATÓRIO: TAB_PESSOA ──
        especialista.setNome(dto.nome());
        especialista.setCpf(dto.cpf().replaceAll("[^\\d]", ""));
        especialista.setDataNascimento(dto.dataNascimento());
        especialista.setEmail(dto.email());
        especialista.setCelular(dto.celular() != null ? dto.celular() : "(15) 99999-9999");
        
        especialista.setSexo('M'); 
        especialista.setEstadoCivil(EstadoCivil.SOLTEIRO);
        especialista.setStatusCadastro(StatusCadastro.ATIVO);
        especialista.setNumCNS("210008000000000"); 

        // ── MAPEAMENTO: TAB_ESPECIALISTA (CAMPOS DESCONTINUADOS REMOVIDOS) ──
        especialista.setRegistroConselho(dto.registroProfissional());

        // ── MAPEAMENTO: ENDEREÇO ──
        if (dto.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(dto.endereco().rua());
            endereco.setNumero(dto.endereco().numero());
            endereco.setComplemento(dto.endereco().complemento());
            endereco.setBairro(dto.endereco().bairro());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setEstado(dto.endereco().estado());
            endereco.setCep(dto.endereco().cep().replaceAll("[^\\d]", ""));
            
            if (especialista.getEnderecos() == null) {
                especialista.setEnderecos(new HashSet<>());
            }
            especialista.getEnderecos().add(endereco);
        }

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