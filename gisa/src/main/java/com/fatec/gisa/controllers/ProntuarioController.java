package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Prontuario;
import com.fatec.gisa.services.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<Prontuario> criar(@RequestBody Prontuario prontuario) {
        Prontuario novoProntuario = prontuarioService.criar(prontuario);
        return ResponseEntity.ok(novoProntuario);
    }

    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        List<Prontuario> prontuarios = prontuarioService.listarTodos();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prontuario>> buscarPorId(@PathVariable Integer id) {
        Optional<Prontuario> prontuario = prontuarioService.buscarPorId(id);
        return ResponseEntity.ok(prontuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Integer id, @RequestBody Prontuario prontuarioAtualizado) {
        Prontuario prontuario = prontuarioService.atualizar(id, prontuarioAtualizado);
        if (prontuario != null) {
            return ResponseEntity.ok(prontuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        prontuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
