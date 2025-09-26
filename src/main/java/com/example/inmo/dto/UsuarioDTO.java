package com.example.inmo.dto;

public class UsuarioDTO {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public String getTipoUsuarioDescripcion() {
		return tipoUsuarioDescripcion;
	}
	public void setTipoUsuarioDescripcion(String tipoUsuarioDescripcion) {
		this.tipoUsuarioDescripcion = tipoUsuarioDescripcion;
	}
	private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String cel;
    private String tipoUsuarioDescripcion;

    // Getters y Setters
}