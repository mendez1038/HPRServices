package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.david.training.dao.UsuarioDAO;

import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;
import com.david.training.util.PasswordEncryptionUtil;



public class UsuarioDAOImpl implements UsuarioDAO{


	public UsuarioDAOImpl () {

	}

	@Override
	public Usuario findByEmail( String email, Connection c) 
			throws DataException {
		// TODO Auto-generated method stub
		Usuario e = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"SELECT EMAIL, CONTRASENA, NOMBRE, APELLIDOS, GENERO, FECHA_NACIMIENTO, TELEFONO " +
							"FROM USUARIO  " +
					"WHERE EMAIL = ? ");


			System.out.println("Creating statement...");
			preparedStatement = c.prepareStatement(queryString.toString(),
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
	public Boolean exists( String email, Connection c) throws DataException  {

		boolean exist = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {

			queryString = new StringBuilder("SELECT EMAIL, CONTRASENA, NOMBRE, APELLIDOS, GENERO, FECHA_NACIMIENTO, TELEFONO " + 
					"FROM USUARIO  "  + 
					"WHERE EMAIL = ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setString(i++, email);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;

	}

	@Override
	public Usuario create( Usuario u, Connection c)
			throws DataException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
		queryString = new StringBuilder("INSERT INTO USUARIO(EMAIL, CONTRASENA) "
					+ "VALUES (?,?)");

			preparedStatement = c.prepareStatement(queryString.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, u.getEmail());
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(u.getContrasena()));
			
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
			JDBCUtils.closeStatement(preparedStatement);
			}
	}

	@Override
	public boolean update( Usuario u, Connection c) 
			throws Exception {

		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder("UPDATE USUARIO ");
			boolean first = true;
			if(u.getContrasena()!=null) {
				addUpdate(queryString,first, "CONTRASENA = ? ");
				first=false;
			}
			if(u.getNombre()!=null) {
				addUpdate(queryString,first, "NOMBRE = ? ");
				first=false;
			}
			if (u.getApellidos()!=null) {
				addUpdate(queryString,first, "APELLIDOS = ? ");
				first=false;
			}
			if(u.getGenero()!=null) {
				addUpdate(queryString,first, "GENERO = ? ");
				first = false;
			}
			if(u.getFechaNacimiento()!=null) {
				addUpdate(queryString,first, "FECHA_NACIMIENTO = ? ");
				first=false;
			}
			if(u.getTelefono()!=null) {
				addUpdate(queryString,first, "TELEFONO = ? ");
				first=false;
			}
					
			queryString.append("WHERE EMAIL= ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;  
			if (u.getContrasena()!=null)
			preparedStatement.setString(i++, PasswordEncryptionUtil.encryptPassword(u.getContrasena()));
			if (u.getNombre()!=null)
			preparedStatement.setString(i++, u.getNombre());
			if (u.getApellidos()!=null)
			preparedStatement.setString(i++, u.getApellidos());
			if (u.getGenero()!=null)
			preparedStatement.setString(i++, u.getGenero());
			if (u.getFechaNacimiento()!=null)
			preparedStatement.setDate(i++, new java.sql.Date(u.getFechaNacimiento().getTime()));
			if (u.getTelefono()!=null)
			preparedStatement.setString(i++, u.getTelefono());
			
			
			preparedStatement.setString(i++, u.getEmail());


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) 
			{
				throw new SQLException("Can not uppdate row to table 'USUARIO'");
			} 
			 return true;


		} catch (SQLException ex) {
			throw new Exception(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}


	}

	@Override
	public   long delete( String email, Connection c) throws DataException {
		// TODO Auto-generated method stub
		
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {
			

			queryString = new StringBuilder("DELETE FROM USUARIO "
					+ "WHERE EMAIL = ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

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

	@Override
	public long countAll(Connection c) 
			throws Exception {
 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					" SELECT count(*) "
							+ " FROM Usuario");

			preparedStatement = c.prepareStatement(queryString.toString());

			resultSet = preparedStatement.executeQuery();

			int i=1;
			if (resultSet.next()) {
				return resultSet.getLong(i++);
			} else {
				throw new DataException("Unexpected condition trying to retrieve count value");
			}

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}	    	 

	}
	
	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? " SET ": " , ").append(clause);
	}
	


}
