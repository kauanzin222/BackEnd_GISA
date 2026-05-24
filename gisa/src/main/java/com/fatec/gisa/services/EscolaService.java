package com.fatec.gisa.services;

import com.fatec.gisa.models.Escola;
import com.fatec.gisa.repositories.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    public Escola criar(Escola escola) {
        return escolaRepository.save(escola);
    }

    public List<Escola> listarTodas() {
        return escolaRepository.findAll();
    }

    public Optional<Escola> buscarPorId(Integer id) {
        return escolaRepository.findById(id);
    }

    public Escola atualizar(Integer id, Escola escolaAtualizada) {
        Optional<Escola> escolaOptional = escolaRepository.findById(id);
        if (escolaOptional.isPresent()) {
            Escola escola = escolaOptional.get();
            if (escolaAtualizada.getNome() != null) {
                escola.setNome(escolaAtualizada.getNome());
            }
            if (escolaAtualizada.getTipoEscola() != null) {
                escola.setTipoEscola(escolaAtualizada.getTipoEscola());
            }
            if (escolaAtualizada.getTelefone() != null) {
                escola.setTelefone(escolaAtualizada.getTelefone());
            }
            return escolaRepository.save(escola);
        }
        return null;
    }

    public void deletar(Integer id) {
        escolaRepository.deleteById(id);
    }
}
