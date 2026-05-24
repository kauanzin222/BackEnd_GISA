package com.fatec.gisa.services;

import com.fatec.gisa.models.Usuario;
import com.fatec.gisa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarioRepository.findByPessoaCpf(cpf);
    }

    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuarioAtualizado.getSenha() != null) {
                usuario.setSenha(usuarioAtualizado.getSenha());
            }
            if (usuarioAtualizado.getPerfil() != null) {
                usuario.setPerfil(usuarioAtualizado.getPerfil());
            }
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
