package com.david.training.model;

public class TipoContenido extends AbstractValueObject{
	
	private String idTipoContenido = null;
	private String nombreContenido = null;
	
	
	public TipoContenido() {
		
	}
	
	public String getIdTipoContenido() {
		return idTipoContenido;
	}
	public void setIdTipoContenido(String idTipoContenido) {
		this.idTipoContenido = idTipoContenido;
	}
	public String getNombreContenido() {
		return nombreContenido;
	}
	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}
	
	public String toString() {
		return "ID:"+idTipoContenido+" y nombre "+nombreContenido;
	}

}
