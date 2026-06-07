package com.fatec.gisa.controllers;

import com.fatec.gisa.dtos.ProfissionalCadastroDTO;
import com.fatec.gisa.dtos.ProfissionalDetailDTO;
import com.fatec.gisa.dtos.ProfissionalSummaryDTO;
import com.fatec.gisa.models.Profissional;
import com.fatec.gisa.services.ProfissionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    // ── GET: LISTAGEM GERAL ──
    @GetMapping
    public ResponseEntity<Page<ProfissionalSummaryDTO>> listarTodos(Pageable pageable) {
        Page<ProfissionalSummaryDTO> profissionais = profissionalService.listarTodos(pageable);
        return ResponseEntity.ok(profissionais);
    }

    // ── GET: DETALHE POR ID ──
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDetailDTO> buscarPorId(@PathVariable Integer id) {
        ProfissionalDetailDTO profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    // ── GET: BUSCA POR FILTRO DE NOME ──
    @GetMapping("/buscar")
    public ResponseEntity<List<ProfissionalSummaryDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProfissionalSummaryDTO> profissionais = profissionalService.buscarPorNome(nome);
        return ResponseEntity.ok(profissionais);
    }

    // ── POST: CRIAÇÃO VIA DTO DO FORMULÁRIO ──
    @PostMapping
    public ResponseEntity<ProfissionalDetailDTO> criar(@RequestBody ProfissionalCadastroDTO cadastroDTO) {
        ProfissionalDetailDTO novoProfissional = profissionalService.criar(cadastroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfissional);
    }

    // ── PUT: ATUALIZAÇÃO VIA DTO DO FORMULÁRIO ──
    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDetailDTO> atualizar(@PathVariable Integer id,
            @RequestBody ProfissionalCadastroDTO cadastroDTO) {
        ProfissionalDetailDTO profissional = profissionalService.atualizar(id, cadastroDTO);
        if (profissional != null) {
            return ResponseEntity.ok(profissional);
        }
        return ResponseEntity.notFound().build();
    }

    // ── GET: CPF (MANTIDO PARA CONSULTAS INTERNAS) ──
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Profissional> buscarPorCpf(@PathVariable String cpf) {
        Profissional profissional = profissionalService.buscarPorCpf(cpf);
        if (profissional != null) {
            return ResponseEntity.ok(profissional);
        }
        return ResponseEntity.notFound().build();
    }

    // ── DELETE: DISPARA A INATIVAÇÃO LÓGICA NO SERVICE ──
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}