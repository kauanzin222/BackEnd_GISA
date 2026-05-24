package com.fatec.gisa.controllers;

import com.fatec.gisa.models.EspecialistaPJ;
import com.fatec.gisa.services.EspecialistaPJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/especialistas-pj")
public class EspecialistaPJController {

    @Autowired
    private EspecialistaPJService especialistaPJService;

    @PostMapping
    public ResponseEntity<EspecialistaPJ> criar(@RequestBody EspecialistaPJ especialistaPJ) {
        EspecialistaPJ novoEspecialistaPJ = especialistaPJService.criar(especialistaPJ);
        return ResponseEntity.ok(novoEspecialistaPJ);
    }

    @GetMapping
    public ResponseEntity<List<EspecialistaPJ>> listarTodos() {
        List<EspecialistaPJ> especialistasPJ = especialistaPJService.listarTodos();
        return ResponseEntity.ok(especialistasPJ);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<EspecialistaPJ>> buscarPorId(@PathVariable Integer id) {
        Optional<EspecialistaPJ> especialistaPJ = especialistaPJService.buscarPorId(id);
        return ResponseEntity.ok(especialistaPJ);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EspecialistaPJ> buscarPorCNPJ(@PathVariable String cnpj) {
        EspecialistaPJ especialistaPJ = especialistaPJService.buscarPorCNPJ(cnpj);
        if (especialistaPJ != null) {
            return ResponseEntity.ok(especialistaPJ);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialistaPJ> atualizar(@PathVariable Integer id, @RequestBody EspecialistaPJ especialistaPJAtualizado) {
        EspecialistaPJ especialistaPJ = especialistaPJService.atualizar(id, especialistaPJAtualizado);
        if (especialistaPJ != null) {
            return ResponseEntity.ok(especialistaPJ);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        especialistaPJService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
