package com.david.training.util;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.exceptions.DataException;

public class TestException {
	
	private UsuarioDAO dao = null;
	
	public TestException() {
		
		dao = new UsuarioDAOImpl();
		
	}
	
	public void miMetodo() throws DataException 
			{
		dao.findByEmail("", null);
	}

	public static void main(String args[]) {
		TestException t = new TestException();
		try {
			t.miMetodo();
		} catch (DataException e) { 
			System.out.println("Problema ao acceder a DB: "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Petou: "+e.getMessage());
			e.printStackTrace();
		}
	}
}
