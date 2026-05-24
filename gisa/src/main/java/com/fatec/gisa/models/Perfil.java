package com.fatec.gisa.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "PERFIL")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_seq")
    @SequenceGenerator(name = "perfil_seq", sequenceName = "SEQ_PERFIL", allocationSize = 1)
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;

    @Column(name = "NOME", nullable = false, unique = true, length = 100)
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "PERFIL_PERMISSAO",
        joinColumns = @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL"),
        inverseJoinColumns = @JoinColumn(name = "ID_PERMISSAO", referencedColumnName = "ID_PERMISSAO")
    )
    private List<Permissao> permissoes;

    // Getters and Setters
    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}
