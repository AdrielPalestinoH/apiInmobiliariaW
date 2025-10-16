package com.example.inmo.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "inmueble")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inmueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precio;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    private String descripcion;

    // FK: inmueble.estado_inmueble -> estado_inmueble.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_inmueble")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private EstadoInmueble estadoInmueble;

    // FK: inmueble.tipo_inmueble -> tipo_inmueble.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_inmueble")
    private TipoInmueble tipoInmueble;

    public Inmueble() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public EstadoInmueble getEstadoInmueble() { return estadoInmueble; }
    public void setEstadoInmueble(EstadoInmueble estadoInmueble) { this.estadoInmueble = estadoInmueble; }

    public TipoInmueble getTipoInmueble() { return tipoInmueble; }
    public void setTipoInmueble(TipoInmueble tipoInmueble) { this.tipoInmueble = tipoInmueble; }
}
