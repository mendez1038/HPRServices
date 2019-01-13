package com.david.training.service;



import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;


public class UsuarioDAOTest {
	
	private UsuarioDAO dao = null;
	
	public UsuarioDAOTest() {
		dao = new UsuarioDAOImpl();
	}
	
	public void testFindByEmail() 
		throws Exception{
		Usuario u = dao.findByEmail("CC@C.COM");
		Usuario u2 = dao.findByEmail("JJ@J.COM");
		System.out.println(u);
		System.out.println(u2);
		
	}
	
	
	public void testCreate()
		throws Exception{
		
		Usuario u = new Usuario();
		u.setEmail("KK@K.COM");
		u.setContrasena("2589");
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero(null);
		u.setFechaNacimiento(null);
		u.setTelefono(null);
		
		dao.create(u);
		System.out.println(u);
	}
	
	public void testDelete()
		throws Exception {
		long fila;
		String email = "KK@K.COM";
		fila = dao.delete(email);
		System.out.println("Usuario eliminado con email "+email);
		
		
	}
	
	
	public void testUpdate()
		throws DataException {
		Usuario u = new  Usuario();
		u.setContrasena("123456");
		u.setNombre(null);
		u.setApellidos(null);
		u.setGenero(null);
		u.setFechaNacimiento(null);
		u.setTelefono(null);
		dao.update(u);
		System.out.println(u);
	}	
	
	public static void main(String args[]) {
		try {
			UsuarioDAOTest test = new UsuarioDAOTest();
			test.testFindByEmail();
			//test.testDelete();
			//test.testCreate();
			//test.testUpdate();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

}

	
