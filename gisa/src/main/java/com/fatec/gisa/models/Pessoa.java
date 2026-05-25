package com.fatec.gisa.models;

import java.time.LocalDate;
import java.util.Set;

import com.fatec.gisa.enums.EstadoCivil;
import com.fatec.gisa.enums.StatusCadastro;
import jakarta.persistence.*;

@Entity
@Table(name = "TAB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_seq")
    @SequenceGenerator(name = "pessoa_seq", sequenceName = "SEQ_PESSOA", allocationSize = 1)
    @Column(name = "IDCADASTRO")
    protected Integer idCadastro;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATANASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "SEXO")
    private char sexo;

    @OneToMany(mappedBy = "moradores")
    private Set<Endereco> enderecos;

    @Column(name = "CELULAR")
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADOCIVIL")
    private EstadoCivil estadoCivil;

    @Column(name = "NUMCNS")
    private String numCNS;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUSCADASTRO")
    private StatusCadastro statusCadastro;

    // Getters and Setters
    public Integer getIdCadastro() {
        return idCadastro;
    }

    public void setIdCadastro(Integer idCadastro) {
        this.idCadastro = idCadastro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNumCNS() {
        return numCNS;
    }

    public void setNumCNS(String numCNS) {
        this.numCNS = numCNS;
    }

    public StatusCadastro getStatusCadastro() {
        return statusCadastro;
    }

    public void setStatusCadastro(StatusCadastro statusCadastro) {
        this.statusCadastro = statusCadastro;
    }
}
