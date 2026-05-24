package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Perfil;
import com.fatec.gisa.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    public ResponseEntity<Perfil> criar(@RequestBody Perfil perfil) {
        Perfil novoPerfil = perfilService.criar(perfil);
        return ResponseEntity.ok(novoPerfil);
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        List<Perfil> perfis = perfilService.listarTodos();
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Perfil>> buscarPorId(@PathVariable Integer id) {
        Optional<Perfil> perfil = perfilService.buscarPorId(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Perfil> buscarPorNome(@PathVariable String nome) {
        Perfil perfil = perfilService.buscarPorNome(nome);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar(@PathVariable Integer id, @RequestBody Perfil perfilAtualizado) {
        Perfil perfil = perfilService.atualizar(id, perfilAtualizado);
        if (perfil != null) {
            return ResponseEntity.ok(perfil);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        perfilService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
