package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_CARGO")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_seq")
    @SequenceGenerator(name = "cargo_seq", sequenceName = "SEQ_CARGO", allocationSize = 1)
    @Column(name = "IDCARGO")
    private Integer idCargo;

    @ManyToOne
    @JoinColumn(name = "CODIGOCBO")
    private CBO cbo;

    @Column(name = "NOMECARGO", nullable = false, length = 100)
    private String nomeCargo;

    // Getters and Setters
    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public CBO getCbo() {
        return cbo;
    }

    public void setCbo(CBO cbo) {
        this.cbo = cbo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}
