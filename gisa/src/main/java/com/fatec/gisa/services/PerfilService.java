package com.fatec.gisa.services;

import com.fatec.gisa.models.Perfil;
import com.fatec.gisa.repositories.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil criar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> buscarPorId(Integer id) {
        return perfilRepository.findById(id);
    }

    public Perfil buscarPorNome(String nome) {
        return perfilRepository.findByNome(nome);
    }

    public Perfil atualizar(Integer id, Perfil perfilAtualizado) {
        Optional<Perfil> perfilOptional = perfilRepository.findById(id);
        if (perfilOptional.isPresent()) {
            Perfil perfil = perfilOptional.get();
            if (perfilAtualizado.getNome() != null) {
                perfil.setNome(perfilAtualizado.getNome());
            }
            if (perfilAtualizado.getPermissoes() != null) {
                perfil.setPermissoes(perfilAtualizado.getPermissoes());
            }
            return perfilRepository.save(perfil);
        }
        return null;
    }

    public void deletar(Integer id) {
        perfilRepository.deleteById(id);
    }
}
