package com.david.training.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.Categoria;
import com.david.training.model.Contenido;
import com.david.training.model.Pais;
import com.david.training.model.ProductoCriteria;
import com.david.training.service.impl.ContenidoServiceImpl;
import com.david.training.util.ToStringUtil;

public class ContenidoServiceTest {

	private ContenidoService servicio = null;

	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class.getName());

	public ContenidoServiceTest() {
		servicio = new ContenidoServiceImpl();
	}


	public void testMiLista() {
		logger.info("Testing miLista ...");
		int page_size = 5;
		try {
			Results<Contenido> lista = null;
			int startIndex =1;
			int i =1;
			String email = "aa@a.com";
			String idioma = "en";
			do {
				lista = servicio.miLista(email, idioma, startIndex, page_size);
				logger.info("Found "+lista.getTotal()+" results.");
				if (lista.getPage().size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+lista.getPage().size()-1)+"] : ");				
					for (Contenido c: lista.getPage()) {
						logger.info("Result "+i+": "+ToStringUtil.toString(c));
						i++;
					} startIndex = startIndex + page_size;
				}
			} while (!(lista.getPage().size()<page_size));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Test testMiLista finished.\n");
	}

	public void testFavoritos() {
		logger.info("Testing favoritos ...");
		int pageSize = 2; 	
		try {

			Results<Contenido> favoritos = null; 
			int startIndex = 1; 
			int total = 1;
			String email = "aa@a.com";
			String idioma = "en";
			do {
			favoritos = servicio.favoritos(email, idioma, startIndex, pageSize);
			logger.info("Found "+favoritos.getTotal()+" results.");
			if (favoritos.getPage().size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+favoritos.getPage().size()-1)+"] : ");				
				for (Contenido c: favoritos.getPage()) {
					logger.info("Result "+total+": "+ToStringUtil.toString(c));
					total++;
				}
				startIndex = startIndex + pageSize;
			}
			} while (favoritos.getPage().size()==pageSize);
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
		logger.info("Testing Busqueda Estructurada ...");
		int pageSize = 3;
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria uno = new Categoria();
		uno.setIdCategoria(13);
		categorias.add(uno);

		List<Pais> paises = new ArrayList<Pais>();
		Pais pais = new Pais();
		pais.setIdPais(2);
		paises.add(pais);

		String titulo = "vo";
		ProductoCriteria pc = new ProductoCriteria();
		//pc.setCategoria(categorias);
		//pc.setPais(paises);
		//pc.setTitulo(titulo);
		
		try {
			Results<Contenido> contenidos = null;
			int startIndex = 1; 
			int i = 1;
			String idioma ="es";
			
			do {
			contenidos = servicio.busquedaEstructurada(pc, idioma, startIndex, pageSize);
			logger.info("Found "+contenidos.getTotal()+" results.");
			if (contenidos.getPage().size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+contenidos.getPage().size()-1)+"] : ");				
				for (Contenido c: contenidos.getPage()) {
					logger.info("Result "+i+": "+ToStringUtil.toString(c));
					i++;
				}
				startIndex = startIndex + pageSize;
			}
			} while (contenidos.getPage().size()==pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Test testBusquedaEstructurada finished.\n");
	}

	public static void main(String[] args) {
		ContenidoServiceTest test = new ContenidoServiceTest();
		//test.testMiLista();
		//test.testFavoritos();
		//test.testPrecioDescontado();
		test.testBusquedaEstructurada();
	}

}
