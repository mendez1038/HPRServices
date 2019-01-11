package com.david.training.model;
import java.util.Date;



public  class Pedido extends AbstractValueObject{
	
	private Integer idPedido = null;
	private Date fechaPedido = null;
	private Double precioTotal = null;
	private String email = null;
	
	public Pedido() {
		
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	
	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return idPedido+","+fechaPedido+","+email+","+precioTotal;
		
	}




}
	