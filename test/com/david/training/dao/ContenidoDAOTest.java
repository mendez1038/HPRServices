package com.david.training.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Categoria;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public class ContenidoDAOTest {
	
	private ContenidoDAO dao = null;
	
	public ContenidoDAOTest() {
		dao = new ContenidoDAOImpl();
	}
	
	public void testFindById()
	throws Exception {
		Connection connection = ConnectionManager.getConnection();
		String idioma = "es";
		Contenido c= dao.findById(connection,7,idioma);
			System.out.println(c);
		
	}
	
	public void testFindLista()
			throws Exception {
			Connection connection = ConnectionManager.getConnection();
			String idioma = "en";
			List<Contenido> contenidos = dao.findLista(connection, "AA@A.COM", idioma);
			for(Contenido c: contenidos) {
				System.out.println(c);
			}
	}
	
	public void testFindFavoritos()
		throws Exception {
		Connection connection = ConnectionManager.getConnection();
		String idioma = "en";
		List<Contenido> contenidos = dao.findFavoritos(connection, "AA@A.COM", idioma);
		for(Contenido c: contenidos) {
			System.out.println(c);
		}
	}

	public void testCreate()
			throws Exception {
		Connection connection = ConnectionManager.getConnection();
		
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
		
		dao.create(connection, c);
		System.out.println(c);
	}
	
	public void testDelete()	
			throws Exception{
		long x;
		Connection connection = ConnectionManager.getConnection();
		
		Integer id = 205;
		x = dao.delete(connection, id);
		System.out.println("Contenido con id "+id+" eliminado.");
		
	}
	
	public void testFindByCriteria()
			throws Exception{
		Connection connection = ConnectionManager.getConnection();
		ProductoCriteria pc = new ProductoCriteria();
		Categoria ca = new Categoria();
		List<Categoria> categorias = new ArrayList();
		ca.setIdCategoria(1);
		categorias.add(ca);
		pc.setCategoria(categorias);
		
	}
	
	public static void main(String args[]) {
		try {
			ContenidoDAOTest test = new ContenidoDAOTest();
			//test.testFindById();
			//test.testFindByTitulo();
			//test.testCreate();
			//test.testDelete();
			//test.testFindFavoritos();
			test.testFindLista();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	
}
