package com.david.training.dao;

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
			String idioma = "es";
			Pais p = dao.findById(1, idioma, c);
			System.out.println(p);
			
		}
	
	public void testFindAll()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "en";
		List<Pais> paises = dao.findAll(idioma, c);
		for(Pais p: paises) {
			System.out.println(p);
		}
	}
	
	public static void main(String args[]) {
		try {
			PaisDAOTest test = new PaisDAOTest();
			
			test.testFindAll();
			//test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
