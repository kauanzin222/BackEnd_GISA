package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Responsavel;
import com.fatec.gisa.services.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {

    @Autowired
    private ResponsavelService responsavelService;

    @PostMapping
    public ResponseEntity<Responsavel> criar(@RequestBody Responsavel responsavel) {
        Responsavel novoResponsavel = responsavelService.criar(responsavel);
        return ResponseEntity.ok(novoResponsavel);
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> listarTodos() {
        List<Responsavel> responsaveis = responsavelService.listarTodos();
        return ResponseEntity.ok(responsaveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Responsavel>> buscarPorId(@PathVariable Integer id) {
        Optional<Responsavel> responsavel = responsavelService.buscarPorId(id);
        return ResponseEntity.ok(responsavel);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Responsavel> buscarPorCpf(@PathVariable String cpf) {
        Responsavel responsavel = responsavelService.buscarPorCpf(cpf);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> atualizar(@PathVariable Integer id, @RequestBody Responsavel responsavelAtualizado) {
        Responsavel responsavel = responsavelService.atualizar(id, responsavelAtualizado);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        responsavelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
