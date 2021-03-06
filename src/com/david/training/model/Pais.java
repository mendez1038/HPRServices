package com.david.training.model;

public class Pais extends AbstractValueObject {
	
	private Integer idPais = null;
	private String nombrePais = null;
	private String capitalPais = null;

	public Pais() {
		
	}
	
	public Pais(Integer idPais, String nombrePais, String capitalPais) {
		this.idPais = idPais;
		this.nombrePais = nombrePais;
		this.capitalPais = capitalPais;
	}


	public Integer getIdPais() {
		return idPais;
	}


	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}


	public String getNombrePais() {
		return nombrePais;
	}


	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}


	public String getCapitalPais() {
		return capitalPais;
	}


	public void setCapitalPais(String capitalPais) {
		this.capitalPais = capitalPais;
	}
//	
//	@Override
//	public String toString() {
//		return "ID:"+idPais+", Nombre:"+nombrePais+" y capital "+capitalPais;
//		
//	}

}
	
	