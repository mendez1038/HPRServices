package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.david.training.dao.ContenidoDAO;

import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Contenido;

public class ContenidoDAOImpl implements ContenidoDAO{


	
	public ContenidoDAOImpl() {
		 
	}

	public Contenido findById(Integer id)
			throws Exception {

		Contenido c = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try{

			//metodo connectionmanager
			connection = ConnectionManager.getConnection();


			sql = new StringBuilder("SELECT ID_CONTENIDO, TITULO, RESTRICCION_EDAD, DESCRIPCCION_CONTENIDO, ANO, PORTADA, FECHA_LANZAMIENTO, ID_DESCUENTO "
					+"FROM CONTENIDO "
					+"WHERE ID_CONTENIDO = ? ");

			//STEP 4: Execute a query

			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


			// Establecer parametros
			int i = 1;
			preparedStatement.setInt(i++, id );
			resultSet = preparedStatement.executeQuery(); 

			//STEP 5: Extract data from result set	
			if (resultSet.next()) {
				c = loadNext(resultSet);
			} else {
				throw new Exception("Non se encontrou o contenido "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Contenido"+id+" duplicado");
			}
				
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
		
		return c;
		
	}


	public List<Contenido> findByTitulo(String title) 
			throws Exception {
		
		

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();
			String sql = "SELECT  ID_CONTENIDO, TITULO, RESTRICCION_EDAD, DESCRIPCCION_CONTENIDO, PORTADA, FECHA_LANZAMIENTO, ID_DESCUENTO "
					+ "FROM CONTENIDO "
					+ "WHERE "
					+ "TITULO LIKE ? ";
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, "%" +title+ "%");
			
			resultSet = preparedStatement.executeQuery();
			
			List<Contenido> contenidos = new ArrayList<Contenido>();
			Contenido c = null;
			while (resultSet.next()) {
				c = loadNext(resultSet);
				contenidos.add(c);
			} return contenidos;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		} 


	}

	public Contenido create (Contenido c)
			throws Exception {
		
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {          

			connection = ConnectionManager.getConnection();
			
			String sql = "INSERT INTO CONTENIDO(ID_CONTENIDO, TITULO, RESTRICCION_EDAD, "
					+ "DESCRIPCCION_CONTENIDO, PORTADA, FECHA_LANZAMIENTO, ID_DESCUENTO )"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setInt(i++, c.getIdContenido());
			preparedStatement.setString(i++, c.getTitulo());
			preparedStatement.setString(i++, c.getRestriccionEdad());
			preparedStatement.setString(i++, c.getDescripcionContenido());
			
			preparedStatement.setString(i++, c.getPortada());
			preparedStatement.setDate(i++, new java.sql.Date(c.getFechaLanzamiento().getTime()));
			//preparedStatement.setInt(i++, c. );
			
			
			int insertedRows = preparedStatement.executeUpdate();
			
			if (insertedRows == 0) {
				throw new SQLException("No se puede añadir fila a 'Contenido'");
			}
			
			
			return c;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
			JDBCUtils.closeConnection(connection);
			
		}
		
		
	}

	public boolean update (Contenido c)
			throws Exception {
		return false;
	}
	
	private Contenido loadNext(ResultSet resultSet) throws Exception {
		Contenido c = new Contenido();
		int i = 1;
		Integer idContenido = resultSet.getInt(i++);
		String titulo = resultSet.getString(i++);
		String restriccionEdad = resultSet.getString(i++);
		String descripcionContenido = resultSet.getString(i++);
		
		String portada = resultSet.getString(i++);
		Date fechaLanzamiento = resultSet.getDate(i++);
		

		c = new Contenido();
		
		c.setIdContenido(idContenido);
		c.setTitulo(titulo);
		c.setRestriccionEdad(restriccionEdad);
		c.setDescripcionContenido(descripcionContenido);
		
		c.setPortada(portada);
		c.setFechaLanzamiento(fechaLanzamiento);
		
		
		
		//Descuento d = descuentoDAO.findById(i);
		//c.se
		return c;
	}

	@Override
	public List<Contenido> findByContenidoCriteria() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
