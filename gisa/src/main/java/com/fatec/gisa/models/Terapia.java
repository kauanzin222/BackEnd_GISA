package com.fatec.gisa.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fatec.gisa.enums.StatusTerapia;
import jakarta.persistence.*;

@Entity
@Table(name = "tab_TERAPIA")
public class Terapia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "terapia_seq")
    @SequenceGenerator(name = "terapia_seq", sequenceName = "SEQ_TERAPIA", allocationSize = 1)
    @Column(name = "IDTERAPIA")
    private Integer idTerapia;

    @Column(name = "DATA")
    private LocalDate data;

    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUSTERAPIA", length = 20)
    private StatusTerapia statusTerapia;

    @Enumerated(EnumType.STRING)
    @Column(name = "MODALIDADE")
    private Modalidade modalidade;

    @ManyToMany
    @JoinTable(
        name = "PACIENTE_TERAPIA",
        joinColumns = @JoinColumn(name = "IDTERAPIA"),
        inverseJoinColumns = @JoinColumn(name = "IDPACIENTE")
    )
    private Set<Paciente> pacientes;

    @ManyToMany
    @JoinTable(
        name = "TERAPIA_ESPECIALISTA",
        joinColumns = @JoinColumn(name = "ID_TERAPIA", referencedColumnName = "ID_TERAPIA"),
        inverseJoinColumns = @JoinColumn(name = "ID_CADASTRO", referencedColumnName = "ID_CADASTRO")
    )
    private List<Especialista> especialistas;

    // Getters and Setters
    public Integer getIdTerapia() {
        return idTerapia;
    }

    public void setIdTerapia(Integer idTerapia) {
        this.idTerapia = idTerapia;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTerapia getStatusTerapia() {
        return statusTerapia;
    }

    public void setStatusTerapia(StatusTerapia statusTerapia) {
        this.statusTerapia = statusTerapia;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Especialista> getEspecialistas() {
        return especialistas;
    }

    public void setEspecialistas(List<Especialista> especialistas) {
        this.especialistas = especialistas;
    }
}
