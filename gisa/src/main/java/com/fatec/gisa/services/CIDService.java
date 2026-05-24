package com.fatec.gisa.services;

import com.fatec.gisa.models.CID;
import com.fatec.gisa.repositories.CIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CIDService {

    @Autowired
    private CIDRepository cidRepository;

    public CID criar(CID cid) {
        return cidRepository.save(cid);
    }

    public List<CID> listarTodas() {
        return cidRepository.findAll();
    }

    public Optional<CID> buscarPorId(Integer id) {
        return cidRepository.findById(id);
    }

    public CID atualizar(Integer id, CID cidAtualizada) {
        Optional<CID> cidOptional = cidRepository.findById(id);
        if (cidOptional.isPresent()) {
            CID cid = cidOptional.get();
            if (cidAtualizada.getDescricao() != null) {
                cid.setDescricao(cidAtualizada.getDescricao());
            }
            return cidRepository.save(cid);
        }
        return null;
    }

    public void deletar(Integer id) {
        cidRepository.deleteById(id);
    }
}
