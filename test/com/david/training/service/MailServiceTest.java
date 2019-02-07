package com.david.training.service;

import java.util.Date;

import com.david.training.model.Contenido;


public class MailServiceTest {
	
	public static void main(String args[]) {
		
		Contenido u1 = new Contenido();
		u1.setIdContenido(1);
		u1.setFechaLanzamiento(new Date());
		
		Contenido u2 = new Contenido();
		u2.setIdContenido(1);
		u2.setFechaLanzamiento(new Date());
		
		
		
		
		String mensaje=
				
				
				"<html>"
				+ "<body>"
				+ "<p> Hola estas invitado para unirte a Fantsy Basketball Game, empezara con el partido "
				+ u1.getIdContenido()+ " con equipos "+u1.getIdContenido()+" y "+u2.getIdContenido()
				+ "<br> a las "+u1.getFechaLanzamiento()+" ¡Bienvenido!"
				+ "</body"
				+ "</html>";
		String asunto ="Invitación";
		String url="";
		String para[]= new String[] {""};
		
	}

}
	

