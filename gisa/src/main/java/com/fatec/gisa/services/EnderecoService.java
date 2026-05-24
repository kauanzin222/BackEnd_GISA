package com.fatec.gisa.services;

import com.fatec.gisa.models.Endereco;
import com.fatec.gisa.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco criar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Integer id) {
        return enderecoRepository.findById(id);
    }

    public Endereco atualizar(Integer id, Endereco enderecoAtualizado) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            if (enderecoAtualizado.getRua() != null) {
                endereco.setRua(enderecoAtualizado.getRua());
            }
            if (enderecoAtualizado.getCidade() != null) {
                endereco.setCidade(enderecoAtualizado.getCidade());
            }
            if (enderecoAtualizado.getBairro() != null) {
                endereco.setBairro(enderecoAtualizado.getBairro());
            }
            if (enderecoAtualizado.getEstado() != null) {
                endereco.setEstado(enderecoAtualizado.getEstado());
            }
            if (enderecoAtualizado.getCep() != null) {
                endereco.setCep(enderecoAtualizado.getCep());
            }
            if (enderecoAtualizado.getNumero() != null) {
                endereco.setNumero(enderecoAtualizado.getNumero());
            }
            if (enderecoAtualizado.getComplemento() != null) {
                endereco.setComplemento(enderecoAtualizado.getComplemento());
            }
            return enderecoRepository.save(endereco);
        }
        return null;
    }

    public void deletar(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
