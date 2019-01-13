package com.david.training.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.LineaPedido;



public class LineaPedidoDAOImpl implements LineaPedidoDAO{

	@Override
	public LineaPedido findById(Integer idp, Integer idc) throws DataException {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {  
			connection = ConnectionManager.getConnection();
			String queryString = 
					"SELECT LP.ID_PEDIDO, LP.ID_CONTENIDO, LP.PRECIO_UNIDAD " + 
							"FROM LINEAPEDIDO LP  " +
							"WHERE LP.ID_PEDIDO = ? AND LP.ID_CONTENIDO = ? ";
			
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, idp);
			preparedStatement.setInt(i++, idc);

			resultSet = preparedStatement.executeQuery();

			LineaPedido lp = null;

			if (resultSet.next()) {
				lp = loadNext( resultSet);				
			} else {
				
			}

			return lp;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}  

	}

	@Override
	public Boolean exists(LineaPedido id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineaPedido> findByPedido( Integer idPedido) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaPedido create( LineaPedido lp) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long delete(LineaPedido id) throws DataException {
		// TODO Auto-generated method stub
		return 0;
	}

	private LineaPedido loadNext( ResultSet resultSet) 
	// TODO Auto-generated method stub
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
