package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Terapia;
import com.fatec.gisa.services.TerapiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/terapias")
public class TerapiaController {

    @Autowired
    private TerapiaService terapiaService;

    @PostMapping
    public ResponseEntity<Terapia> criar(@RequestBody Terapia terapia) {
        Terapia novaTerapia = terapiaService.criar(terapia);
        return ResponseEntity.ok(novaTerapia);
    }

    @GetMapping
    public ResponseEntity<List<Terapia>> listarTodas() {
        List<Terapia> terapias = terapiaService.listarTodas();
        return ResponseEntity.ok(terapias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Terapia>> buscarPorId(@PathVariable Integer id) {
        Optional<Terapia> terapia = terapiaService.buscarPorId(id);
        return ResponseEntity.ok(terapia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terapia> atualizar(@PathVariable Integer id, @RequestBody Terapia terapiaAtualizada) {
        Terapia terapia = terapiaService.atualizar(id, terapiaAtualizada);
        if (terapia != null) {
            return ResponseEntity.ok(terapia);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        terapiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
