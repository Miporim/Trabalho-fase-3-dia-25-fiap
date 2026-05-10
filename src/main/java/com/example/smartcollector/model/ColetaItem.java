package com.example.smartcollector.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_coleta_itens")
@IdClass(ColetaItem.ColetaItemId.class)
public class ColetaItem {

    @Id
    @Column(name = "id_coleta")
    private Long idColeta;

    @Id
    @Column(name = "id_item")
    private Long idItem;

    @ManyToOne
    @JoinColumn(name = "id_coleta", insertable = false, updatable = false)
    private Coleta coleta;

    @ManyToOne
    @JoinColumn(name = "id_item", insertable = false, updatable = false)
    private Item item;

    public ColetaItem() {
    }

    public ColetaItem(Long idColeta, Long idItem) {
        this.idColeta = idColeta;
        this.idItem = idItem;
    }

    public Long getIdColeta() {
        return idColeta;
    }

    public Long getIdItem() {
        return idItem;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public Item getItem() {
        return item;
    }

    public void setIdColeta(Long idColeta) {
        this.idColeta = idColeta;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public static class ColetaItemId implements Serializable {

        private Long idColeta;
        private Long idItem;

        public ColetaItemId() {
        }

        public ColetaItemId(Long idColeta, Long idItem) {
            this.idColeta = idColeta;
            this.idItem = idItem;
        }

        public Long getIdColeta() {
            return idColeta;
        }

        public Long getIdItem() {
            return idItem;
        }

        public void setIdColeta(Long idColeta) {
            this.idColeta = idColeta;
        }

        public void setIdItem(Long idItem) {
            this.idItem = idItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ColetaItemId that)) return false;
            return Objects.equals(idColeta, that.idColeta) &&
                    Objects.equals(idItem, that.idItem);
        }

        @Override
        public int hashCode() {
            return Objects.hash(idColeta, idItem);
        }
    }
}