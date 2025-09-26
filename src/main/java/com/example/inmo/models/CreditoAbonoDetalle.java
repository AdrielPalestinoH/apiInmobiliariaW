package com.example.inmo.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credito_abono_detalle")
public class CreditoAbonoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aunque en DB es int(11), mapeamos la relación por claridad con la tabla metodo_pago.
    // No hay FK en el dump; si lo agregas, esto ya queda listo.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago") // columna existente
    private MetodoPago metodoPago;

    @Column(name = "total_abonado")
    private Double totalAbonado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    private String referencia;

    // OJO: El dump dice que 'credito_detalle_id' referencia a credito.id
    // Así que aquí es ManyToOne hacia Credito, con esa columna.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credito_detalle_id")
    private Credito credito;

    public CreditoAbonoDetalle() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public Double getTotalAbonado() { return totalAbonado; }
    public void setTotalAbonado(Double totalAbonado) { this.totalAbonado = totalAbonado; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public Credito getCredito() { return credito; }
    public void setCredito(Credito credito) { this.credito = credito; }
}
