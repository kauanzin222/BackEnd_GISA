package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<Profissional> criar(@RequestBody Profissional profissional) {
        Profissional novoProfissional = profissionalService.criar(profissional);
        return ResponseEntity.ok(novoProfissional);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listarTodos() {
        List<Profissional> profissionais = profissionalService.listarTodos();
        return ResponseEntity.ok(profissionais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Profissional>> buscarPorId(@PathVariable Integer id) {
        Optional<Profissional> profissional = profissionalService.buscarPorId(id);
        return ResponseEntity.ok(profissional);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Profissional> buscarPorCpf(@PathVariable String cpf) {
        Profissional profissional = profissionalService.buscarPorCpf(cpf);
        if (profissional != null) {
            return ResponseEntity.ok(profissional);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@PathVariable Integer id, @RequestBody Profissional profissionalAtualizado) {
        Profissional profissional = profissionalService.atualizar(id, profissionalAtualizado);
        if (profissional != null) {
            return ResponseEntity.ok(profissional);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
