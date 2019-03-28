package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;
import com.david.training.service.ContenidoService;
import com.david.training.service.Results;


public class ContenidoServiceImpl implements ContenidoService{

	private ContenidoDAO dao = null;
	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	public ContenidoServiceImpl()  {
		dao = new ContenidoDAOImpl();
	}

	@Override
	public Results<Contenido> miLista(String email, String idioma, int startIndex, int count) 
			throws DataException {
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Results<Contenido> lista = dao.findLista(c, email, idioma, startIndex, count);
			commit = true; 
			return lista;
		}  catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}
	}

	@Override
	public Results<Contenido> favoritos(String email, String idioma, int startIndex, int count) 
			throws DataException {
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			Results<Contenido> favoritos = dao.findFavoritos(c, email, idioma, startIndex, count);
			commit = true; 
			return favoritos;
		}  catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
		}}



	public Double sacarPrecioDescontado(Integer id) throws DataException {
		boolean commit = false;

		Connection connection = null;
		try {

			Contenido c = new Contenido();
			connection = ConnectionManager.getConnection();


			connection.setAutoCommit(false);
			c = dao.findPorId(connection, id);
			Double precioDescontado = c.getPorcentaje()*c.getPrecio()/100;

			return precioDescontado;
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);



		}

	}

	@Override
	public void precioDescontado(Contenido c) throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			dao.update(connection, c);
			commit = true;

		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}

	}

	@Override
	public Results<Contenido> busquedaEstructurada(ProductoCriteria producto, String idioma, int startIndex, int count) 
			throws DataException {
		long t0 = 0,t1 = 0,t2 = 0,t3=0;
		boolean commit=false;
		Connection c=null;
		try {
			t0 = System.currentTimeMillis();
			c=ConnectionManager.getConnection();
			t1= System.currentTimeMillis();
			c.setAutoCommit(false);

			Results<Contenido> productos = dao.findByCriteria(c, producto, idioma, startIndex, count);
			t2= System.currentTimeMillis();
			return productos;

		}catch(SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		}finally {
			JDBCUtils.closeConnection(c, commit);
			t3= System.currentTimeMillis();

			System.out.println("Get c: "+(t1-t0)+", dao: "+(t2-t1)+" Close c: "+(t3-t2));			
		}

	}
}
