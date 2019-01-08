package com.david.training.service;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.model.Usuario;

public class UsuarioDAOTest {
	
	private UsuarioDAO dao = null;
	
	public UsuarioDAOTest() {
		dao = new UsuarioDAOImpl();
	}
	public void testCreate()
		throws Exception{
		
		Usuario u = new Usuario();
		u.setEmail("KK@K.COM");
		u.setContrasena("2589");
		
		dao.create(u);
	}
	
	public static void main(String args[]) {
		try {
			UsuarioDAOTest test = new UsuarioDAOTest();
			//test.testFindByEmail();
			
			test.testCreate();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

}

	
