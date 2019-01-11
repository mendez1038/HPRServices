package com.david.training.service;

import java.util.Date;
import java.util.List;

import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.model.Contenido;

public class ContenidoDAOTest {
	
	private ContenidoDAO dao = null;
	
	public ContenidoDAOTest() {
		dao = new ContenidoDAOImpl();
	}
	
	public void testFindById()
	throws Exception {
		Contenido c= dao.findById(5);
			System.out.println(c);
		
	}
	
	public void testFindByTitulo()
		throws Exception {
	List<Contenido> contenidos = dao.findByTitulo("s");
	for(Contenido c: contenidos) {
		System.out.println(c);
	}
	}
	
	public void testCreate()
			throws Exception {
		
		Contenido c = new Contenido();
		c.setIdContenido(205);
		c.setTitulo("No Crossover");
		c.setRestriccionEdad("TP");
		c.setPortada("URL");
		c.setFechaLanzamiento(new Date());
		c.setDescripcionBreve("La vida de Allen Iverson");
		c.setPrecio(1.50);
		c.setDuracion(50);
		c.setIdDescuento(1);
		c.setTipoContenido("DOC");
		
		dao.create(c);
		System.out.println(c);
	}
	
	public static void main(String args[]) {
		try {
			ContenidoDAOTest test = new ContenidoDAOTest();
			//test.testFindById();
			//test.testFindByTitulo();
			test.testCreate();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	
}
