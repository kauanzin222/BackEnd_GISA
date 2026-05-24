package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "MODALIDADE")
public class Modalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modalidade_seq")
    @SequenceGenerator(name = "modalidade_seq", sequenceName = "SEQ_MODALIDADE", allocationSize = 1)
    @Column(name = "ID_MODALIDADE")
    private Integer idModalidade;

    @Column(name = "NOME", nullable = false, unique = true, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", length = 255)
    private String descricao;

    // Getters and Setters
    public Integer getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Integer idModalidade) {
        this.idModalidade = idModalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
