package com.david.training.model;

public class ArtistaRol extends AbstractValueObject {

	private Integer idArtista = null;
	private String idRol = null;
    private String nombreArtista = null;
    private String nombreRol = null;

	public String getNombreArtista() {
		return nombreArtista;
	}

	public void setNombreArtista(String nombreArtista) {
		this.nombreArtista = nombreArtista;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

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
