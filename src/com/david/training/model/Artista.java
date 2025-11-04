package com.david.training.model;

import java.util.Date;

public class Artista extends AbstractValueObject{
	
	private Integer idArtista = null;
	private String nombreArtista = null;
	private Date fechaNacimiento = null;
	private String pais = null;
	private String rol = null;
	
	public Artista() {
		
	}
	
	public Artista(Integer idArtista, String nombreArtista, Date fechaNacimiento) {
		setIdArtista(idArtista);
		setNombreArtista(nombreArtista);
		setFechaNacimiento(fechaNacimiento);
		
		
	}
	
	
	
	public Integer getIdArtista() {
		return idArtista;
	}



	public void setIdArtista(Integer idArtista) {
		this.idArtista = idArtista;
	}



	public String getNombreArtista() {
		return nombreArtista;
	}



	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}



	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}



	@Override
	public String toString() {
		return "ID:"+idArtista+" con nombre "+nombreArtista+" y nacido el "+fechaNacimiento;
		
	}


}
