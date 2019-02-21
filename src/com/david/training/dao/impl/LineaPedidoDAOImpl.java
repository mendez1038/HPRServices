package com.david.training.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;
import com.david.training.model.LineaPedidoId;
import com.david.training.model.Usuario;



public class LineaPedidoDAOImpl implements LineaPedidoDAO{

	public static Logger logger = LogManager.getLogger(LineaPedidoDAOImpl.class);
	public LineaPedidoDAOImpl() {
		
	}
	@Override
	public LineaPedido findById(Connection c, LineaPedidoId id) 
			throws InstanceNotFoundException,DataException {
		logger.debug("Id Linea Pedido = {}", id);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {  
			queryString = new StringBuilder(
					"SELECT LP.ID_PEDIDO, LP.ID_CONTENIDO, LP.PRECIO_UNIDAD " + 
					"FROM LINEAPEDIDO LP  " +
					"WHERE LP.ID_PEDIDO = ? AND LP.ID_CONTENIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, id.getIdPedido());
			preparedStatement.setInt(i++, id.getIdContenido());

			resultSet = preparedStatement.executeQuery();

			LineaPedido lp = null;

			if (resultSet.next()) {
				lp = loadNext( resultSet);				
			} else {
				throw new InstanceNotFoundException("PedidoDetails not found", Usuario.class.getName());
				
			}

			return lp;

		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}  

	}	

	@Override
	public List<LineaPedido> findByPedido(Connection c,  Integer idPedido) 
			throws DataException {
		logger.debug("Id = {}", idPedido);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"SELECT LP.ID_PEDIDO, LP.ID_CONTENIDO, LP.PRECIO_UNIDAD " + 
					"FROM LINEAPEDIDO LP " +
					"INNER JOIN PEDIDO P "+
					"ON LP.ID_PEDIDO = P.ID_PEDIDO AND P.ID_PEDIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, idPedido);

			resultSet = preparedStatement.executeQuery();

			List<LineaPedido> results = new ArrayList<LineaPedido>();  

			LineaPedido lp = null;

			while (resultSet.next()) {
				lp = loadNext (resultSet);
				results.add(lp);
			}
			return results;

		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	@Override
	public LineaPedido create(Connection c,  LineaPedido lp) 
			throws DuplicateInstanceException, DataException {
		logger.debug("Linea Pedido = {}", lp);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder(
					"INSERT INTO LINEAPEDIDO(ID_PEDIDO,ID_CONTENIDO,PRECIO_UNIDAD) "
					+ "VALUES (?, ?, ?)");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;     
			preparedStatement.setInt(i++,lp.getIdPedido());
			preparedStatement.setInt(i++,lp.getIdContenido());
			preparedStatement.setDouble(i++,lp.getPrecioUnidad());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'LINEAPEDIDO'");
			}

			return lp;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}

	@Override
	public long delete(Connection c, LineaPedidoId id) 
			throws InstanceNotFoundException, DataException {
		 logger.debug("Id linea pedido = {} ", id);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {

			queryString =	new StringBuilder(
					"DELETE FROM LINEAPEDIDO " 
					+ "WHERE ID_PEDIDO = ? AND ID_CONTENIDO = ? ");


			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setInt(i++, id.getIdPedido());
			preparedStatement.setInt(i++,id.getIdContenido());

			int removedRows = preparedStatement.executeUpdate();
			if (removedRows == 0) {
				throw new InstanceNotFoundException(id, LineaPedido.class.getName());
			} 
			return removedRows;

		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
	}

	private LineaPedido loadNext( ResultSet resultSet) 
	
			throws DataException, SQLException {

		int i = 1;


		Integer idPedido = resultSet.getInt(i++);
		Integer idContenido = resultSet.getInt(i++);
		Double precioUnidad = resultSet.getDouble(i++);


		LineaPedido lp = new LineaPedido();

		lp.setIdPedido(idPedido);
		lp.setIdContenido(idContenido);
		lp.setPrecioUnidad(precioUnidad);


		return lp;
	}
}
