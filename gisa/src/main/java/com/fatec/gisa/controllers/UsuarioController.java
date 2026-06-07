package com.fatec.gisa.controllers;

import com.fatec.gisa.dtos.LoginRequestDTO;
import com.fatec.gisa.dtos.LoginResponseDTO;
import com.fatec.gisa.models.Usuario;
import com.fatec.gisa.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://eduardofproenca.github.io")

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ── ENDPOINT CRÍTICO: AUTENTICAÇÃO DO LOGIN ──
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // 1. Busca o usuário pelo ID numérico enviado pelo front-end
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(loginRequest.id());

        // 2. Se o usuário não existir no banco, barra imediatamente (401 Unauthorized)
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Usuario usuario = usuarioOptional.get();

        // 3. Verifica se a senha informada é EXATAMENTE igual à senha do banco
        if (!usuario.getSenha().equals(loginRequest.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 4. Captura o nome real da Pessoa associada (se existir) para personalizar a
        // sessão
        String nomeProfissional = "Profissional";
        if (usuario.getPessoa() != null) {
            nomeProfissional = usuario.getPessoa().getNome();
        }

        // 5. Monta a resposta limpa e segura (200 OK)
        LoginResponseDTO responseDTO = new LoginResponseDTO(usuario.getId(), nomeProfissional);
        return ResponseEntity.ok(responseDTO);
    }

    // ── OUTROS ENDPOINTS EXISTENTES ──

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Usuario> buscarPorCpf(@PathVariable String cpf) {
        Usuario usuario = usuarioService.buscarPorCpf(cpf);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = usuarioService.atualizar(id, usuarioAtualizado);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}