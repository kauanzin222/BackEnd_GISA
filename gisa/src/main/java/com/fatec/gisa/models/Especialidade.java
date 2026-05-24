package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ESPECIALIDADE")
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade_seq")
    @SequenceGenerator(name = "especialidade_seq", sequenceName = "SEQ_ESPECIALIDADE", allocationSize = 1)
    @Column(name = "ID_ESPECIALIDADE")
    private Integer idEspecialidade;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "DESCRICAO", length = 255)
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
