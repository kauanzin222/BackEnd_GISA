package com.fatec.gisa.services;

import com.fatec.gisa.models.Cargo;
import com.fatec.gisa.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public Cargo criar(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    public List<Cargo> listarTodos() {
        return cargoRepository.findAll();
    }

    public Optional<Cargo> buscarPorId(Integer id) {
        return cargoRepository.findById(id);
    }

    public Cargo atualizar(Integer id, Cargo cargoAtualizado) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);
        if (cargoOptional.isPresent()) {
            Cargo cargo = cargoOptional.get();
            if (cargoAtualizado.getNomeCargo() != null) {
                cargo.setNomeCargo(cargoAtualizado.getNomeCargo());
            }
            if (cargoAtualizado.getCbo() != null) {
                cargo.setCbo(cargoAtualizado.getCbo());
            }
            return cargoRepository.save(cargo);
        }
        return null;
    }

    public void deletar(Integer id) {
        cargoRepository.deleteById(id);
    }
}
