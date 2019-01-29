package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.david.training.dao.PedidoDAO;

import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Pedido;
import com.david.training.exceptions.InstanceNotFoundException;




public class PedidoDAOImpl implements PedidoDAO{

	@Override
	public Pedido findById(Connection c, Integer id) throws DataException {
		
		Pedido p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			
			queryString = new StringBuilder(
					"SELECT ID_PEDIDO, FECHA_PEDIDO, PRECIO_TOTAL, EMAIL " +
							"FROM PEDIDO  " +
					"WHERE ID_PEDIDO = ? ");


			System.out.println("Creating statement...");
			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, id);

			resultSet = preparedStatement.executeQuery();



			if (resultSet.next()) {
				p = loadNext(resultSet);				
			}else {
				throw new DataException("Non se atopou pedido con id = "+id);
			} if (resultSet.next()) {
				throw new DataException("Pedido con id  "+id+" duplicado");
			}

			return p;

		} catch (SQLException e1) {
			throw new DataException(e1);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Pedido loadNext( ResultSet resultSet) 
	
			throws DataException, SQLException {

		int i = 1;


		Integer idPedido = resultSet.getInt(i++);
		Date fechaPedido = resultSet.getDate(i++);
		Double precioTotal = resultSet.getDouble(i++);
		String email = resultSet.getString(i++);

		Pedido p = new Pedido();

		p.setIdPedido(idPedido);
		p.setFechaPedido(fechaPedido);
		p.setPrecioTotal(precioTotal);
		p.setEmail(email);

		return p;
	}

	@Override
	public List<Pedido> findByUsuario(Connection c, String email) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"SELECT P.ID_PEDIDO, P.FECHA_PEDIDO, P.PRECIO_TOTAL, P.EMAIL " + 
					"FROM PEDIDO P " +
					"INNER JOIN USUARIO U "+
					"ON P.EMAIL = U.EMAIL AND P.EMAIL = ? ");

			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			List<Pedido> results = new ArrayList<Pedido>();  

			Pedido p = null;

			while (resultSet.next()) {
				p = loadNext (resultSet);
				results.add(p);
			}
			return results;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	@Override
	public Pedido create(Connection c, Pedido p) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder(
					"INSERT INTO PEDIDO(ID_PEDIDO, FECHA_PEDIDO, PRECIO_TOTAL, EMAIL) "
					+ "VALUES (?, ?, ?)");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;     
			preparedStatement.setInt(i++,p.getIdPedido());
			preparedStatement.setDate(i++, new java.sql.Date(p.getFechaPedido().getTime()));
			preparedStatement.setDouble(i++,p.getPrecioTotal());
			preparedStatement.setString(i++, p.getEmail());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'PEDIDO'");
			}

			return p;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}

	
	public void delete(Connection c, Integer idPedido) throws Exception {
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					  "DELETE FROM PEDIDO " 
					+ "WHERE ID_PEDIDO = ? ");
			
			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setLong(i++, idPedido);

			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				throw new InstanceNotFoundException(idPedido,"No se elimino el pedido correctamente");
			} 
			

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		

	
	}



}
