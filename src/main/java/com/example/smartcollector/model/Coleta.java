package com.example.smartcollector.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_coletas")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coleta_seq")
    @SequenceGenerator(name = "coleta_seq", sequenceName = "coleta_seq", allocationSize = 1)
    @Column(name = "id_coleta")
    private Long idColeta;

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_catador")
    private Catador catador;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_descartador", nullable = false)
    private Descartador descartador;

    @ManyToOne
    @JoinColumn(name = "id_centro")
    private CentroColeta centroColeta;

    @Column(name = "foi_finalizada", nullable = false)
    private Boolean foiFinalizada = false;

    public Coleta() {
    }

    public Coleta(Long idColeta, LocalDateTime data, Catador catador, Descartador descartador,
                  CentroColeta centroColeta, Boolean foiFinalizada) {
        this.idColeta = idColeta;
        this.data = data;
        this.catador = catador;
        this.descartador = descartador;
        this.centroColeta = centroColeta;
        this.foiFinalizada = foiFinalizada;
    }

    public Long getIdColeta() {
        return idColeta;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Catador getCatador() {
        return catador;
    }

    public Descartador getDescartador() {
        return descartador;
    }

    public CentroColeta getCentroColeta() {
        return centroColeta;
    }

    public Boolean getFoiFinalizada() {
        return foiFinalizada;
    }

    public void setIdColeta(Long idColeta) {
        this.idColeta = idColeta;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setCatador(Catador catador) {
        this.catador = catador;
    }

    public void setDescartador(Descartador descartador) {
        this.descartador = descartador;
    }

    public void setCentroColeta(CentroColeta centroColeta) {
        this.centroColeta = centroColeta;
    }

    public void setFoiFinalizada(Boolean foiFinalizada) {
        this.foiFinalizada = foiFinalizada;
    }
}