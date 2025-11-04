package com.david.training.model;

import java.util.Date;

//PedidoCriteria.java
public class PedidoCriteria {
	private String email; // obligatorio
	private Date fechaDesde; // opcional
	private Date fechaHasta; // opcional (inclusive)
	private String sortBy = "FECHA_PEDIDO"; // FECHA_PEDIDO | ID_PEDIDO
	private boolean desc = true;

//getters/setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
