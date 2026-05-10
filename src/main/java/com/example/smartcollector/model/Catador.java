package com.example.smartcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_catador")
public class Catador {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;

    @Column(name = "capacidade_volume_total", nullable = false)
    private Double capacidadeVolumeTotal;

    public Catador() {
    }

    public Catador(Usuario usuario, Double capacidadeVolumeTotal) {
        this.usuario = usuario;
        this.capacidadeVolumeTotal = capacidadeVolumeTotal;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Double getCapacidadeVolumeTotal() {
        return capacidadeVolumeTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setCapacidadeVolumeTotal(Double capacidadeVolumeTotal) {
        this.capacidadeVolumeTotal = capacidadeVolumeTotal;
    }
}