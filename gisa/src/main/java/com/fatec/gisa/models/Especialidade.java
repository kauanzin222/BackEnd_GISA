package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_ESPECIALIDADE")
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade_seq")
    @SequenceGenerator(name = "especialidade_seq", sequenceName = "SEQ_ESPECIALIDADE", allocationSize = 1)
    @Column(name = "IDESPECIALIDADE")
    private Integer idEspecialidade;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    // Getters and Setters
    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
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
