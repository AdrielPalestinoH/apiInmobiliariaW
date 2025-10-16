package com.example.inmo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credito_abono_detalle")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) 
public class CreditoAbonoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Relación correcta con detalle del crédito (pago individual)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credito_detalle_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "credito"})
    private CreditoDetalle creditoDetalle;

    // 🔹 Si quieres mantener la relación con crédito completo (usa otra FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credito_id", nullable = true)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "inmueble", "usuario"})
    private Credito credito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MetodoPago metodoPago;

    @Column(name = "total_abonado")
    private Double totalAbonado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    private String referencia;

    public CreditoAbonoDetalle() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CreditoDetalle getCreditoDetalle() { return creditoDetalle; }
    public void setCreditoDetalle(CreditoDetalle creditoDetalle) { this.creditoDetalle = creditoDetalle; }

    public Credito getCredito() { return credito; }
    public void setCredito(Credito credito) { this.credito = credito; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public Double getTotalAbonado() { return totalAbonado; }
    public void setTotalAbonado(Double totalAbonado) { this.totalAbonado = totalAbonado; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}
