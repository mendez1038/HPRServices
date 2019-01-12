package com.david.training.model;

public class EqualsToString {

	public static void main(String[] args) {
		
		Contenido u = new Contenido();
		
		u.setIdContenido(2);
		u.setIdDescuento(2);
		
		String idEquipo1 = u.getIdContenido().toString();
		String idEquipo2 = u.getIdDescuento().toString();
		
		System.out.println(idEquipo1.equals(idEquipo2));
	}

}
