package com.example.inmo.dto;
 

public class AbonoDTO {
    private Long creditoDetalleId;
    private Long metodoPagoId;
    private Double totalAbonado;
    private String referencia;

    public Long getCreditoDetalleId() { return creditoDetalleId; }
    public void setCreditoDetalleId(Long creditoDetalleId) { this.creditoDetalleId = creditoDetalleId; }

    public Long getMetodoPagoId() { return metodoPagoId; }
    public void setMetodoPagoId(Long metodoPagoId) { this.metodoPagoId = metodoPagoId; }

    public Double getTotalAbonado() { return totalAbonado; }
    public void setTotalAbonado(Double totalAbonado) { this.totalAbonado = totalAbonado; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }
}
