package com.david.training.service;



import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Usuario;


public class UsuarioDAOTest {
	
	private UsuarioDAO dao = null;
	
	public UsuarioDAOTest() {
		dao = new UsuarioDAOImpl();
	}
	
	public void testFindByEmail() 
		throws Exception{
		Connection c = ConnectionManager.getConnection();
		Usuario u = dao.findByEmail("CC@C.COM", c);
		Usuario u2 = dao.findByEmail("JJ@J.COM", c);
		System.out.println(u);
		System.out.println(u2);
		
	}
	
	
	public void testCreate()
		throws Exception{
		Connection c = ConnectionManager.getConnection();
		
		Usuario u = new Usuario();
		u.setEmail("KK@K.COM");
		u.setContrasena("AB89");
		u.setNombre("GUI");
		u.setApellidos("FITI");
		u.setGenero("F");
		//u.setFechaNacimiento(2000,Calendar.JANUARY,3);
		u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "20000103" ));
		u.setTelefono("754987321");
		
		dao.create(u, c);
		System.out.println(u);
	}
	
	public void testDelete()
		throws Exception {
		Connection c = ConnectionManager.getConnection();
		String email = "LL@L.COM";
		dao.delete(email, c);
		System.out.println("Usuario eliminado con email "+email);
	}
	
	
	public void testUpdate()
		throws Exception {
		Connection c = ConnectionManager.getConnection();
		Usuario u = new  Usuario();
		u.setContrasena("123456");
		u.setNombre("PEDRO");
		u.setApellidos("ALVAREZ");
		u.setGenero(null);
		u.setFechaNacimiento(null);
		u.setTelefono("333666999");
		u.setEmail("KK@K.COM");
		dao.update(u, c);
		System.out.println(u);
	}	
	
	public void testExists() 
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		System.out.println("Testeando existencia de usuarios ...");
		String email = "AA@A.COM";
		try {
			Boolean exists = dao.exists(email, c);
			System.out.println("Existe "+email+"? =>"+exists);
		} catch (Throwable t) {
			System.out.println(t);
		}
		System.out.println("Test finalizado");
		
	}
	
	public void testCountAll()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		System.out.println("Contando usuarios ...");
		System.out.println("Usuarios totales: "+dao.countAll(c));
	}
	
	public static void main(String args[]) {
		try {
			UsuarioDAOTest test = new UsuarioDAOTest();
			//test.testFindByEmail();
			//test.testDelete();
			//test.testCreate();
			test.testUpdate();
			//test.testExists();
			//test.testCountAll();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

}

	
