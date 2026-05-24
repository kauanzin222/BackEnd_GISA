package com.fatec.gisa.services;

import com.fatec.gisa.models.Prontuario;
import com.fatec.gisa.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public Prontuario criar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> buscarPorId(Integer id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario atualizar(Integer id, Prontuario prontuarioAtualizado) {
        Optional<Prontuario> prontuarioOptional = prontuarioRepository.findById(id);
        if (prontuarioOptional.isPresent()) {
            Prontuario prontuario = prontuarioOptional.get();
            if (prontuarioAtualizado.getAlergias() != null) {
                prontuario.setAlergias(prontuarioAtualizado.getAlergias());
            }
            if (prontuarioAtualizado.getComorbidade() != null) {
                prontuario.setComorbidade(prontuarioAtualizado.getComorbidade());
            }
            if (prontuarioAtualizado.getMobilidade() != null) {
                prontuario.setMobilidade(prontuarioAtualizado.getMobilidade());
            }
            return prontuarioRepository.save(prontuario);
        }
        return null;
    }

    public void deletar(Integer id) {
        prontuarioRepository.deleteById(id);
    }
}
