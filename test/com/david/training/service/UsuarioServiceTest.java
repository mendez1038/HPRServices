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
		u.setEmail("dmendez1038@gmail.com");
		u.setContrasena("123456");
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero(null);
		u.setFechaNacimiento(null);
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19470403" ));
		u.setTelefono("626408214");
		
		servicio.signUp(u);
		servicio.update(u);
		System.out.println(u);
		
	}
	
	
	public void testSignIn() 
			throws Exception {
		String email= "dmendez1038@gmail.com";
		String contrasena= "123456";
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
		String email = "varelafernandez.pablo@gmail.com";
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
