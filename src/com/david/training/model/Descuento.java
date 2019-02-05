package com.david.training.model;

import java.util.Date;

public class Descuento extends AbstractValueObject {
	
	private Integer idDescuento = null;
	private Integer porcentaje = null;
	private String nombreOferta = null;
	private Date fechaInicio = null;
	private Date fechaFin = null;
	


	public Descuento() {
		
	}



	public Integer getIdDescuento() {
		return idDescuento;
	}



	public void setIdDescuento(Integer idDescuento) {
		this.idDescuento = idDescuento;
	}



	public Integer getPorcentaje() {
		return porcentaje;
	}



	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}



	public String getNombreOferta() {
		return nombreOferta;
	}



	public void setNombreOferta(String nombreOferta) {
		this.nombreOferta = nombreOferta;
	}



	public Date getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
//	@Override
//	public String toString() {
//		return "ID:"+idDescuento+", Porcentaje:"+porcentaje+"% , Nombre:"+nombreOferta+", de "+
//	fechaInicio+" a "+fechaFin;
//		
//	}

}