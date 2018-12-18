package com.david.training.model;

import java.util.Date;

public class Matchup {
	
	@Override
	public String toString() {
		return "Matchup: "+getIdMatchup(); 
	}
	private String idMatchup = null;
	private Integer idEquipo1 = null;
	private Integer idEquipo2 = null;
	
	private Date fechaHoraPrevista = null;
	private Date fechaHoraInicio = null;
	private Date fechaHoraFin = null;
	private Integer valoracion = null;
	private Integer score=null;
	
	
	public Matchup() {
		
	}

	public String getIdMatchup() {
		return idMatchup;
	}

	public void setIdMatchup(String idMatchup) {
		this.idMatchup = idMatchup;
	}

	public Integer getIdEquipo1() {
		return idEquipo1;
	}

	public void setIdEquipo1(Integer idEquipo1) {
		this.idEquipo1 = idEquipo1;
	}

	public Integer getIdEquipo2() {
		return idEquipo2;
	}

	public void setIdEquipo2(Integer idEquipo2) {
		this.idEquipo2 = idEquipo2;
	}

	public Date getFechaHoraPrevista() {
		return fechaHoraPrevista;
	}

	public void setFechaHoraPrevista(Date fechaHoraPrevista) {
		this.fechaHoraPrevista = fechaHoraPrevista;
	}

	public Date getFechaHoraInicio() {
		return fechaHoraInicio;
	}

	public void setFechaHoraInicio(Date fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
	}

	public Date getFechaHoraFin() {
		return fechaHoraFin;
	}

	public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public boolean equals(Object o) {
		
		String otroMatchup = ((Matchup) o).getIdMatchup();		
		return getIdMatchup().equalsIgnoreCase(otroMatchup);
	}
	
}
