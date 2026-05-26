package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_CBO")
public class CBO {
    @Id
    @Column(name = "CODIGOCBO")
    private Integer codigoCBO;

    @Column(name = "TITULOCBO")
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
