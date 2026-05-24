package com.fatec.gisa.services;

import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional criar(Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public Optional<Profissional> buscarPorId(Integer id) {
        return profissionalRepository.findById(id);
    }

    public Profissional buscarPorCpf(String cpf) {
        return profissionalRepository.findByCpf(cpf);
    }

    public Profissional atualizar(Integer id, Profissional profissionalAtualizado) {
        Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);
        if (profissionalOptional.isPresent()) {
            Profissional profissional = profissionalOptional.get();
            if (profissionalAtualizado.getNome() != null) {
                profissional.setNome(profissionalAtualizado.getNome());
            }
            if (profissionalAtualizado.getCargo() != null) {
                profissional.setCargo(profissionalAtualizado.getCargo());
            }
            if (profissionalAtualizado.getCelular() != null) {
                profissional.setCelular(profissionalAtualizado.getCelular());
            }
            return profissionalRepository.save(profissional);
        }
        return null;
    }

    public void deletar(Integer id) {
        profissionalRepository.deleteById(id);
    }
}
