package com.fatec.gisa.models;

import java.time.LocalDate;
import java.util.List;

import com.fatec.gisa.enums.StatusPaciente;
import com.fatec.gisa.enums.TipoEntrada;
import jakarta.persistence.*;

@Entity
@Table(name = "TAB_PACIENTE")
public class Paciente extends Pessoa {
    @ManyToMany
    @JoinTable(
        name = "PACIENTE_CID",
        joinColumns = @JoinColumn(name = "IDPACIENTE"),
        inverseJoinColumns = @JoinColumn(name = "CODIGOCID")
    )
    private List<CID> cids;

    @OneToOne(mappedBy = "paciente")
    private Prontuario prontuario;

    @ManyToOne
    @JoinColumn(name = "IDESCOLA")
    private Escola escola;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUSPACIENTE")
    private StatusPaciente statusPaciente;

    @Column(name = "DATACADASTRO")
    private LocalDate dataCadastro;

    @Column(name = "CONVENIO")
    private boolean convenio;

    @ManyToOne
    @JoinColumn(name = "CIDPRINCIPAL")
    private CID cidPrincipal;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPOENTRADA")
    private TipoEntrada tipoEntrada;

    // Getters and Setters
    public List<CID> getCids() {
        return cids;
    }

    public void setCids(List<CID> cids) {
        this.cids = cids;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public StatusPaciente getStatusPaciente() {
        return statusPaciente;
    }

    public void setStatusPaciente(StatusPaciente statusPaciente) {
        this.statusPaciente = statusPaciente;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean isConvenio() {
        return convenio;
    }

    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }

    public CID getCidPrincipal() {
        return cidPrincipal;
    }

    public void setCidPrincipal(CID cidPrincipal) {
        this.cidPrincipal = cidPrincipal;
    }

    public TipoEntrada getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(TipoEntrada tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }
}
