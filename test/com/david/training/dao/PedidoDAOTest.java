package com.david.training.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.david.training.dao.PedidoDAO;
import com.david.training.dao.impl.PedidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.model.Pedido;

public class PedidoDAOTest {

	private PedidoDAO dao = null;
	
	public PedidoDAOTest() {
		dao = new PedidoDAOImpl();
	}
		
	
	public void testFindById()
		throws Exception {
		Connection c = ConnectionManager.getConnection();
		Pedido p = dao.findById(c,10);
		System.out.println(p);
	}
	
	public void testFindByUsuario() 
			throws Exception{
			System.out.println("Buscando pedidos del usuario solicitado ...");
			Connection c = ConnectionManager.getConnection();
			List<Pedido> pedidos = dao.findByUsuario(c,"AA@A.COM");
				
			
				for (Pedido p: pedidos) {
					System.out.println(p);
				}
				
		}
	public void testCreate()
			throws Exception{
		System.out.println("Creando pedido ...");
		Connection c = ConnectionManager.getConnection();
		Pedido p = new Pedido();
		
		p.setIdPedido(1);
		p.setFechaPedido(new Date());
		p.setPrecioTotal(2.99);
		p.setEmail("");
		dao.create(c,p);
		System.out.println(p);
	}
	
	public void testDelete()
			throws Exception{
	System.out.println("Eliminando pedido ...");
	Connection c = ConnectionManager.getConnection();
	Integer idPedido =11;
	dao.delete(c, idPedido);
	System.out.println("Pedido eliminado con id: "+idPedido);
	
	}
	
	public static  void main(String args[]) {
		try {
			PedidoDAOTest test = new PedidoDAOTest();
			//test.testFindById();
			test.testFindByUsuario();
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

	
	
	}

