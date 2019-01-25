package com.david.training.service;

import java.sql.Connection;

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
		String idioma = "en";
		Categoria ca = dao.findById(12, idioma, c);
		System.out.println(ca);
		
	}
	
	public void testFindByNombre() 
		throws Exception {
		
	}
	
	/*
	 * public void testCreate()
		throws Exception{
		Categoria ca = new Categoria();
		
		ca.setNombreCategoria("Terror");
		dao.create(ca, null, null);
	}
	
	  
	 */
	
	public static void main(String args[]) {
		try {
			CategoriaDAOTest test = new CategoriaDAOTest();
			
			//test.testCreate();
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}