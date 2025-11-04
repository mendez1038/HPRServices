package com.david.training.model;

import java.util.Objects;

public class Favorito extends AbstractValueObject{

	private String email =null;
	private Integer idContenido =null;
	private Boolean favorito=null;
	
	public Favorito() {}

    public Favorito(String email, Integer idContenido, Boolean favorito) {
        this.email = email;
        this.idContenido = idContenido;
        this.favorito = favorito;
    }
	
	
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
	
	public boolean equals(Object o) {
		if (o == null) {
			return false;}
		Favorito f = (Favorito) o;
		
		if(f.getEmail()!=null && f.getIdContenido()!= null) {
		
		return true;
		}
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(email,idContenido);
		
	}
}

