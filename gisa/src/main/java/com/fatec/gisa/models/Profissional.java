package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PROFISSIONAL")
public class Profissional extends Pessoa {
    @ManyToOne
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO")
    private Cargo cargo;

    // Getters and Setters
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
