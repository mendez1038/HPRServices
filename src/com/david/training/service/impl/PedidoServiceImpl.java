package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.PedidoDAO;
import com.david.training.dao.impl.LineaPedidoDAOImpl;
import com.david.training.dao.impl.PedidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.model.LineaPedido;
import com.david.training.model.Pedido;
import com.david.training.service.PedidoService;

public class PedidoServiceImpl implements PedidoService{
	
	private PedidoDAO dao = null;
	private LineaPedidoDAO daoLp = null;
	
	public PedidoServiceImpl() {
		dao = new PedidoDAOImpl();
		daoLp = new LineaPedidoDAOImpl();
	}

	@Override
	public List<Pedido> historial(String email) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Pedido> historial = dao.findByUsuario(c, email);
			
			commit = true; 
			return historial;
		}  catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
	}

	@Override
	public List<LineaPedido> carrito(LineaPedido lp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineaPedido> historialAmpliado(Integer id) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<LineaPedido> ampliacion = daoLp.findByPedido(c, id);
			
			commit = true; 
			return ampliacion;
		}  catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

}
