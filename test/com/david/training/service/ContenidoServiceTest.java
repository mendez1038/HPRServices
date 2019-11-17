package com.david.training.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.Artista;
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
			for (int i = 1; i < 11; i++) {
				Contenido c = new Contenido();
				Double precioDescontado = servicio.sacarPrecioDescontado(i);
				c.setPrecioDescontado(precioDescontado);;
				c.setIdContenido(i);
				servicio.precioDescontado(c);
				System.out.println(c);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testBusquedaEstructurada() {
		logger.info("Testing Busqueda Estructurada ...");
		int pageSize = 5;
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria uno = new Categoria();
		Categoria dos = new Categoria();
		uno.setIdCategoria(13);
		categorias.add(uno);
		//categorias.add(dos);
		List<Pais> paises = new ArrayList<Pais>();
		Pais pais = new Pais();
		Pais pais2 = new Pais();
		pais2.setIdPais(2);
		pais.setIdPais(2);
		//paises.add(pais);
		paises.add(pais2);
		String titulo = "str";
		String rEdad = "16+";
		String tipo = "DOC";
		int por = 5;
		Artista a = new Artista();
		String nombre = "tarantino";
		a.setNombreArtista(nombre);
		ProductoCriteria pc = new ProductoCriteria();
		
		
//		pc.setTitulo(titulo);
		//pc.setRestriccionEdad(rEdad);
//		pc.setTipoContenido(tipo);
//		pc.setPorcentaje(por);
		pc.setA(a);		
		//pc.setPais(paises);
		//pc.setCategoria(categorias);		

		
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
		logger.info("Titulo: "+ pc.getTitulo());
		logger.info("Edad: "+ pc.getRestriccionEdad());
	}
	
	public void testDetalle() {
		logger.info("Testing Vista Detalle ...");
		String idioma = "en";
		Integer idContenido = 105;
		try {
		Contenido contenido = servicio.findById(idContenido, idioma);
		System.out.println(contenido);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public void testFindAllByRebajas() {
		logger.info("Testing findAllByRebajas ...");
		int pageSize = 4; 	
		try {

			Results<Contenido> all = null; 
			int startIndex = 1; 
			int total = 1;
			String idioma = "en";
		do {
			all = servicio.findAllByRebajas(idioma, startIndex, pageSize);
			logger.info("Found "+all.getTotal()+" results.");
			if (all.getPage().size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+all.getPage().size()-1)+"] : ");				
				for (Contenido c: all.getPage()) {
					logger.info("Result "+total+": "+ToStringUtil.toString(c));
					total++;
				}
				startIndex = startIndex + pageSize;
			}
			} while (all.getPage().size()==pageSize);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFindAllByDate() {
		logger.info("Testing findAllByDate ...");
		int pageSize = 2; 	
		try {

			Results<Contenido> all = null; 
			int startIndex = 1; 
			int total = 1;
			String idioma = "en";
			do {
			all = servicio.findAllByDate(idioma, startIndex, pageSize);
			logger.info("Found "+all.getTotal()+" results.");
			if (all.getPage().size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+all.getPage().size()-1)+"] : ");				
				for (Contenido c: all.getPage()) {
					logger.info("Result "+total+": "+ToStringUtil.toString(c));
					total++;
				}
				startIndex = startIndex + pageSize;
			}
			} while (all.getPage().size()==pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFindAllByVentas() {
		logger.info("Testing findAllByVentas ...");
		int pageSize = 2; 	
		try {

			List<Contenido> all = null; 
			int startIndex = 1; 
			int total = 1;
			String idioma = "en";
		
			all = servicio.findAllByVentas(idioma);
			logger.info("Found results.");
			
				for (Contenido c: all) {
					logger.info("Result "+total+": "+ToStringUtil.toString(c));
					total++;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ContenidoServiceTest test = new ContenidoServiceTest();
		//test.testMiLista();
		
		/*Properties systemProperties = System.getProperties();
		String key = null;
		for (Enumeration keys = systemProperties.keys(); keys.hasMoreElements(); ) {
			key  = (String) keys.nextElement();
			System.out.println(key+"="+System.getProperty(key));
			
		}
		*/
		//test.testFavoritos();
		//test.testPrecioDescontado();
		//test.testBusquedaEstructurada();
		//test.testDetalle();
		test.testFindAllByRebajas();
		//test.testFindAllByVentas();
	}

}
