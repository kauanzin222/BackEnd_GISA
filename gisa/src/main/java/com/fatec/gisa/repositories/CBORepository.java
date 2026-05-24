package com.fatec.gisa.repositories;

import com.fatec.gisa.models.CBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CBORepository extends JpaRepository<CBO, Integer> {
}
