package com.fatec.gisa.repositories;

import com.fatec.gisa.models.Terapia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapiaRepository extends JpaRepository<Terapia, Integer> {
}
