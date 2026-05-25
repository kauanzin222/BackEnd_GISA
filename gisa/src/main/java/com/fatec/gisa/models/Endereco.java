package com.fatec.gisa.models;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "TAB_ENDERECO")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
    @SequenceGenerator(name = "endereco_seq", sequenceName = "SEQ_ENDERECO", allocationSize = 1)
    @Column(name = "IDENDERECO")
    private Integer idEndereco;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "CIDADE")
    private String cidade;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @ManyToMany
    @JoinTable(
        name = "ENDERECO_PESSOA",
        joinColumns = @JoinColumn(name = "IDENDERECO"),
        inverseJoinColumns = @JoinColumn(name = "IDCADASTRO")
    )
    private Set<Pessoa> moradores;

    // Getters and Setters
    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

	public Set<Pessoa> getMoradores() {
		return moradores;
	}

	public void setMoradores(Set<Pessoa> moradores) {
		this.moradores = moradores;
	} 
}
