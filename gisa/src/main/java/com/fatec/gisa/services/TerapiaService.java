package com.fatec.gisa.services;

import com.fatec.gisa.models.Terapia;
import com.fatec.gisa.repositories.TerapiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TerapiaService {

    @Autowired
    private TerapiaRepository terapiaRepository;

    public Terapia criar(Terapia terapia) {
        return terapiaRepository.save(terapia);
    }

    public List<Terapia> listarTodas() {
        return terapiaRepository.findAll();
    }

    public Optional<Terapia> buscarPorId(Integer id) {
        return terapiaRepository.findById(id);
    }

    public Terapia atualizar(Integer id, Terapia terapiaAtualizada) {
        Optional<Terapia> terapiaOptional = terapiaRepository.findById(id);
        if (terapiaOptional.isPresent()) {
            Terapia terapia = terapiaOptional.get();
            if (terapiaAtualizada.getData() != null) {
                terapia.setData(terapiaAtualizada.getData());
            }
            if (terapiaAtualizada.getDescricao() != null) {
                terapia.setDescricao(terapiaAtualizada.getDescricao());
            }
            if (terapiaAtualizada.getStatusTerapia() != null) {
                terapia.setStatusTerapia(terapiaAtualizada.getStatusTerapia());
            }
            if (terapiaAtualizada.getModalidade() != null) {
                terapia.setModalidade(terapiaAtualizada.getModalidade());
            }
            if (terapiaAtualizada.getPacientes() != null) {
                terapia.setPacientes(terapiaAtualizada.getPacientes());
            }
            if (terapiaAtualizada.getEspecialistas() != null) {
                terapia.setEspecialistas(terapiaAtualizada.getEspecialistas());
            }
            return terapiaRepository.save(terapia);
        }
        return null;
    }

    public void deletar(Integer id) {
        terapiaRepository.deleteById(id);
    }
}
