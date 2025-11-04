package com.david.training.dao;

import java.sql.Connection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.david.training.dao.impl.ArtistaDAOImpl;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Artista;


public class ArtistaDAOTest {

	private ArtistaDAO dao = null;
	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	public ArtistaDAOTest() {
		dao = new ArtistaDAOImpl();
	}

	public void testFindById()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		Artista a = dao.findById(20, c);
		System.out.println(a);

	}

	public void testFindByNombre()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		List<Artista> artistas = dao.findByNombre("sa", c);
		for(Artista a: artistas) {
			System.out.println(a);
		}
	}

	

	public void testFindAll()
			throws Exception{
		Connection c = ConnectionManager.getConnection();
		List<Artista> results = null;

		results = dao.findAll(c);

		for (Artista a : results) {
			System.out.println(a);
		}
	}

	public static void main(String args[]) {
		try {
			ArtistaDAOTest test = new ArtistaDAOTest();
			test.testFindById();
			//test.testFindByNombre();
			//test.testFindAll();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}

}
