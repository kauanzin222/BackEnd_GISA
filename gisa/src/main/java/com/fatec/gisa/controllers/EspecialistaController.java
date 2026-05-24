package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Especialista;
import com.fatec.gisa.services.EspecialistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialistas")
public class EspecialistaController {

    @Autowired
    private EspecialistaService especialistaService;

    @PostMapping
    public ResponseEntity<Especialista> criar(@RequestBody Especialista especialista) {
        Especialista novoEspecialista = especialistaService.criar(especialista);
        return ResponseEntity.ok(novoEspecialista);
    }

    @GetMapping
    public ResponseEntity<List<Especialista>> listarTodos() {
        List<Especialista> especialistas = especialistaService.listarTodos();
        return ResponseEntity.ok(especialistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Especialista>> buscarPorId(@PathVariable Integer id) {
        Optional<Especialista> especialista = especialistaService.buscarPorId(id);
        return ResponseEntity.ok(especialista);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Especialista> buscarPorCpf(@PathVariable String cpf) {
        Especialista especialista = especialistaService.buscarPorCpf(cpf);
        if (especialista != null) {
            return ResponseEntity.ok(especialista);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialista> atualizar(@PathVariable Integer id, @RequestBody Especialista especialistaAtualizado) {
        Especialista especialista = especialistaService.atualizar(id, especialistaAtualizado);
        if (especialista != null) {
            return ResponseEntity.ok(especialista);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        especialistaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
