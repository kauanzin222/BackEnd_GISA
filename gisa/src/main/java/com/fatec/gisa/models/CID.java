package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CID")
public class CID {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cid_seq")
    @SequenceGenerator(name = "cid_seq", sequenceName = "SEQ_CID", allocationSize = 1)
    @Column(name = "CODIGO_CID")
    private Integer codigoCID;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
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
