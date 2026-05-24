package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByPessoaCpf(String cpf);
}
