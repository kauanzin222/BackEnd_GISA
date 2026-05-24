package com.fatec.gisa.services;

import com.fatec.gisa.models.Pessoa;
import com.fatec.gisa.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Integer id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public Pessoa atualizar(Integer id, Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            if (pessoaAtualizada.getNome() != null) {
                pessoa.setNome(pessoaAtualizada.getNome());
            }
            if (pessoaAtualizada.getCpf() != null) {
                pessoa.setCpf(pessoaAtualizada.getCpf());
            }
            if (pessoaAtualizada.getDataNascimento() != null) {
                pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
            }
            if (pessoaAtualizada.getCelular() != null) {
                pessoa.setCelular(pessoaAtualizada.getCelular());
            }
            if (pessoaAtualizada.getEstadoCivil() != null) {
                pessoa.setEstadoCivil(pessoaAtualizada.getEstadoCivil());
            }
            if (pessoaAtualizada.getStatusCadastro() != null) {
                pessoa.setStatusCadastro(pessoaAtualizada.getStatusCadastro());
            }
            return pessoaRepository.save(pessoa);
        }
        return null;
    }

    public void deletar(Integer id) {
        pessoaRepository.deleteById(id);
    }
}
