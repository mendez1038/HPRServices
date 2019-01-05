package com.david.training.model;

public class Categoria extends AbstractValueObject{
	
	private Integer idCategoria = null;
	private String nombreCategoria = null;
	
	
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
