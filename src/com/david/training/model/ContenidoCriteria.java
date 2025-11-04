package com.david.training.model;

public class ContenidoCriteria {
	  private String titulo;          // LIKE
	  private String artista;         // LIKE
	  private String restriccionEdad; // "TP","7+","12+","16+","18+"
	  private String idTipoContenido;         // "es","en"
	  private String sortBy = "FECHA_LANZAMIENTO"; // o "TITULO"
	  private boolean desc = true;

	  // getters/setters
	  public String getTitulo() {
			return titulo;
		}
		  public void setTitulo(String titulo) {
			  this.titulo = titulo;
		  }
		  public String getArtista() {
			  return artista;
		  }
		  public void setArtista(String artista) {
			  this.artista = artista;
		  }
		  public String getRestriccionEdad() {
			  return restriccionEdad;
		  }
		  public void setRestriccionEdad(String restriccionEdad) {
			  this.restriccionEdad = restriccionEdad;
		  }
		  public String getIdTipoContenido() {
			  return idTipoContenido;
		  }
		  public void setIdTipoContenido(String idTipoContenido) {
			  this.idTipoContenido = idTipoContenido;
		  }
		  public String getSortBy() {
			  return sortBy;
		  }
		  public void setSortBy(String sortBy) {
			  this.sortBy = sortBy;
		  }
		  public boolean isDesc() {
			  return desc;
		  }
		  public void setDesc(boolean desc) {
			  this.desc = desc;
		  }
	}
