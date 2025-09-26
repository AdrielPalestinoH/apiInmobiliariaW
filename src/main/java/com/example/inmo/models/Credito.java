package com.example.inmo.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "credito")
public class Credito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // bit(1)
    private Boolean autorizacion;

    private Double total;

    @Column(name = "total_pagos")
    private Integer totalPagos;

    private String estatus;
    private String referencia;

    private LocalDateTime fecha;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final")
    private LocalDateTime fechaFinal;

    // FK: credito.inmueble_id -> inmueble.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inmueble_id")
    private Inmueble inmueble;

    // FK: credito.usuario_id -> usuario.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación 1:N con CreditoDetalle (FK en credito_detalle.credito_id)
    @OneToMany(mappedBy = "credito", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CreditoDetalle> detalles;

    // Relación 1:N con CreditoAbonoDetalle (FK en credito_abono_detalle.credito_detalle_id -> credito.id)
    @OneToMany(mappedBy = "credito", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<CreditoAbonoDetalle> abonos;

    public Credito() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getAutorizacion() { return autorizacion; }
    public void setAutorizacion(Boolean autorizacion) { this.autorizacion = autorizacion; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Integer getTotalPagos() { return totalPagos; }
    public void setTotalPagos(Integer totalPagos) { this.totalPagos = totalPagos; }

    public String getEstatus() { return estatus; }
    public void setEstatus(String estatus) { this.estatus = estatus; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFinal() { return fechaFinal; }
    public void setFechaFinal(LocalDateTime fechaFinal) { this.fechaFinal = fechaFinal; }

    public Inmueble getInmueble() { return inmueble; }
    public void setInmueble(Inmueble inmueble) { this.inmueble = inmueble; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<CreditoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<CreditoDetalle> detalles) { this.detalles = detalles; }

    public List<CreditoAbonoDetalle> getAbonos() { return abonos; }
    public void setAbonos(List<CreditoAbonoDetalle> abonos) { this.abonos = abonos; }
}
