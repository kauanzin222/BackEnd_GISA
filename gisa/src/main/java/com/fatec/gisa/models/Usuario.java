package com.fatec.gisa.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_USUARIO")
public class Usuario {
    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "IDCADASTRO")
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(
        name = "USUARIO_PERFIL",
        joinColumns = @JoinColumn(name = "IDCADASTRO"),
        inverseJoinColumns = @JoinColumn(name = "IDPERFIL")
    )
    private Set<Perfil> perfis;

    @Column(name = "SENHA", nullable = false, length = 255)
    private String senha;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    
}
