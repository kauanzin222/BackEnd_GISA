package com.fatec.gisa.services;

import com.fatec.gisa.models.CBO;
import com.fatec.gisa.repositories.CBORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CBOService {

    @Autowired
    private CBORepository cboRepository;

    public CBO criar(CBO cbo) {
        return cboRepository.save(cbo);
    }

    public List<CBO> listarTodas() {
        return cboRepository.findAll();
    }

    public Optional<CBO> buscarPorId(Integer id) {
        return cboRepository.findById(id);
    }

    public CBO atualizar(Integer id, CBO cboAtualizada) {
        Optional<CBO> cboOptional = cboRepository.findById(id);
        if (cboOptional.isPresent()) {
            CBO cbo = cboOptional.get();
            if (cboAtualizada.getTituloCBO() != null) {
                cbo.setTituloCBO(cboAtualizada.getTituloCBO());
            }
            return cboRepository.save(cbo);
        }
        return null;
    }

    public void deletar(Integer id) {
        cboRepository.deleteById(id);
    }
}
