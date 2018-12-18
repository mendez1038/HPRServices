package com.david.training.model;

import java.util.Date;

public class Contenido extends ValueObject{
	
	private Integer idContenido = null;
	private String titulo = null;
	private String restriccionEdad = null;
	private String descripcionContenido = null;
	private Integer ano = null;
	private String portada = null;
	private Date fechaLanzamiento = null;
	private Integer idDescuento = null;
	private String tipoContenido = null;
	
	
	public Contenido() {
		
		//this(idContenido, titulo, restriccionEdad, descripcionContenido, ano, fechaLanzamiento, null, idDescuento);
	}
	
	public Contenido( Integer idContenido, String titulo, 
			String restriccionEdad, String descripcionContenido, Integer ano, Date fechaLanzamiento
			, Integer idPais, Integer idDescuento, String tipoContenido) {
		setIdContenido(idContenido);
		setTitulo(titulo);
		setRestriccionEdad(restriccionEdad);
		setDescripcionContenido(descripcionContenido);
		setAno(ano);
		setPortada(portada);
		setFechaLanzamiento(fechaLanzamiento);
		setIdDescuento(idDescuento);
		setTipoContenido(tipoContenido);
		
	}




	public Integer getIdContenido() {
		return idContenido;
	}


	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getRestriccionEdad() {
		return restriccionEdad;
	}


	public void setRestriccionEdad(String restriccionEdad) {
		this.restriccionEdad = restriccionEdad;
	}


	public String getDescripcionContenido() {
		return descripcionContenido;
	}


	public void setDescripcionContenido(String descripcion) {
		this.descripcionContenido = descripcion;
	}


	public Integer getAno() {
		return ano;
	}


	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}


	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}


	public Integer getIdDescuento() {
		return idDescuento;
	}


	public void setIdDescuento(Integer idDescuento) {
		this.idDescuento = idDescuento;
	}


	public String getPortada() {
		return portada;
	}


	public void setPortada(String portada) {
		this.portada = portada;
	}
	
	public String getTipoContenido() {
		return tipoContenido;
	}


	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	
	
	
	//@Override
	//public String toString() {
		//return this.idContenido+","+this.titulo+","+this.restriccionEdad+","+
	//this.descripcionContenido+","+this.ano+","+this.portada+","+this.fechaLanzamiento+","
	//+this.idDescuento+","+this.tipoContenido;
		
	//}


	

}
