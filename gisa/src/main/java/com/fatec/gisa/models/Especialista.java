package com.fatec.gisa.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "TAB_ESPECIALISTA")
public class Especialista extends Profissional {
    @Column(name = "REGISTROCONSELHO")
    private String registroConselho;

    @ManyToMany
    @JoinTable(
        name = "ESPECIALISTA_PROFISSIONAL",
        joinColumns = @JoinColumn(name = "IDESPECIALISTA"),
        inverseJoinColumns = @JoinColumn(name = "IDESPECIALIDADE")
    )
    private List<Especialidade> especialidades;

    // Getters and Setters
    public String getRegistroConselho() {
        return registroConselho;
    }

    public void setRegistroConselho(String registroConselho) {
        this.registroConselho = registroConselho;
    }

    public List<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidade> especialidades) {
        this.especialidades = especialidades;
    }
}
