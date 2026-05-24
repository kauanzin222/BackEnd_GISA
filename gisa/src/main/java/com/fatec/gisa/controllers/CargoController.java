package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Cargo;
import com.fatec.gisa.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<Cargo> criar(@RequestBody Cargo cargo) {
        Cargo novoCargo = cargoService.criar(cargo);
        return ResponseEntity.ok(novoCargo);
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> listarTodos() {
        List<Cargo> cargos = cargoService.listarTodos();
        return ResponseEntity.ok(cargos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cargo>> buscarPorId(@PathVariable Integer id) {
        Optional<Cargo> cargo = cargoService.buscarPorId(id);
        return ResponseEntity.ok(cargo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable Integer id, @RequestBody Cargo cargoAtualizado) {
        Cargo cargo = cargoService.atualizar(id, cargoAtualizado);
        if (cargo != null) {
            return ResponseEntity.ok(cargo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        cargoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
