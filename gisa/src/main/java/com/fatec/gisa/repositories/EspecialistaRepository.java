package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Especialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Integer> {
    Especialista findByCpf(String cpf);
}
