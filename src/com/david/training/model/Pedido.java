package com.david.training.model;
import java.util.Date;

import com.david.training.model.ValueObject;

public class Pedido extends ValueObject{
	
	private Integer idPedido = null;
	private Date fechaPedido = null;
	private String email = null;
	private Double precioTotal = null;
	
	
	public Pedido(Integer idPedido, Date fechaPedido, String email, Double precioTotal) {
		
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
		return this.idPedido+","+this.fechaPedido+","+this.precioTotal+","+
	this.email;
		
	}


}
	