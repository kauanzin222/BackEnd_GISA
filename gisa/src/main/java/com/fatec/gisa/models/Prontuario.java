package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRONTUARIO")
public class Prontuario {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "IDPACIENTE")
    private Paciente paciente;

    @Column(name = "ALERGIAS")
    private String alergias;

    @Column(name = "COMORBIDADE")
    private String comorbidade;

    @Column(name = "MOBILIDADE")
    private String mobilidade;

    // Getters and Setters
    public Integer getIdProntuario() {
        return idProntuario;
    }

    public void setIdProntuario(Integer idProntuario) {
        this.idProntuario = idProntuario;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getComorbidade() {
        return comorbidade;
    }

    public void setComorbidade(String comorbidade) {
        this.comorbidade = comorbidade;
    }

    public String getMobilidade() {
        return mobilidade;
    }

    public void setMobilidade(String mobilidade) {
        this.mobilidade = mobilidade;
    }
}
