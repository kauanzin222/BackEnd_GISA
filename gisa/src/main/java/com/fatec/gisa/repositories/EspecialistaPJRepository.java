package com.fatec.gisa.repositories;

import com.fatec.gisa.models.EspecialistaPJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaPJRepository extends JpaRepository<EspecialistaPJ, Integer> {
    EspecialistaPJ findByCNPJ(String cnpj);
}
