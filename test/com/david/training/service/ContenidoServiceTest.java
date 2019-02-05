package com.david.training.service;


import java.util.ArrayList;
import java.util.List;

import com.david.training.model.Contenido;
import com.david.training.service.impl.ContenidoServiceImpl;

public class ContenidoServiceTest {
	
	private ContenidoService servicio = null;
	
	public ContenidoServiceTest() {
		servicio = new ContenidoServiceImpl();
	}

	
	public void testMiLista() {
		
		
	try {
			
			List<Contenido> lista = new ArrayList<Contenido>(); 
			String email = "aa@a.com";
			String idioma = "en";
			lista = servicio.miLista(email, idioma);
			for(Contenido c : lista) {
				System.out.println(c);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFavoritos() {
		
		try {
			
			List<Contenido> favoritos = new ArrayList<Contenido>(); 
			String email = "EE@E.com";
			String idioma = "en";
			favoritos = servicio.favoritos(email, idioma);
			for(Contenido c : favoritos) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void testPrecioDescontado() {
	try {
		Contenido c = new Contenido();
		Double precioDescontado = servicio.sacarPrecioDescontado(102);
		c.setPrecioDescontado(precioDescontado);;
		c.setIdContenido(102);
		servicio.precioDescontado(c);
		System.out.println(c.getPrecioDescontado());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	
	
	public static void main(String[] args) {
		ContenidoServiceTest test = new ContenidoServiceTest();
		//test.testMiLista();
		//test.testFavoritos();
		test.testPrecioDescontado();

	}

}
