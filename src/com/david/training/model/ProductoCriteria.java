package com.david.training.model;

import java.util.ArrayList;
import java.util.List;

public class ProductoCriteria extends Contenido implements ValueObject{
	
	private List<Categoria> categoria=null;
	private List<Pais> pais=null;
	private Artista a = null;

	
	public ProductoCriteria () {
		categoria = new ArrayList<Categoria>();
		pais = new ArrayList<Pais>();
	}
	
	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	public List<Pais> getPais() {
		return pais;
	}

	public void setPais(List<Pais> pais) {
		this.pais = pais;
	}

	public Artista getA() {
		return a;
	}

	public void setA(Artista a) {
		this.a = a;
	}
}
