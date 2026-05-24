package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Modalidade;
import com.fatec.gisa.services.ModalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modalidades")
public class ModalidadeController {

    @Autowired
    private ModalidadeService modalidadeService;

    @PostMapping
    public ResponseEntity<Modalidade> criar(@RequestBody Modalidade modalidade) {
        Modalidade novaModalidade = modalidadeService.criar(modalidade);
        return ResponseEntity.ok(novaModalidade);
    }

    @GetMapping
    public ResponseEntity<List<Modalidade>> listarTodas() {
        List<Modalidade> modalidades = modalidadeService.listarTodas();
        return ResponseEntity.ok(modalidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Modalidade>> buscarPorId(@PathVariable Integer id) {
        Optional<Modalidade> modalidade = modalidadeService.buscarPorId(id);
        return ResponseEntity.ok(modalidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modalidade> atualizar(@PathVariable Integer id, @RequestBody Modalidade modalidadeAtualizada) {
        Modalidade modalidade = modalidadeService.atualizar(id, modalidadeAtualizada);
        if (modalidade != null) {
            return ResponseEntity.ok(modalidade);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        modalidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
