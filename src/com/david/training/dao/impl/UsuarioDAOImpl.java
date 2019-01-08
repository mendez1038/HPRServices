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
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.model.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO{

	@Override
	public Usuario findByEmail(Connection connection, String email) throws DataException {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			String queryString = 
					"SELECT U.EMAIL, U.CONTRASENA, U.NOMBRE, U.APELLIDOS, U.GENERO, U.FECHA_NACIMIENTO, U.TELEFONO" +
							"FROM USUARIO U " +
							"WHERE U.EMAIL = ? ";
			preparedStatement = connection.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			Usuario e = null;

			if (resultSet.next()) {
				e = loadNext(connection, resultSet);				
			}else {}

			return e;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	}

	private Usuario loadNext(Connection connection, ResultSet resultSet) 
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
	public Usuario exists(Connection connection, Usuario u) throws DataException  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario create( Usuario u) throws DataException {
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
	public void update(Connection connection, Usuario u) throws DataException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long delete(Connection connection, Long id) throws DataException {
		// TODO Auto-generated method stub
		return 0;
	}

}
