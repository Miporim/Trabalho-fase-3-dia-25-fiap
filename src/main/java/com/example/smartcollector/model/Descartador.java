package com.example.smartcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_descartador")
public class Descartador {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;

    @Column(nullable = false, length = 150)
    private String endereco;

    public Descartador() {
    }

    public Descartador(Usuario usuario, String endereco) {
        this.usuario = usuario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}