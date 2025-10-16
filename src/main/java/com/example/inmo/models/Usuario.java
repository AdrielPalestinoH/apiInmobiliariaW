package com.example.inmo.models;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK: usuario.tipo_usuario -> tipo_usuario.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    private String apellidos;
    private String cel;
    private String email;
    private String nombre;

    // Considera renombrar a "password" en DTOs; aquí reflejamos la columna tal cual
    @Column(name = "pwd")
    private String pwd;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCel() { return cel; }
    public void setCel(String cel) { this.cel = cel; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
}
