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
import com.david.training.service.Results;

public class ContenidoDAOTest {
	
	private ContenidoDAO dao = null;
	
	public ContenidoDAOTest() {
		dao = new ContenidoDAOImpl();
	}
	
	public void testFindById()
	throws Exception {
		Connection connection = ConnectionManager.getConnection();
		String idioma = "es";
		Integer idContenido = 105;
		Contenido c= dao.findById(connection,idContenido,idioma);
			System.out.println(c);
		
	}
	
	public void testFindLista()
			throws Exception {
			Connection connection = ConnectionManager.getConnection();
			String idioma = "en";
			Results<Contenido> contenidos = dao.findBiblioteca(connection, "AA@A.COM", idioma, 0, 0);
			contenidos.getTotal();
			
	}
	
	public void testFindFavoritos()
		throws Exception {
		Connection connection = ConnectionManager.getConnection();
		String idioma = "en";
		Results<Contenido> contenidos = dao.findFavoritos(connection, "AA@A.COM", idioma, 0, 0);
		contenidos.getTotal();
		
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
		@SuppressWarnings("unused")
		long x;
		Connection connection = ConnectionManager.getConnection();
		
		Integer id = 205;
		x = dao.delete(connection, id);
		System.out.println("Contenido con id "+id+" eliminado.");
		
	}
	
	public void testFindByCriteria()
			throws Exception{
		Connection connection = ConnectionManager.getConnection();
		
		
		
		Categoria ca = new Categoria();
		List<Categoria> categorias = new ArrayList<Categoria>();
		ca.setIdCategoria(1);
		categorias.add(ca);
		ProductoCriteria pc = new ProductoCriteria();
		pc.setCategoria(categorias);
		dao.findByCriteria(connection, pc, "es", 0, 0);
		
	}
	
	
	public void testUpdate() 
		throws Exception{
		Connection connection = ConnectionManager.getConnection();
		String idioma = "es";
		Integer idContenido = 1;
		Contenido c= dao.findById(connection,idContenido,idioma);
		dao.update(connection, c);
		
		
	}
	
	
	
	public static void main(String args[]) {
		try {
			ContenidoDAOTest test = new ContenidoDAOTest();
			//test.testFindById();
			//test.testFindByTitulo();
			//test.testCreate();
			//test.testDelete();
			test.testFindFavoritos();
			//test.testFindLista();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	
}
