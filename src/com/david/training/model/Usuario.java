package com.david.training.model;

import java.util.Date;

public class Usuario extends AbstractValueObject{

	private String email = null ;
	private String contrasena = null;
	private String nombre = null;
	private String apellidos = null;
	private String genero = null;
	private Date fechaNacimiento = null;
	private String telefono = null;
	
	
	public Usuario () {
		
	}
	
	public Usuario(String email) {
		this(email,null);
	}
	
	public Usuario(String email, String contrasena) {
		this(email, contrasena, null);
	}
	
	public Usuario(String email, String contrasena, String nombre) {
		setEmail(email);
		setContrasena(contrasena);
		setNombre(nombre);
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


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public boolean equals(Object obj) {
		boolean error = false;
		if (obj == null) {
			return error;
		} else {
		Usuario u= (Usuario)obj;
		 
		return u.getEmail().equals(this.getEmail());
		
		}
		//con "equalsIgnoreCase" podemos comparar cadenas sen importar se ten mayusculas ou minusculas
		
	}

	
	
	public String toString() {
		return "Email: "+email+", Contrase�a: "+contrasena+", Nombre: "+nombre+", Apellidos: "+
	apellidos+", Genero: "+genero+", Fecha de nacimiento: "+fechaNacimiento+" y Telefono: "+telefono;
		
	}
}
