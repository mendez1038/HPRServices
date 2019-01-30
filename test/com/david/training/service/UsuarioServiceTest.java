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
		u.setEmail("DMENDEZ1038@GMAIL.COM");
		u.setContrasena("123456");
		u.setNombre("DAVID");
		u.setApellidos("MENDEZ");
		u.setGenero("M");
		//u.setFechaNacimiento(new Date());
		u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19970528" ));
		u.setTelefono("");
		
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
	
		Usuario u = new Usuario();
		u.setContrasena("AB38");
		u.setNombre("GUI");
		u.setApellidos("FITI");
		u.setGenero(null);
		u.setFechaNacimiento(null);
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19981003" ));
		u.setTelefono("759346638");
		u.setEmail("KK@K.COM");
		servicio.update(u);
		System.out.println(u);
		
	
	}
	
	
	public void testRemoveAccount()
			throws Exception {
		String email = "";
		servicio.delete(email);
		System.out.println("Usuario eliminado con email "+email);
	}
	
	public void testFindByEmail()
			throws Exception {
		String email = "KK@K.COM";
		System.out.println(servicio.findByEmail(email));
		
	}

	public static void main(String args[]) {
		try {
			UsuarioServiceTest test = new UsuarioServiceTest();
			test.testSignUp();
			//test.testSignIn();
			//test.testFindByEmail();
			//test.testEditProfile();
			//test.testRemoveAccount();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}
	
}
