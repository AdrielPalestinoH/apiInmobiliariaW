package com.example.inmo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "credito_detalle")
public class CreditoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer estatus; // si luego quieres enum, lo hacemos

    private Double precio;

    // FK: credito_detalle.credito_id -> credito.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credito_id")
    private Credito credito;

    public CreditoDetalle() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getEstatus() { return estatus; }
    public void setEstatus(Integer estatus) { this.estatus = estatus; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Credito getCredito() { return credito; }
    public void setCredito(Credito credito) { this.credito = credito; }
}
