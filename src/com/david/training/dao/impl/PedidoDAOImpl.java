package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.david.training.dao.PedidoDAO;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Pedido;



public class PedidoDAOImpl implements PedidoDAO{

	@Override
	public Pedido findById(Integer id) throws DataException {
		// TODO Auto-generated method stub
		
	

		Pedido p = null;
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionManager.getConnection();
			String queryString = 
					"SELECT P.ID_PEDIDO, P.FECHA_PEDIDO, P.PRECIO_TOTAL, P.EMAIL " +
							"FROM PEDIDO P " +
							"WHERE P.ID_PEDIDO = ? ";


			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(queryString,
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
			JDBCUtils.closeConnection(connection);
		}

	}

	private Pedido loadNext( ResultSet resultSet) 
	// TODO Auto-generated method stub
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

	

}
