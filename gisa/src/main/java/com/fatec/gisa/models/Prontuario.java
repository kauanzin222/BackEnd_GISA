package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRONTUARIO")
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prontuario_seq")
    @SequenceGenerator(name = "prontuario_seq", sequenceName = "SEQ_PRONTUARIO", allocationSize = 1)
    @Column(name = "ID_PRONTUARIO")
    private Integer idProntuario;

    @Column(name = "ALERGIAS", length = 255)
    private String alergias;

    @Column(name = "COMORBIDADE", length = 255)
    private String comorbidade;

    @Column(name = "MOBILIDADE", length = 100)
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
