package com.fatec.gisa.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "ESPECIALISTA")
public class Especialista extends Profissional {
    @Column(name = "REGISTRO_CONSELHO", length = 50)
    private String registroConselho;

    @ManyToMany
    @JoinTable(
        name = "ESPECIALISTA_ESPECIALIDADE",
        joinColumns = @JoinColumn(name = "ID_CADASTRO", referencedColumnName = "ID_CADASTRO"),
        inverseJoinColumns = @JoinColumn(name = "ID_ESPECIALIDADE", referencedColumnName = "ID_ESPECIALIDADE")
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
