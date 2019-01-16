package com.david.training.service;



import java.text.SimpleDateFormat;
import java.util.Date;

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
		u.setEmail("LL@L.COM");
		u.setContrasena("2589");
		u.setNombre("Lucas");
		u.setApellidos("VdG");
		u.setGenero("M");
		u.setFechaNacimiento(new Date());
		//u.setFechaNacimiento(new SimpleDateFormat( "yyyyMMdd" ).parse( "20001210" ));
		u.setTelefono("654987321");
		
		dao.create(u);
		System.out.println(u);
	}
	
	public void testDelete()
		throws Exception {
		long fila;
		String email = "LL@L.COM";
		fila = dao.delete(email);
		System.out.println("Usuario eliminado con email "+email);
		
		
	}
	
	
	public void testUpdate()
		throws DataException {
		
		Usuario u = new  Usuario();
		u.setContrasena("123456");
		u.setNombre("Pedro");
		u.setApellidos("Alvarez");
		u.setGenero("O");
		u.setFechaNacimiento(new Date());
		u.setTelefono("333666999");
		u.setEmail("AA@A.COM");
		dao.update(u);
		System.out.println(u);
	}	
	
	public void testExists() 
			throws Exception {
		System.out.println("Testeando existencia de usuarios ...");
		String email = "AA@A.COM";
		try {
			Boolean exists = dao.exists(email);
			System.out.println("Existe "+email+"? =>"+exists);
		} catch (Throwable t) {
			System.out.println(t);
		}
		System.out.println("Test finalizado");
		
	}
	
	public void testCountAll()
			throws Exception {
		System.out.println("Contando usuarios ...");
		System.out.println("Usuarios totales: "+dao.countAll());
	}
	
	public static void main(String args[]) {
		try {
			UsuarioDAOTest test = new UsuarioDAOTest();
			//test.testFindByEmail();
			//test.testDelete();
			test.testCreate();
			//stest.testUpdate();
			//test.testExists();
			test.testCountAll();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

}

	
