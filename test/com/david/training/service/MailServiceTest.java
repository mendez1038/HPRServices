package com.david.training.service;

import com.david.training.model.Usuario;
import com.david.training.service.impl.MailServiceImpl;



public class MailServiceTest {
	
	public static void main(String args[]) throws Exception {
		
		Usuario u1 = new Usuario();
		u1.setEmail("varelavazquez.pablo@gmail.com");
		u1.setNombre("Pepe");
		u1.setApellidos("Perez");
		
		
		
		Usuario u2 = new Usuario();
		u2.setEmail("joseantoniolp.teacher@gmail.com");
		u2.setNombre("Jose Antonio");
		u2.setApellidos("Lopez");

		Usuario[] usuarios = new Usuario[2];
		usuarios[0] = u1;
		usuarios[1] = u2;
		
		MailService mailService = new MailServiceImpl();
		
		for (@SuppressWarnings("unused") Usuario u :usuarios) {
			String mensaje=			
					"<html>"
					+ "<body>"
					+ "<p> Hola estas invitado para unirte a Fantsy Basketball Game, empezara con el partido "
					+ u1.getEmail()+ " con equipos "+u1.getApellidos()+" y "+u2.getEmail()
					+ "<br> a las "+u1.getApellidos()+" ¡Bienvenido!"
					+ "</body"
					+ "</html>";
			String asunto ="Invitación";
			String para[]= new String[] {""};
			
			mailService.sendEmail(mensaje, asunto, para);
		}
		
		
	}

}
	

