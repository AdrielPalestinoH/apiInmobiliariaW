package com.example.inmo.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreditoDTO {
    private Long id;
    private Boolean autorizacion;
    private Double total;
    private Integer totalPagos;
    private String estatus;
    private String referencia;
    private LocalDate fechaInicio;  // 👈 cambia a LocalDate
    private LocalDate fechaFinal;   // 👈 cambia a LocalDate

    private Long inmuebleId;
    private String inmuebleDescripcion;
    private Long usuarioId;
    private String usuarioNombre;
    
    private String frecuencia; // 👈 NUEVO CAMPO: SEMANAL / QUINCENAL / MENSUAL

    // getters y setters
    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String frecuencia) { this.frecuencia = frecuencia; }
    

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

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFinal() { return fechaFinal; }
    public void setFechaFinal(LocalDate fechaFinal) { this.fechaFinal = fechaFinal; }

    public Long getInmuebleId() { return inmuebleId; }
    public void setInmuebleId(Long inmuebleId) { this.inmuebleId = inmuebleId; }

    public String getInmuebleDescripcion() { return inmuebleDescripcion; }
    public void setInmuebleDescripcion(String inmuebleDescripcion) { this.inmuebleDescripcion = inmuebleDescripcion; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }
}
