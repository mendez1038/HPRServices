package com.david.training.model;

public class Favorito extends AbstractValueObject{

	private String email =null;
	private Integer idContenido =null;
	private Boolean favorito=null;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public Boolean getFavorito() {
		return favorito;
	}
	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}
}
