package com.david.training.model;

import java.util.Date;

public class User {
	
	private String idUsuario = null;
	private String nombre=null;
	private String email=null;
	private String contrasena=null;
	private String genero=null;
	private Date fechaNacimiento = null;

	@Override
	public String toString() {
	return "User: "+getIdUsuario();
	}
	
	public User(String idUsuario, String email, String contrasena) {
		
		this(idUsuario, email, contrasena, null);
	}
	public User(String idUsuario, String nombre, String email, String contrasena) {
		setIdUsuario(idUsuario);
		setEmail(email);
		setContrasena(contrasena);
		setNombre(nombre);
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario2) {
		this.idUsuario = idUsuario2;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public boolean equals(Object o) {
		
		String otroUser = ((User) o).getIdUsuario();		
		return getIdUsuario().equalsIgnoreCase(otroUser);
	}
	
}
