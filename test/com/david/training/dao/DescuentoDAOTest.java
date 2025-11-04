
package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.impl.DescuentoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Descuento;


public class DescuentoDAOTest {

	private DescuentoDAO dao = null;

	public DescuentoDAOTest() {
		dao = new DescuentoDAOImpl();
	}

	public void testFindById()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		Descuento d2 = dao.findById(9, idioma ,c );
		System.out.println(d2);
	}


/*
	public void testCreate()
			throws Exception{
		Descuento d= new Descuento();
		d.setPorcentaje(15);
		d.setNombreOferta("hola");
		d.setFechaInicio(new Date());
		d.setFechaFin(new Date());
		dao.create(d);
		System.out.println(d);
	}
*/
	/*public void testDelete()	
			throws Exception{
		long x;
		Integer id = 12;
		x = dao.delete(id);
		System.out.println("Descuento con id "+id+" eliminado.");

	}
*/
	public void testFindAll() 
			throws Exception{
		Connection c = ConnectionManager.getConnection();
		List<Descuento> results = null;

		results = dao.findAll(c);

		for (Descuento d: results) {
			System.out.println(d);
		}
	}


	public void testFindByPorcentaje()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "es";
		List<Descuento> results = dao.findByPorcentaje(15, idioma ,c );
		for(Descuento d: results) {
			System.out.println(d);
		}
	}
	
	public static void main(String args[]) {
		
		try {
			DescuentoDAOTest test = new DescuentoDAOTest();
			test.testFindById();
			//test.testFindByPorcentaje();
			//test.testFindAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

