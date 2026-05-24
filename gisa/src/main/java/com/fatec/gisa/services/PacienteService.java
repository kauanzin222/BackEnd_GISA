package com.fatec.gisa.services;

import com.fatec.gisa.models.Paciente;
import com.fatec.gisa.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente criar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Integer id) {
        return pacienteRepository.findById(id);
    }

    public Paciente buscarPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Paciente atualizar(Integer id, Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            if (pacienteAtualizado.getNome() != null) {
                paciente.setNome(pacienteAtualizado.getNome());
            }
            if (pacienteAtualizado.getStatusPaciente() != null) {
                paciente.setStatusPaciente(pacienteAtualizado.getStatusPaciente());
            }
            if (pacienteAtualizado.getTipoEntrada() != null) {
                paciente.setTipoEntrada(pacienteAtualizado.getTipoEntrada());
            }
            if (pacienteAtualizado.getEscola() != null) {
                paciente.setEscola(pacienteAtualizado.getEscola());
            }
            if (pacienteAtualizado.getProntuario() != null) {
                paciente.setProntuario(pacienteAtualizado.getProntuario());
            }
            return pacienteRepository.save(paciente);
        }
        return null;
    }

    public void deletar(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
