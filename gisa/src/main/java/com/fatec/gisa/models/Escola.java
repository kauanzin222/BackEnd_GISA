package com.fatec.gisa.models;

import com.fatec.gisa.enums.TipoEscola;
import jakarta.persistence.*;

@Entity
@Table(name = "TAB_ESCOLA")
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "escola_seq")
    @SequenceGenerator(name = "escola_seq", sequenceName = "SEQ_ESCOLA", allocationSize = 1)
    @Column(name = "IDESCOLA")
    private Integer idEscola;

    @Column(name = "NOME")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPOESCOLA")
    private TipoEscola tipoEscola;

    @Column(name = "TELEFONE")
    private String telefone;

    // Getters and Setters
    public Integer getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Integer idEscola) {
        this.idEscola = idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEscola getTipoEscola() {
        return tipoEscola;
    }

    public void setTipoEscola(TipoEscola tipoEscola) {
        this.tipoEscola = tipoEscola;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
