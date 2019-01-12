package com.david.training.model;

public class Categoria extends AbstractValueObject{
	
	private Integer idCategoria = null;
	private String nombreCategoria = null;
	
	
	public Categoria() {
		
	}


	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	@Override
	public String toString() {
		return "ID:"+idCategoria+", Nombre:"+nombreCategoria;
		
	}
}
