package com.fatec.gisa.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tab_PERFIL")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_seq")
    @SequenceGenerator(name = "perfil_seq", sequenceName = "SEQ_PERFIL", allocationSize = 1)
    @Column(name = "IDPERFIL")
    private Integer idPerfil;

    @Column(name = "NOME")
    private String nome;

    @ManyToMany
    @JoinTable(
        name = "PERFIL_PERMISSAO",
        joinColumns = @JoinColumn(name = "IDPERFIL"),
        inverseJoinColumns = @JoinColumn(name = "IDPERMISSAO")
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
