package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO{
	
	
	public UsuarioDAOImpl () {
		
	}

	@Override
	public Usuario findByEmail( String email) 
			throws DataException {
		// TODO Auto-generated method stub
		Usuario e = null;
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionManager.getConnection();
			String queryString = 
					"SELECT U.EMAIL, U.CONTRASENA, U.NOMBRE, U.APELLIDOS, U.GENERO, U.FECHA_NACIMIENTO, U.TELEFONO " +
							"FROM USUARIO U " +
							"WHERE U.EMAIL = ? ";
			
			
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			

			if (resultSet.next()) {
				e = loadNext(resultSet);				
			}else {
				throw new DataException("Non se atopou usuario con email = "+email);
			} if (resultSet.next()) {
				throw new DataException("Usuario con email  "+email+" duplicado");
			}

			return e;

		} catch (SQLException e1) {
			throw new DataException(e1);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
		
	}

	private Usuario loadNext( ResultSet resultSet) 
		// TODO Auto-generated method stub
		throws DataException, SQLException {
			
		int i = 1;
		String email = resultSet.getString(i++);
		String contrasena = resultSet.getString(i++);
		String nombre = resultSet.getString(i++);
		String apellidos = resultSet.getString(i++);
		String genero = resultSet.getString(i++);
		Date fechaNacimiento = resultSet.getDate(i++);
		String telefono = resultSet.getString(i++);
		
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setContrasena(contrasena);
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setGenero(genero);
		u.setFechaNacimiento(fechaNacimiento);
		u.setTelefono(telefono);
		
		return u;
	}

	@Override
	public Usuario exists( Usuario u) throws DataException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario create( Usuario u)
			throws DataException {
		// TODO Auto-generated method stub
		Connection connection = null; 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {

			connection = ConnectionManager.getConnection();
			String queryString = "INSERT INTO USUARIO(EMAIL, CONTRASENA) "
					+ "VALUES (?,?)";
			
			preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, u.getContrasena());
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuario'");
			}
				

			// Return the DTO
			return u;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);}
		}
	
	@Override
	public boolean update( Usuario u) 
			throws DataException {
				return false;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public   long delete( String email) throws DataException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		try {
			connection = ConnectionManager.getConnection();
			
			String queryString = "DELETE FROM USUARIO "
					+ "WHERE EMAIL = ? ";
		
			preparedStatement = connection.prepareStatement(queryString);
			
			int i =1;
			preparedStatement.setString(i++, email);
			
			long removedRows = preparedStatement.executeUpdate(); 
			
			return removedRows;
			
		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

}
