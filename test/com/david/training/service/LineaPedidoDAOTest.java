package com.david.training.service;

import java.util.List;

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
	
	public void testFindByPedido() 
		throws Exception{
		System.out.println("Buscando líneas de pedido ...");
		
			List<LineaPedido> lineas = dao.findByPedido(1);
			
		
			for (LineaPedido lp:lineas) {
				System.out.println(lp);
			}
			
	}
	
	public void testDelete() 
			throws Exception{
		long fila;
		
		Integer idPedido = 1;
		Integer idContenido = 1;
		dao.delete(idPedido, idContenido);
		System.out.println("Eliminada linea de pedido numero "+idContenido+" del pedido con id: "+idPedido);
			
		
	}
	
	public void testCreate()
			throws Exception{
		LineaPedido lp = new LineaPedido();
		
		lp.setIdPedido(1);
		lp.setIdContenido(1);
		lp.setPrecioUnidad(2.99);
		dao.create(lp);
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


