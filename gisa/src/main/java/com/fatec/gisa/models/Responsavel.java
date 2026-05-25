package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_RESPONSAVEL")
public class Responsavel extends Pessoa {
    @Column(name = "OCUPACAO")
    private String ocupacao;

    // Getters and Setters
    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }
}
