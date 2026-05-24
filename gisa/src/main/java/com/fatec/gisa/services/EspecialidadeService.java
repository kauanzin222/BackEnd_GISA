package com.fatec.gisa.services;

import com.fatec.gisa.models.Especialidade;
import com.fatec.gisa.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public Especialidade criar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    public List<Especialidade> listarTodas() {
        return especialidadeRepository.findAll();
    }

    public Optional<Especialidade> buscarPorId(Integer id) {
        return especialidadeRepository.findById(id);
    }

    public Especialidade atualizar(Integer id, Especialidade especialidadeAtualizada) {
        Optional<Especialidade> especialidadeOptional = especialidadeRepository.findById(id);
        if (especialidadeOptional.isPresent()) {
            Especialidade especialidade = especialidadeOptional.get();
            if (especialidadeAtualizada.getNome() != null) {
                especialidade.setNome(especialidadeAtualizada.getNome());
            }
            if (especialidadeAtualizada.getDescricao() != null) {
                especialidade.setDescricao(especialidadeAtualizada.getDescricao());
            }
            return especialidadeRepository.save(especialidade);
        }
        return null;
    }

    public void deletar(Integer id) {
        especialidadeRepository.deleteById(id);
    }
}
