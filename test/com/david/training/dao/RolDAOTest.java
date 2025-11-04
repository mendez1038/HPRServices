package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.impl.RolDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Rol;

public class RolDAOTest {
	
	private RolDAO dao = null;
	
	public RolDAOTest() {
		dao = new RolDAOImpl();
	}
	
	public void testFindById()
			throws Exception {
			Connection c = ConnectionManager.getConnection();
			String idioma = "en";
			Rol r = dao.findById("cre", idioma, c);
			System.out.println(r);
			
		}
	
	public void testFindByNombre()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "en";
		List<Rol> roles = dao.findByNombre("e", idioma, c);
		for(Rol r: roles) {
			System.out.println(r);
		}
	}
	
	public static void main(String args[]) {
		try {
			RolDAOTest test = new RolDAOTest();
			
			//test.testFindByNombre();
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
