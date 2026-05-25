package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_ESPECIALISTAPJ")
public class EspecialistaPJ extends Especialista {
    @Column(name = "CNPJ")
    private String CNPJ;

    @Column(name = "RAZAOSOCIAL")
    private String razaoSocial;

    @Column(name = "NOMEFANTASIA")
    private String nomeFantasia;

    @Column(name = "INSCRICAOESTADUAL")
    private String inscricaoEstadual;

    // Getters and Setters
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
