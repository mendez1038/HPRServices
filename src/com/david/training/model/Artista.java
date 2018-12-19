package com.david.training.model;

import java.util.Date;

public class Artista extends ValueObject{
	
	private Integer idArtista = null;
	private String nombreArtista = null;
	private String descripcionArtista = null;
	private Date fechaNacimiento = null;
	
	
	public Artista(Integer idArtista, String nombreArtista, String descripcionArtista, Date fechaNacimiento) {

		
	}
	
	public Integer getIdParticipante() {
		return idArtista;
	}


	public void setIdParticipante(Integer idParticipante) {
		this.idArtista = idParticipante;
	}


	public String getNombreParticipante() {
		return nombreArtista;
	}


	public void setNombreParticipante(String nombreParticipante) {
		this.nombreArtista = nombreParticipante;
	}
	

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}




	public String getDescripcionArtista() {
		return descripcionArtista;
	}


	public void setDescripcionArtista(String descripcionArtista) {
		this.descripcionArtista = descripcionArtista;
	}
	
	@Override
	public String toString() {
		return this.idArtista+","+this.nombreArtista+","+this.fechaNacimiento+","+
	this.descripcionArtista;
		
	}


}