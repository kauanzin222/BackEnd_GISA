package com.fatec.gisa.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.models.Especialidade;
import com.fatec.gisa.dtos.EspecialidadeSummaryDTO;
import com.fatec.gisa.dtos.EnderecoDTO;
import com.fatec.gisa.dtos.ProfissionalSummaryDTO;
import com.fatec.gisa.dtos.ProfissionalDetailDTO;

@Component
public class ProfissionalMapper {

    /**
     * Converte Profissional para ProfissionalSummaryDTO de forma polimórfica e segura.
     */
    public ProfissionalSummaryDTO toSummaryDTO(Profissional profissional) {
        if (profissional == null) {
            return null;
        }

        List<String> nomeEspecialidades = List.of();
        String registro = null;

        if (profissional instanceof Especialista especialista) {
            nomeEspecialidades = especialista.getEspecialidades() != null
                ? especialista.getEspecialidades().stream().map(Especialidade::getNome).collect(Collectors.toList())
                : List.of();
            registro = especialista.getRegistroConselho();
        }

        String status = profissional.getStatusCadastro() != null
            ? profissional.getStatusCadastro().toString()
            : "Desconhecido";

        return new ProfissionalSummaryDTO(
            profissional.getIdCadastro(),
            profissional.getNome(),
            nomeEspecialidades,
            registro,
            profissional.getEmail(),
            status
        );
    }

    /**
     * Converte Profissional para ProfissionalDetailDTO de forma polimórfica e ajustada.
     */
    public ProfissionalDetailDTO toDetailDTO(Profissional profissional) {
        if (profissional == null) {
            return null;
        }

        List<EspecialidadeSummaryDTO> especialidadesDTO = List.of();
        List<EnderecoDTO> enderecosDTO = profissional.getEnderecos() != null
            ? profissional.getEnderecos().stream().map(this::toEnderecoDTO).collect(Collectors.toList())
            : List.of();

        Boolean isPJ = profissional instanceof EspecialistaPJ;
        String cnpj = null;
        String razaoSocial = null;
        String nomeFantasia = null;
        String inscricaoEstadual = null;
        String registro = null;

        if (profissional instanceof Especialista especialista) {
            registro = especialista.getRegistroConselho();
            especialidadesDTO = especialista.getEspecialidades() != null
                ? especialista.getEspecialidades().stream().map(this::toEspecialidadeDTO).collect(Collectors.toList())
                : List.of();
            
            if (especialista instanceof EspecialistaPJ pj) {
                cnpj = pj.getCNPJ();
                razaoSocial = pj.getRazaoSocial();
                nomeFantasia = pj.getNomeFantasia();
                inscricaoEstadual = pj.getInscricaoEstadual();
            }
        }

        // CORREÇÃO: Assinatura do construtor reduzida (removido os placeholders nulos que quebravam a compilação)
        return new ProfissionalDetailDTO(
            profissional.getIdCadastro(),
            profissional.getNome(),
            profissional.getCpf(),
            profissional.getDataNascimento(),
            profissional.getCelular(),
            profissional.getEstadoCivil() != null ? profissional.getEstadoCivil().toString() : null,
            profissional.getStatusCadastro() != null ? profissional.getStatusCadastro().toString() : null,
            enderecosDTO,
            profissional.getEmail(),
            registro,
            especialidadesDTO,
            cnpj,
            razaoSocial,
            nomeFantasia,
            inscricaoEstadual,
            isPJ
        );
    }

    private EspecialidadeSummaryDTO toEspecialidadeDTO(Especialidade especialidade) {
        if (especialidade == null) return null;
        return new EspecialidadeSummaryDTO(especialidade.getIdEspecialidade(), especialidade.getNome());
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        if (endereco == null) return null;
        return new EnderecoDTO(endereco.getIdEndereco(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
    }
}