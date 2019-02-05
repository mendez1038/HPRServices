package com.david.training.model;

public class Rol extends AbstractValueObject{
	
	private String idRol = null;
	private String nombreRol = null;
	
	public Rol() {
		
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

//	public String toString() {
//		return "ID:"+idRol+" con nombre "+nombreRol;
//	}
}
