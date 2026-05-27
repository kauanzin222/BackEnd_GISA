package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Especialidade;
import com.fatec.gisa.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping
    public ResponseEntity<Especialidade> criar(@RequestBody Especialidade especialidade) {
        Especialidade novaEspecialidade = especialidadeService.criar(especialidade);
        return ResponseEntity.ok(novaEspecialidade);
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> listarTodas() {
        List<Especialidade> especialidades = especialidadeService.listarTodas();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Especialidade>> buscarPorId(@PathVariable Integer id) {
        Optional<Especialidade> especialidade = especialidadeService.buscarPorId(id);
        return ResponseEntity.ok(especialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Integer id, @RequestBody Especialidade especialidadeAtualizada) {
        Especialidade especialidade = especialidadeService.atualizar(id, especialidadeAtualizada);
        if (especialidade != null) {
            return ResponseEntity.ok(especialidade);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        especialidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
