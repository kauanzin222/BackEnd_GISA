package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Profissional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    Profissional findByCpf(String cpf);
    
    org.springframework.data.domain.Page<Profissional> findByStatus(String status, org.springframework.data.domain.Pageable pageable);
    
    List<Profissional> findByNomeContainingIgnoreCase(String nome);
}
