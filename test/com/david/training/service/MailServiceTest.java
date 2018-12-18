package com.david.training.service;

import java.util.Date;

import com.david.training.model.Matchup;

public class MailServiceTest {
	
	public static void main(String args[]) {
		
		Matchup u1 = new Matchup();
		u1.setIdMatchup("Partido 1");
		u1.setIdEquipo1(2);
		u1.setIdEquipo2(4);
		u1.setFechaHoraPrevista(new Date());
		
		
		
		String mensaje=
				
				
				"<html>"
				+ "<body>"
				+ "<p> Hola estas invitado para unirte a Fantsy Basketball Game, empezara con el partido "
				+ u1.getIdMatchup()+ " con equipos "+u1.getIdEquipo1()+" y "+u1.getIdEquipo2()
				+ "<br> a las "+u1.getFechaHoraPrevista()+" ¡Bienvenido!"
				+ "</body"
				+ "</html>";
		String asunto ="Invitación";
		String url="";
		String para[]= new String[] {""};
		MailService.sendMail(mensaje,asunto, url, para);
	}

}
	

