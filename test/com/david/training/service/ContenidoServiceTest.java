package com.david.training.service;


import java.util.ArrayList;
import java.util.List;

import com.david.training.model.Categoria;
import com.david.training.model.Contenido;
import com.david.training.model.Pais;
import com.david.training.model.ProductoCriteria;
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
	
	public void testBusquedaEstructurada() {
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		 Categoria uno = new Categoria();
		 uno.setIdCategoria(13);
		 categorias.add(uno);
		 
		 List<Pais> paises = new ArrayList<Pais>();
		 Pais pais = new Pais();
		 pais.setIdPais(2);
		 
		 ProductoCriteria pc = new ProductoCriteria();
		 pc.setCategoria(categorias);
		 pc.setPais(paises);
		 
		try {
			List<Contenido> contenidos = null;
			contenidos = servicio.busquedaEstructurada(pc, "en");
			for(int i =0;i<contenidos.size();i++) {
				System.out.println(contenidos.get(i).getTitulo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ContenidoServiceTest test = new ContenidoServiceTest();
		//test.testMiLista();
		//test.testFavoritos();
		//test.testPrecioDescontado();
		test.testBusquedaEstructurada();
	}

}
