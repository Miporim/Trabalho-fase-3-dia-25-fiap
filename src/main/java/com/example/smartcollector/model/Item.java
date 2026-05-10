package com.example.smartcollector.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Double volume;

    public Item() {
    }

    public Item(Long id, String nome, Double volume) {
        this.id = id;
        this.nome = nome;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getVolume() {
        return volume;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}