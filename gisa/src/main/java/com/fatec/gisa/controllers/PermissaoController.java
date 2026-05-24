package com.fatec.gisa.controllers;

import com.fatec.gisa.models.Permissao;
import com.fatec.gisa.services.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @PostMapping
    public ResponseEntity<Permissao> criar(@RequestBody Permissao permissao) {
        Permissao novaPermissao = permissaoService.criar(permissao);
        return ResponseEntity.ok(novaPermissao);
    }

    @GetMapping
    public ResponseEntity<List<Permissao>> listarTodas() {
        List<Permissao> permissoes = permissaoService.listarTodas();
        return ResponseEntity.ok(permissoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Permissao>> buscarPorId(@PathVariable Integer id) {
        Optional<Permissao> permissao = permissaoService.buscarPorId(id);
        return ResponseEntity.ok(permissao);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Permissao> buscarPorNome(@PathVariable String nome) {
        Permissao permissao = permissaoService.buscarPorNome(nome);
        if (permissao != null) {
            return ResponseEntity.ok(permissao);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permissao> atualizar(@PathVariable Integer id, @RequestBody Permissao permissaoAtualizada) {
        Permissao permissao = permissaoService.atualizar(id, permissaoAtualizada);
        if (permissao != null) {
            return ResponseEntity.ok(permissao);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        permissaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
