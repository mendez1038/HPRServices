package com.david.training.service;

import java.text.SimpleDateFormat;
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
		u.setEmail("HUGOMEMA@GMAIL.COM");
		u.setContrasena("HMM");
		u.setNombre("HUGO");
		u.setApellidos("MENDEZ");
		u.setGenero("M");
		//u.setFechaNacimiento(new Date());
		u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19990423" ));
		u.setTelefono("626408214");
		
		servicio.signUp(u);
		System.out.println(u);
		
	}
	
	
	public void testSignIn() 
			throws Exception {
		String email= "HUGOMEMA@GMAIL.COM";
		String contrasena= "H"
				+ "MM";
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
