package com.david.training.service;

import java.text.SimpleDateFormat;

import com.david.training.model.Favorito;
import com.david.training.model.Usuario;
import com.david.training.service.impl.FavoritoServiceImpl;
import com.david.training.service.impl.UsuarioServiceImpl;

public class UsuarioServiceTest {
	
	private UsuarioService servicio = null;
	private FavoritoService servicioFavorito = null;
	
	public UsuarioServiceTest() {
		servicio = new UsuarioServiceImpl();
		servicioFavorito = new FavoritoServiceImpl();
	}

	
	public void testSignUp() {
		try {
		Usuario u = new Usuario();
		u.setEmail("hh@h.com");
		u.setContrasena("123456");
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero(null);
		//u.setFechaNacimiento(null);
		u.setFechaNacimiento(new SimpleDateFormat( "yyyy-MM-dd" ).parse( "1997-05-28" ));
		u.setTelefono(null);
		
		servicio.signUp(u);
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
		u.setGenero("F");
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
		String email = "hh@h.com";
		//servicio.exists(email); implementarlo en el service
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
	
	public void testEliminarFavoritos() {
		try {
			Favorito f = new Favorito();
			f.setFavorito(false);
			f.setEmail("aa@a.com");
			f.setIdContenido(5);;
			servicioFavorito.upadteEliminarFavorito(f);
			System.out.println(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public void testAñadirFavoritos()   {
		try {
			Favorito f = new Favorito();
			
			f.setEmail("aa@a.com");
			f.setIdContenido(500);
			f.setFavorito(true);
			
			servicioFavorito.añadirFavorito(f);
			System.out.println(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	

	public static void main(String args[]) {
		
			UsuarioServiceTest test = new UsuarioServiceTest();
			//test.testSignUp();
			//test.testSignIn();
			//test.testFindByEmail();
			//test.testEditProfile();
			test.testRemoveAccount();
			//test.testEliminarFavoritos();
			//test.testAñadirFavoritos();
	}
	
}
