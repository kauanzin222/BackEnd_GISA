package com.fatec.gisa.services;

import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.repositories.EspecialistaPJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialistaPJService {

    @Autowired
    private EspecialistaPJRepository especialistaPJRepository;

    public EspecialistaPJ criar(EspecialistaPJ especialistaPJ) {
        return especialistaPJRepository.save(especialistaPJ);
    }

    public List<EspecialistaPJ> listarTodos() {
        return especialistaPJRepository.findAll();
    }

    public Optional<EspecialistaPJ> buscarPorId(Integer id) {
        return especialistaPJRepository.findById(id);
    }

    public EspecialistaPJ buscarPorCNPJ(String cnpj) {
        return especialistaPJRepository.findByCNPJ(cnpj);
    }

    public EspecialistaPJ atualizar(Integer id, EspecialistaPJ especialistaPJAtualizado) {
        Optional<EspecialistaPJ> especialistaPJOptional = especialistaPJRepository.findById(id);
        if (especialistaPJOptional.isPresent()) {
            EspecialistaPJ especialistaPJ = especialistaPJOptional.get();
            if (especialistaPJAtualizado.getRazaoSocial() != null) {
                especialistaPJ.setRazaoSocial(especialistaPJAtualizado.getRazaoSocial());
            }
            if (especialistaPJAtualizado.getNomeFantasia() != null) {
                especialistaPJ.setNomeFantasia(especialistaPJAtualizado.getNomeFantasia());
            }
            if (especialistaPJAtualizado.getInscricaoEstadual() != null) {
                especialistaPJ.setInscricaoEstadual(especialistaPJAtualizado.getInscricaoEstadual());
            }
            if (especialistaPJAtualizado.getNome() != null) {
                especialistaPJ.setNome(especialistaPJAtualizado.getNome());
            }
            return especialistaPJRepository.save(especialistaPJ);
        }
        return null;
    }

    public void deletar(Integer id) {
        especialistaPJRepository.deleteById(id);
    }
}
