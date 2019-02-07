package com.david.training.service;

import com.david.training.model.Usuario;
import com.david.training.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	
	private UsuarioService servicio = null;
	
	public UsuarioServiceTest() {
		servicio = new UsuarioServiceImpl();
	}

	
	public void testSignUp() {
		try {
		Usuario u = new Usuario();
		u.setEmail("d.mendez_97@hotmail.com");
		u.setContrasena("123456");
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero(null);
		u.setFechaNacimiento(null);
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19470403" ));
		u.setTelefono(null);
		
		servicio.signUp(u);
		servicio.update(u);
		System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void testSignIn()  {
		try {
		Usuario u = new Usuario();
		String email= "dmendez1038@gmail.com";
		String contrasena= "123456";
		u = servicio.signIn(email,contrasena);
		System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testEditProfile()  {
		try {
	
		Usuario u = new Usuario();
		u.setContrasena(null);
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero("M");
		u.setFechaNacimiento(null);
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "19981003" ));
		u.setTelefono(null);
		u.setEmail("d.mendez_97@hotmail.com");
		servicio.update(u);
		System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void testRemoveAccount() {
		try {
		String email = "d.mendez_97@hotmail.com";
		servicio.delete(email);
		System.out.println("Usuario eliminado con email "+email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFindByEmail() {
		try {
		String email = "dmendez1038@gmail.com";
		System.out.println(servicio.findByEmail(email));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		
			UsuarioServiceTest test = new UsuarioServiceTest();
			//test.testSignUp();
			//test.testSignIn();
			test.testFindByEmail();
			//test.testEditProfile();
			//test.testRemoveAccount();
		
	}
	
}
