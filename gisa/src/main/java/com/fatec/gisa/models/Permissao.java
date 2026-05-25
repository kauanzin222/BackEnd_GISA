package com.fatec.gisa.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tab_PERMISSAO")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissao_seq")
    @SequenceGenerator(name = "permissao_seq", sequenceName = "SEQ_PERMISSAO", allocationSize = 1)
    @Column(name = "IDPERMISSAO")
    private Integer idPermissao;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    // Getters and Setters
    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
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
