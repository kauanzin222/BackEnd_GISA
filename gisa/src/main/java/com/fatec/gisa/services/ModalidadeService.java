package com.fatec.gisa.services;

import com.fatec.gisa.models.Modalidade;
import com.fatec.gisa.repositories.ModalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ModalidadeService {

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    public Modalidade criar(Modalidade modalidade) {
        return modalidadeRepository.save(modalidade);
    }

    public List<Modalidade> listarTodas() {
        return modalidadeRepository.findAll();
    }

    public Optional<Modalidade> buscarPorId(Integer id) {
        return modalidadeRepository.findById(id);
    }

    public Modalidade atualizar(Integer id, Modalidade modalidadeAtualizada) {
        Optional<Modalidade> modalidadeOptional = modalidadeRepository.findById(id);
        if (modalidadeOptional.isPresent()) {
            Modalidade modalidade = modalidadeOptional.get();
            if (modalidadeAtualizada.getNome() != null) {
                modalidade.setNome(modalidadeAtualizada.getNome());
            }
            if (modalidadeAtualizada.getDescricao() != null) {
                modalidade.setDescricao(modalidadeAtualizada.getDescricao());
            }
            return modalidadeRepository.save(modalidade);
        }
        return null;
    }

    public void deletar(Integer id) {
        modalidadeRepository.deleteById(id);
    }
}
