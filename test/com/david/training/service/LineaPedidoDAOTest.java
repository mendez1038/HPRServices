package com.david.training.service;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.impl.LineaPedidoDAOImpl;
import com.david.training.model.LineaPedido;

public class LineaPedidoDAOTest {
	
	private LineaPedidoDAO dao = null;
	
	public LineaPedidoDAOTest() {
		dao = new LineaPedidoDAOImpl();
	}
	
	public void testFindById() 
		throws Exception{
		
		LineaPedido lp = dao.findById(1,1);
		System.out.println(lp);
			
			
		}
	
	public static  void main(String args[]) {
		try {
			LineaPedidoDAOTest test = new LineaPedidoDAOTest();
			test.testFindById();
			;
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

	
	}


