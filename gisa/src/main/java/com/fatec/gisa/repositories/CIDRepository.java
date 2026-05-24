package com.fatec.gisa.repositories;

import com.fatec.gisa.models.CID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CIDRepository extends JpaRepository<CID, Integer> {
}
