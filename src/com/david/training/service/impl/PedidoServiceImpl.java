package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.PedidoDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.impl.LineaPedidoDAOImpl;
import com.david.training.dao.impl.PedidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.model.LineaPedido;
import com.david.training.model.LineaPedidoId;
import com.david.training.model.Pedido;
import com.david.training.service.PedidoService;



public class PedidoServiceImpl implements PedidoService{
	
	private PedidoDAO dao = null;
	private LineaPedidoDAO daoLp = null;
	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	
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
			logger.warn(e.getMessage(),e);
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
	}
	
	@Override
	public Pedido carrito(Pedido p) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		p = dao.create(c,p);

		commit=true;
		
		return p;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public LineaPedido carritoAmplidado(LineaPedido lp) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		lp = daoLp.create(c,lp);

		commit=true;
		
		return lp;
		
		}catch(SQLException ed) {
			ed.printStackTrace();
			throw ed;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}
	
	@Override
	public void eliminarLineaPedido(LineaPedidoId id) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
	          
            c = ConnectionManager.getConnection();

            c.setAutoCommit(false);

            daoLp.delete(c, id);
            commit = true;
            
        } catch (SQLException ed) {
            throw new Exception(ed);

        } finally {
        	JDBCUtils.closeConnection(c, commit);
        }
		
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
			logger.warn(e.getMessage(),e);
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

}
