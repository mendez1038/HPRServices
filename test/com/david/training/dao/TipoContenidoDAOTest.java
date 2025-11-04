package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.impl.TipoContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.TipoContenido;

public class TipoContenidoDAOTest {
	
	private TipoContenidoDAO dao = null;
	
	public TipoContenidoDAOTest() {
		dao = new TipoContenidoDAOImpl();
	}
	
	public void testFindById()
			throws Exception {
			Connection c = ConnectionManager.getConnection();
			String idioma = "es";
			TipoContenido tc = dao.findById("pel", idioma, c);
			System.out.println(tc);
			
		}
	
	public void testFindByNombre()
			throws Exception {
		Connection c = ConnectionManager.getConnection();
		String idioma = "en";
		List<TipoContenido> tipos = dao.findByNombre("e", idioma, c);
		for(TipoContenido tc: tipos) {
			System.out.println(tc);
		}
	}
	
	public static void main(String args[]) {
		try {
			TipoContenidoDAOTest test = new TipoContenidoDAOTest();
			
			test.testFindByNombre();
			test.testFindById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
