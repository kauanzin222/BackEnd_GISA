package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_PROFISSIONAL")
@PrimaryKeyJoinColumn(name = "IDPROFISSIONAL")
public class Profissional extends Pessoa {
    @ManyToOne
    @JoinColumn(name = "IDCARGO")
    private Cargo cargo;

    // Getters and Setters
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
