package com.example.smartcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_centros_coleta")
public class CentroColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centro_coleta_seq")
    @SequenceGenerator(name = "centro_coleta_seq", sequenceName = "centro_coleta_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 150)
    private String endereco;

    @Column(name = "volume_itens_total", nullable = false)
    private Double volumeItensTotal;

    @Column(name = "volume_itens_atual", nullable = false)
    private Double volumeItensAtual;

    public CentroColeta() {
    }

    public CentroColeta(Long id, String endereco, Double volumeItensTotal, Double volumeItensAtual) {
        this.id = id;
        this.endereco = endereco;
        this.volumeItensTotal = volumeItensTotal;
        this.volumeItensAtual = volumeItensAtual;
    }

    public Long getId() {
        return id;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getVolumeItensTotal() {
        return volumeItensTotal;
    }

    public Double getVolumeItensAtual() {
        return volumeItensAtual;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setVolumeItensTotal(Double volumeItensTotal) {
        this.volumeItensTotal = volumeItensTotal;
    }

    public void setVolumeItensAtual(Double volumeItensAtual) {
        this.volumeItensAtual = volumeItensAtual;
    }
}