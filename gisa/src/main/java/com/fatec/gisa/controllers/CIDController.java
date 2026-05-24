package com.fatec.gisa.controllers;

import com.fatec.gisa.models.CID;
import com.fatec.gisa.services.CIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cids")
public class CIDController {

    @Autowired
    private CIDService cidService;

    @PostMapping
    public ResponseEntity<CID> criar(@RequestBody CID cid) {
        CID novaCID = cidService.criar(cid);
        return ResponseEntity.ok(novaCID);
    }

    @GetMapping
    public ResponseEntity<List<CID>> listarTodas() {
        List<CID> cids = cidService.listarTodas();
        return ResponseEntity.ok(cids);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CID>> buscarPorId(@PathVariable Integer id) {
        Optional<CID> cid = cidService.buscarPorId(id);
        return ResponseEntity.ok(cid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CID> atualizar(@PathVariable Integer id, @RequestBody CID cidAtualizada) {
        CID cid = cidService.atualizar(id, cidAtualizada);
        if (cid != null) {
            return ResponseEntity.ok(cid);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cidService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
