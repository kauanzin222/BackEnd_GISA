package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_CID")
public class CID {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cid_seq")
    @SequenceGenerator(name = "cid_seq", sequenceName = "SEQ_CID", allocationSize = 1)
    @Column(name = "CODIGOCID")
    private Integer codigoCID;

    @Column(name = "DESCRICAO")
    private String descricao;

    // Getters and Setters
    public Integer getCodigoCID() {
        return codigoCID;
    }

    public void setCodigoCID(Integer codigoCID) {
        this.codigoCID = codigoCID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
