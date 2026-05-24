package com.fatec.gisa.services;

import com.fatec.gisa.models.Permissao;
import com.fatec.gisa.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao criar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public List<Permissao> listarTodas() {
        return permissaoRepository.findAll();
    }

    public Optional<Permissao> buscarPorId(Integer id) {
        return permissaoRepository.findById(id);
    }

    public Permissao buscarPorNome(String nome) {
        return permissaoRepository.findByNome(nome);
    }

    public Permissao atualizar(Integer id, Permissao permissaoAtualizada) {
        Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);
        if (permissaoOptional.isPresent()) {
            Permissao permissao = permissaoOptional.get();
            if (permissaoAtualizada.getNome() != null) {
                permissao.setNome(permissaoAtualizada.getNome());
            }
            if (permissaoAtualizada.getDescricao() != null) {
                permissao.setDescricao(permissaoAtualizada.getDescricao());
            }
            return permissaoRepository.save(permissao);
        }
        return null;
    }

    public void deletar(Integer id) {
        permissaoRepository.deleteById(id);
    }
}
