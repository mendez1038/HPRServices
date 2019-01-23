package com.david.training.service;

import java.util.Date;

import com.david.training.model.Usuario;
import com.david.training.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	
	private UsuarioService servicio = null;
	
	UsuarioServiceTest() {
		servicio = new UsuarioServiceImpl();
	}

	
	public void testSignUp() 
			throws Exception {
		Usuario u = new Usuario();
		u.setEmail("DMENDEZ@GMAIL.COM");
		u.setContrasena("DMM");
		u.setNombre("DAVID");
		u.setApellidos("MENDEZ");
		u.setGenero("M");
		u.setFechaNacimiento(new Date());
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "20001210" ));
		u.setTelefono("626408214");
		
		servicio.signUp(u);
		System.out.println(u);
		
	}
	
	
	public void testSignIn() 
			throws Exception {
		String email= "DMENDEZ1038@GMAIL.COM";
		String contrasena= "DMM";
		//servicio.signIn(email,contrasena);
		System.out.println(servicio.signIn(email,contrasena));
		
	}

	public static void main(String args[]) {
		try {
			UsuarioServiceTest test = new UsuarioServiceTest();
			//test.testSignUp();
			test.testSignIn();
			
		} catch (Exception u) {
			u.printStackTrace();
		}
	}
	
}
