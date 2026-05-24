package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ESPECIALISTA_PJ")
public class EspecialistaPJ extends Especialista {
    @Column(name = "CNPJ", unique = true, length = 20)
    private String CNPJ;

    @Column(name = "RAZAO_SOCIAL", length = 150)
    private String razaoSocial;

    @Column(name = "NOME_FANTASIA", length = 150)
    private String nomeFantasia;

    @Column(name = "INSCRICAO_ESTADUAL", length = 20)
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
