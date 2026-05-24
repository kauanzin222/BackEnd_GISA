package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
    Responsavel findByCpf(String cpf);
}
