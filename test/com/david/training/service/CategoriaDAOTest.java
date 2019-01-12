package com.david.training.service;

import com.david.training.dao.CategoriaDAO;
import com.david.training.dao.impl.CategoriaDAOImpl;
import com.david.training.model.Categoria;

public class CategoriaDAOTest {
	
	private CategoriaDAO dao = null;
	
	public CategoriaDAOTest() {
		dao = new CategoriaDAOImpl();
	}

	public void testFindById()
		throws Exception {
		Categoria ca = dao.findById(1);
		System.out.println(ca);
		
	}
	
	public void testFindByNombre() 
		throws Exception {
		
	}
	
	public void testCreate()
		throws Exception{
		Categoria ca = new Categoria();
		
		ca.setNombreCategoria("Terror");
		dao.testCreate(ca);
	}
	
	public static void main(String args[]) {
		try {
			CategoriaDAOTest test = new CategoriaDAOTest();
			
			
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}