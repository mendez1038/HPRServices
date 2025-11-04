package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.impl.LineaPedidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.LineaPedido;

public class LineaPedidoDAOTest {
	
	private LineaPedidoDAO dao = null;
	
	public LineaPedidoDAOTest() {
		dao = new LineaPedidoDAOImpl();
	}
	
	public void testFindById() 
		throws Exception{
		Connection c = ConnectionManager.getConnection();
		System.out.println("Buscando la linea de pedido solicitada");
		LineaPedido lp = dao.findById(c, 3, 101);
		System.out.println(lp);
			
			
		}
	
	public void testFindByPedido() 
		throws Exception{
		System.out.println("Buscando l�neas del pedido solicitado ...");
		Connection c = ConnectionManager.getConnection();
			List<LineaPedido> lineas = dao.findByPedido(c,1);
			
		
			for (LineaPedido lp:lineas) {
				System.out.println(lp);
			}
			
	}
	
	public void testDelete() 
			throws Exception{
		
		Connection c = ConnectionManager.getConnection();
		
		LineaPedido id = new LineaPedido();
		id.setIdPedido(1);
		id.setIdContenido(1);
		dao.delete(c, id);
		System.out.println("Eliminada linea de pedido numero "+id.getIdContenido()+" del pedido con id: "+id.getIdPedido());
			
		
	}
	
	public void testCreate()
			throws Exception{
		System.out.println("Creando pedido ...");
		Connection c = ConnectionManager.getConnection();
		LineaPedido lp = new LineaPedido();
		
		lp.setIdPedido(1);
		lp.setIdContenido(1);
		lp.setPrecioUnidad(2.99);
		dao.create(c,lp);
		System.out.println(lp);
	}
	
	public static  void main(String args[]) {
		try {
			LineaPedidoDAOTest test = new LineaPedidoDAOTest();
			//test.testFindById();
			//test.testFindByPedido();
			//test.testDelete();
			test.testCreate();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

	
	}


