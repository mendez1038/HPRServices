package com.david.training.service;

import com.david.training.dao.PedidoDAO;
import com.david.training.dao.impl.PedidoDAOImpl;
import com.david.training.model.Pedido;

public class PedidoDAOTest {

	private PedidoDAO dao = null;
	
	public PedidoDAOTest() {
		dao = new PedidoDAOImpl();
	}
		
	
	public void testFindById()
		throws Exception {
		Pedido p = dao.findById(10);
		System.out.println(p);
	}
	
	public static  void main(String args[]) {
		try {
			PedidoDAOTest test = new PedidoDAOTest();
			test.testFindById();
			;
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

	
	
	}

