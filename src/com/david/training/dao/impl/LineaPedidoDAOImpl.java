package com.david.training.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		StringBuilder queryString = null;
		try {  
			connection = ConnectionManager.getConnection();
			queryString = new StringBuilder(
					"SELECT LP.ID_PEDIDO, LP.ID_CONTENIDO, LP.PRECIO_UNIDAD " + 
							"FROM LINEAPEDIDO LP  " +
					"WHERE LP.ID_PEDIDO = ? AND LP.ID_CONTENIDO = ? ");

			preparedStatement = connection.prepareStatement(queryString.toString(),
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
	public List<LineaPedido> findByPedido( Integer idPedido) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		StringBuilder queryString = null;
		try {
			connection = ConnectionManager.getConnection();
			queryString = new StringBuilder(
					"SELECT LP.ID_PEDIDO, LP.ID_CONTENIDO, LP.PRECIO_UNIDAD " + 
							"FROM LINEAPEDIDO LP " +
							"INNER JOIN PEDIDO P "+
					"ON LP.ID_PEDIDO = P.ID_PEDIDO AND P.ID_PEDIDO = ? ");

			preparedStatement = connection.prepareStatement(queryString.toString(),
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
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public LineaPedido create( LineaPedido lp) throws DataException {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {          

			connection = ConnectionManager.getConnection();
			queryString = new StringBuilder(
					"INSERT INTO LINEAPEDIDO(ID_PEDIDO,ID_CONTENIDO,PRECIO_UNIDAD) "
							+ "VALUES (?, ?, ?)");

			preparedStatement = connection.prepareStatement(queryString.toString());

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
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
			JDBCUtils.closeConnection(connection);
		}
	}

	@Override
	public long delete(Integer idPedido, Integer idContenido) throws DataException {
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {
			connection = ConnectionManager.getConnection();

			queryString =	new StringBuilder(
					"DELETE FROM LINEAPEDIDO " 
							+ "WHERE ID_PEDIDO = ? AND ID_CONTENIDO = ? ");


			preparedStatement = connection.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setInt(i++, idPedido);
			preparedStatement.setInt(i++,idContenido);

			int removedRows = preparedStatement.executeUpdate();

			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
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
