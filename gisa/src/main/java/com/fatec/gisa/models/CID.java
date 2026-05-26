package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_CID")
public class CID {
    @Id
    @Column(name = "CODIGOCID")
    private String codigoCID;

    @Column(name = "DESCRICAO")
    private String descricao;

    // Getters and Setters
    public String getCodigoCID() {
        return codigoCID;
    }

    public void setCodigoCID(String codigoCID) {
        this.codigoCID = codigoCID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
