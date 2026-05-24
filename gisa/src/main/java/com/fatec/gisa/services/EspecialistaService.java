package com.fatec.gisa.services;

import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.repositories.EspecialistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialistaService {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    public Especialista criar(Especialista especialista) {
        return especialistaRepository.save(especialista);
    }

    public List<Especialista> listarTodos() {
        return especialistaRepository.findAll();
    }

    public Optional<Especialista> buscarPorId(Integer id) {
        return especialistaRepository.findById(id);
    }

    public Especialista buscarPorCpf(String cpf) {
        return especialistaRepository.findByCpf(cpf);
    }

    public Especialista atualizar(Integer id, Especialista especialistaAtualizado) {
        Optional<Especialista> especialistaOptional = especialistaRepository.findById(id);
        if (especialistaOptional.isPresent()) {
            Especialista especialista = especialistaOptional.get();
            if (especialistaAtualizado.getNome() != null) {
                especialista.setNome(especialistaAtualizado.getNome());
            }
            if (especialistaAtualizado.getRegistroConselho() != null) {
                especialista.setRegistroConselho(especialistaAtualizado.getRegistroConselho());
            }
            if (especialistaAtualizado.getEspecialidades() != null) {
                especialista.setEspecialidades(especialistaAtualizado.getEspecialidades());
            }
            if (especialistaAtualizado.getCargo() != null) {
                especialista.setCargo(especialistaAtualizado.getCargo());
            }
            return especialistaRepository.save(especialista);
        }
        return null;
    }

    public void deletar(Integer id) {
        especialistaRepository.deleteById(id);
    }
}
