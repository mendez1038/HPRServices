package com.david.training.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public  class Pedido extends AbstractValueObject{
	
	private Integer idPedido = null;
	private String email = null;
	private Date fechaPedido = null;
	private Double precioTotal = null;
	private List<LineaPedido> lineas = null;
	
	public Pedido() {
		lineas = new ArrayList<LineaPedido>();
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = (precioTotal != null) ? precioTotal : 0.0;
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

	public List<LineaPedido> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}
	
	public void addLinea(LineaPedido lp) {
	    if (!lineas.contains(lp)) {
	        lineas.add(lp);
	    }
	}
	
	
	@Override
	public String toString() {
		return "ID:"+idPedido+", Fecha:"+fechaPedido+", Precio:"+precioTotal+" realizado por:"+email;
		
	}




}
	