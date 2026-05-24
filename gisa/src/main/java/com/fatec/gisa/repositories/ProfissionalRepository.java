package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
    Profissional findByCpf(String cpf);
}
