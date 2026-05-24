package com.fatec.gisa.services;

import com.fatec.gisa.models.Responsavel;
import com.fatec.gisa.repositories.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public Responsavel criar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    public Optional<Responsavel> buscarPorId(Integer id) {
        return responsavelRepository.findById(id);
    }

    public Responsavel buscarPorCpf(String cpf) {
        return responsavelRepository.findByCpf(cpf);
    }

    public Responsavel atualizar(Integer id, Responsavel responsavelAtualizado) {
        Optional<Responsavel> responsavelOptional = responsavelRepository.findById(id);
        if (responsavelOptional.isPresent()) {
            Responsavel responsavel = responsavelOptional.get();
            if (responsavelAtualizado.getNome() != null) {
                responsavel.setNome(responsavelAtualizado.getNome());
            }
            if (responsavelAtualizado.getOcupacao() != null) {
                responsavel.setOcupacao(responsavelAtualizado.getOcupacao());
            }
            if (responsavelAtualizado.getCelular() != null) {
                responsavel.setCelular(responsavelAtualizado.getCelular());
            }
            return responsavelRepository.save(responsavel);
        }
        return null;
    }

    public void deletar(Integer id) {
        responsavelRepository.deleteById(id);
    }
}
