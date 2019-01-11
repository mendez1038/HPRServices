package com.david.training.service;

import java.util.Date;

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
		Descuento d = dao.findById(1);
		System.out.println(d);

	}

	public void testFindByNombre() 
			throws Exception {

	}

	public void testCreate()
			throws Exception{
		Descuento d= new Descuento();
		
		d.setPorcentaje(10);
		d.setNombreOferta("hola");
		d.setFechaInicio(new Date());
		d.setFechaFin(new Date());
		dao.create(d);
	}

	public static void main(String args[]) {
		try {
			DescuentoDAOTest test = new DescuentoDAOTest();
			test.testFindById();
			test.testCreate();
			//test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

