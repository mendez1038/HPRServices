package com.david.training.service;

import java.util.Date;
import java.util.List;

import com.david.training.dao.DescuentoDAO;
import com.david.training.dao.impl.DescuentoDAOImpl;
import com.david.training.model.Descuento;


public class DescuentoDAOTest {

	private DescuentoDAO dao = null;

	public DescuentoDAOTest() {
		dao = new DescuentoDAOImpl();
	}

	public void testFindById()
			throws Exception {

		Descuento d2 = dao.findById(9);
		System.out.println(d2);
	}



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

	public void testDelete()	
			throws Exception{
		long x;
		Integer id = 12;
		x = dao.delete(id);
		System.out.println("Descuento con id "+id+" eliminado.");

	}

	public void testUpdate()
			throws Exception{

	}

	public void testFindAll() 
			throws Exception{

		List<Descuento> results = null;

		results = dao.findAll();

		for (Descuento d: results) {
			System.out.println(d);
		}
	}


	public static void main(String args[]) {
		try {
			DescuentoDAOTest test = new DescuentoDAOTest();
			test.testFindById();
			//test.testCreate();
			test.testDelete();
			test.testFindAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

