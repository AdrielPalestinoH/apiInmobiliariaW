package com.example.inmo.dto;
 

import java.time.LocalDateTime;

public class PagoDTO {

    private Long id;
    private Long creditoId;
    private Long metodoPagoId;
    private Double totalAbonado;
    private LocalDateTime fechaPago;
    private String referencia;
    private Long creditoDetalleId; // 👈 ESTE FALTABA

    public Long getCreditoDetalleId() {
		return creditoDetalleId;
	}

	public void setCreditoDetalleId(Long creditoDetalleId) {
		this.creditoDetalleId = creditoDetalleId;
	}

	// ✅ Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Long creditoId) {
        this.creditoId = creditoId;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public Double getTotalAbonado() {
        return totalAbonado;
    }

    public void setTotalAbonado(Double totalAbonado) {
        this.totalAbonado = totalAbonado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
