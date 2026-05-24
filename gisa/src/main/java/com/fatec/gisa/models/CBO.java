package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CBO")
public class CBO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cbo_seq")
    @SequenceGenerator(name = "cbo_seq", sequenceName = "SEQ_CBO", allocationSize = 1)
    @Column(name = "CODIGO_CBO")
    private Integer codigoCBO;

    @Column(name = "TITULO_CBO", nullable = false, length = 255)
    private String tituloCBO;

    // Getters and Setters
    public Integer getCodigoCBO() {
        return codigoCBO;
    }

    public void setCodigoCBO(Integer codigoCBO) {
        this.codigoCBO = codigoCBO;
    }

    public String getTituloCBO() {
        return tituloCBO;
    }

    public void setTituloCBO(String tituloCBO) {
        this.tituloCBO = tituloCBO;
    }
}
