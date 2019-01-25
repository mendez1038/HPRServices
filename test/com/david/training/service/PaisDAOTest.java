package com.david.training.service;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.PaisDAO;
import com.david.training.dao.impl.PaisDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Pais;


public class PaisDAOTest {
	
	private PaisDAO dao = null;
	
	public PaisDAOTest() {
		dao = new PaisDAOImpl();
	}
	
	public void testFindById()
			throws Exception {
			Connection c = ConnectionManager.getConnection();
			String idioma = "en";
			Pais p = dao.findById(1, idioma, c);
			System.out.println(p);
			
		}
	
	public void testFindByNombre()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		List<Pais> paises = dao.findByNombre("e", idioma, c);
		for(Pais p: paises) {
			System.out.println(p);
		}
	}
	
	public static void main(String args[]) {
		try {
			PaisDAOTest test = new PaisDAOTest();
			
			//test.testFindByNombre();
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
