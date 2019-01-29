package com.david.training.service;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.CategoriaDAO;
import com.david.training.dao.impl.CategoriaDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Categoria;


public class CategoriaDAOTest {
	
	private CategoriaDAO dao = null;
	
	public CategoriaDAOTest() {
		dao = new CategoriaDAOImpl();
	}

	public void testFindById()
		throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		Categoria ca = dao.findById(12, idioma, c);
		System.out.println(ca);
		
	}
	
	public void testFindByContenido() 
		throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		List<Categoria> categorias = dao.findByContenido(2, idioma, c);
		for(Categoria a: categorias) {
			System.out.println(a);
		}
	}
	
	public void testFindAll()
			throws Exception{
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		List<Categoria> categorias = null;

		categorias = dao.findAll(idioma, c);

		for (Categoria a : categorias) {
			System.out.println(a);
		}
	}
	

	
	public static void main(String args[]) {
		try {
			CategoriaDAOTest test = new CategoriaDAOTest();
			//test.testFindById();
			test.testFindByContenido();
			//test.testFindAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}