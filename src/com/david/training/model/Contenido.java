package com.david.training.model;

import java.util.Date;

public class Contenido extends AbstractValueObject  implements Comparable<Contenido>{
	
	private Integer idContenido = null;
	private String titulo = null;
	private String restriccionEdad = null;
	private String portada = null;
	private Date fechaLanzamiento = null;
	private String descripcionContenido = null;
	private Double precio = null;
	private Integer duracion = null;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
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
	
	
	@Override
	public int compareTo(Contenido c) {
		
		System.out.println("Comparando " +this.titulo+ " con" + c.getTitulo());
		return this.getFechaLanzamiento().compareTo(c.getFechaLanzamiento());
	}

	
	
	
	@Override
	public String toString() {
		return this.idContenido+","+this.titulo+","+this.restriccionEdad+","
	+this.portada+","+this.fechaLanzamiento+","+this.descripcionContenido+","
	+this.precio+","+this.duracion+","+this.idDescuento+","+this.tipoContenido;
		
	}
	

	/*	for (Usuario: usuarios) {
	*		System.out.println(u.getNombre());
	*	}
	*	for (int i =0; i<usuarios.size(); i++) {
	*		System.out.println(usuarios.get(i).getNombre());
	*	}
	*/	

}
