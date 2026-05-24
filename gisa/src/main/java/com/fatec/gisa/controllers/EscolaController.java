package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Escola;
import com.fatec.gisa.services.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    @PostMapping
    public ResponseEntity<Escola> criar(@RequestBody Escola escola) {
        Escola novaEscola = escolaService.criar(escola);
        return ResponseEntity.ok(novaEscola);
    }

    @GetMapping
    public ResponseEntity<List<Escola>> listarTodas() {
        List<Escola> escolas = escolaService.listarTodas();
        return ResponseEntity.ok(escolas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Escola>> buscarPorId(@PathVariable Integer id) {
        Optional<Escola> escola = escolaService.buscarPorId(id);
        return ResponseEntity.ok(escola);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escola> atualizar(@PathVariable Integer id, @RequestBody Escola escolaAtualizada) {
        Escola escola = escolaService.atualizar(id, escolaAtualizada);
        if (escola != null) {
            return ResponseEntity.ok(escola);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        escolaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
