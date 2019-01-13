package com.david.training.service;

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
	public static void main(String args[]) {
		try {
			ArtistaDAOTest test = new ArtistaDAOTest();
			test.testFindById();
			test.testFindByNombre();
			//test.testCreate();
		} catch (Exception c) {
			c.printStackTrace();
		}
	}
	
}
