package com.david.training.model;

public class LineaPedido extends AbstractValueObject{
	
	private Integer idPedido = null;
	private Integer idContenido = null;
	private Double precioUnidad = null;
	
	
	public LineaPedido () {
		
	}
	
	
	public Integer getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}


	public Integer getIdContenido() {
		return idContenido;
	}


	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}


	public Double getPrecioUnidad() {
		return precioUnidad;
	}


	public void setPrecioUnidad(Double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}


	
//	@Override
//	public String toString() {
//		return "Pedido "+idPedido+", contenido "+idContenido+", precio:"+precioUnidad+"�";
//		
//	}

}
