 package com.david.training.model;

import java.util.Date;

public class Contenido extends AbstractValueObject  implements Comparable<Contenido>{
	


	private Integer idContenido = null;
	private String titulo = null;
	private String restriccionEdad = null;
	private String portada = null;
	private Date fechaLanzamiento = null;
	private String descripcionBreve = null;
	private Double precio = null;
	private Double precioDescontado = null;
	private Integer duracion = null;
	private Integer idDescuento = null;
	private String tipoContenido = null;
	private Integer porcentaje = null;
	
	
	public Contenido() {
		
		
	}
	
	public Contenido( Integer idContenido, String titulo, 
			String restriccionEdad, String portada, 
			Date fechaLanzamiento, String descripcionBreve,
			Double precio, Double precioDescontado, Integer duracion, 
			Integer idDescuento, String tipoContenido, Integer porcentaje) {
		
		setIdContenido(idContenido);
		setTitulo(titulo);
		setRestriccionEdad(restriccionEdad);
		setPortada(portada);
		setFechaLanzamiento(fechaLanzamiento);
		setDescripcionBreve(descripcionBreve);
		setPrecio(precio);
		setPrecioDescontado(precioDescontado);
		setIdDescuento(idDescuento);
		setTipoContenido(tipoContenido);
		setPorcentaje(porcentaje);
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

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public String getDescripcionBreve() {
		return descripcionBreve;
	}

	public void setDescripcionBreve(String descripcionBreve) {
		this.descripcionBreve = descripcionBreve;
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

	public Integer getIdDescuento() {
		return idDescuento;
	}

	public void setIdDescuento(Integer idDescuento) {
		this.idDescuento = idDescuento;
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

	
	
	
//	//@Override
//	public String toString() {
//		return "ID:"+idContenido+" Titulo:"+titulo+" Restriccion de edad:"+restriccionEdad+" "
//				+ "Link de la portada:"+portada+" Fecha de lanzamiento:"+fechaLanzamiento+" "
//				+ "Descripcion:"+descripcionBreve+" Precio:"+precio+", Precio descontado: "+precioDescontado+", con una duracion de "+duracion+" "
//				+ "minutos, Descuento:"+idDescuento+" Tipo contenido:"+tipoContenido;
//		
//	}

	public Double getPrecioDescontado() {
		return precioDescontado;
	}

	public void setPrecioDescontado(Double precioDescontado) {
		this.precioDescontado = precioDescontado;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}
	

	/*	for (Usuario: usuarios) {
	*		System.out.println(u.getNombre());
	*	}
	*	for (int i =0; i<usuarios.size(); i++) {
	*		System.out.println(usuarios.get(i).getNombre());
	*	}
	*/	

}
