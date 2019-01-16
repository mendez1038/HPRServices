package com.david.training.service;

import java.util.Date;
import java.util.List;

import com.david.training.dao.ArtistaDAO;
import com.david.training.dao.impl.ArtistaDAOImpl;
import com.david.training.model.Artista;


public class ArtistaDAOTest {

	private ArtistaDAO dao = null;

	public ArtistaDAOTest() {
		dao = new ArtistaDAOImpl();
	}

	public void testFindById()
			throws Exception {
		Artista a = dao.findById(20);
		System.out.println(a);

	}

	public void testFindByNombre()
			throws Exception {
		List<Artista> artistas = dao.findByNombre("sa");
		for(Artista a: artistas) {
			System.out.println(a);
		}
	}

	public void testCreate()
			throws Exception{
		Artista a= new Artista();

		a.setNombreArtista("DAVID CABALLERO");
		a.setFechaNacimiento(new Date());
		dao.create(a);
		System.out.println(a);
	}

	public void testFindAll()
			throws Exception{

		List<Artista> results = null;

		results = dao.findAll();

		for (Artista a : results) {
			System.out.println(a);
		}
	}

	public static void main(String args[]) {
		try {
			ArtistaDAOTest test = new ArtistaDAOTest();
			test.testFindById();
			test.testFindByNombre();
			test.testCreate();
			test.testFindAll();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}

}
