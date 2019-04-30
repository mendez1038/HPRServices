package com.david.training.model;

public class ArtistaRol extends AbstractValueObject {
	
	private Integer idArtista = null;
	private String idRol = null;
	
	public Integer getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(Integer idArtista) {
		this.idArtista = idArtista;
	}
	public String getIdRol() {
		return idRol;
	}
	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

}
