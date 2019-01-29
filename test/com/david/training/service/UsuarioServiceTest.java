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
		u.setEmail("KK@K.COM");
		u.setContrasena("AB38");
		u.setNombre("GUI");
		u.setApellidos("FITI");
		u.setGenero("F");
		//u.setFechaNacimiento(new Date());
		u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19981003" ));
		u.setTelefono("759346638");
		
		servicio.signUp(u);
		System.out.println(u);
		
	}
	
	
	public void testSignIn() 
			throws Exception {
		String email= "";
		String contrasena= "";
		//servicio.signIn(email,contrasena);
		System.out.println(servicio.signIn(email,contrasena));
		
	}
	
	public void testEditProfile() 
			throws Exception {
	}

	public static void main(String args[]) {
		try {
			UsuarioServiceTest test = new UsuarioServiceTest();
			test.testSignUp();
			//test.testSignIn();
			
		} catch (Exception u) {
			u.printStackTrace();
		}
	}
	
}
