package com.david.training.model;

public class Categoria extends ValueObject{
	
	
	private String nombreCategoria = null;
	private Integer idCategoria = null;
	
	public Categoria(Integer idCategoria, String nombreCategoria) {
		
		setIdCategoria(idCategoria);
		setNombreCategoria(nombreCategoria);
		
		
	}



	public Categoria() {
		// TODO Auto-generated constructor stub
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
		return this.idCategoria+","+this.nombreCategoria;
		
	}
}
