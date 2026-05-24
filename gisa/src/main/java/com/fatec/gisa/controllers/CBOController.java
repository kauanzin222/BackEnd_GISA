package com.fatec.gisa.controllers;

import com.fatec.gisa.models.CBO;
import com.fatec.gisa.services.CBOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cbos")
public class CBOController {

    @Autowired
    private CBOService cboService;

    @PostMapping
    public ResponseEntity<CBO> criar(@RequestBody CBO cbo) {
        CBO novoCBO = cboService.criar(cbo);
        return ResponseEntity.ok(novoCBO);
    }

    @GetMapping
    public ResponseEntity<List<CBO>> listarTodas() {
        List<CBO> cbos = cboService.listarTodas();
        return ResponseEntity.ok(cbos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CBO>> buscarPorId(@PathVariable Integer id) {
        Optional<CBO> cbo = cboService.buscarPorId(id);
        return ResponseEntity.ok(cbo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CBO> atualizar(@PathVariable Integer id, @RequestBody CBO cboAtualizada) {
        CBO cbo = cboService.atualizar(id, cboAtualizada);
        if (cbo != null) {
            return ResponseEntity.ok(cbo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cboService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
