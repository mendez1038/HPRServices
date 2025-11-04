package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.FavoritoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Favorito;
import com.david.training.model.Usuario;

public class FavoritoDAOImpl implements FavoritoDAO{

	public static Logger logger = LogManager.getLogger(FavoritoDAOImpl.class);
	
	public FavoritoDAOImpl() {
	}

	
	@Override
	public Favorito create(Favorito f, Connection connection) 
			throws DuplicateInstanceException, DataException {
		
		logger.debug("Favorito = {}",f);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
		queryString = new StringBuilder("INSERT INTO USUARIO_CONTENIDO(EMAIL, ID_CONTENIDO, FAVORITO) "
					+ "VALUES (?,?,1)");

			preparedStatement = connection.prepareStatement(queryString.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, f.getEmail());
			preparedStatement.setInt(i++, f.getIdContenido());
			
			// Execute query
			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'Usuario_Contenido'");
			}


			// Return the DTO
			return f;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			}
	}

	@Override
	public Favorito updateEliminarFavoritos(Favorito f, Connection c) 
			throws InstanceNotFoundException, DataException {
		logger.debug("Favorito = {} ", f);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder("UPDATE USUARIO_CONTENIDO "
					+ "SET FAVORITO = 0 "
					+ "WHERE EMAIL= ? AND ID_CONTENIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;  
			preparedStatement.setString(i++, f.getEmail());
			preparedStatement.setInt(i++, f.getIdContenido());
			
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(f.getEmail(), Usuario.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for email = '" + 
						f.getEmail() + "' in table 'Usuario_contenido'");
			}     
			
			 return f;


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}
		
	}

	@Override
	public Favorito updateAnadirFavoritos(Favorito f, Connection c) 
			throws InstanceNotFoundException, DataException {
		logger.debug("Favorito = {} ", f);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder("UPDATE USUARIO_CONTENIDO "
					+ "SET FAVORITO = 1 "
					+ "WHERE EMAIL= ? AND ID_CONTENIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;  
			preparedStatement.setString(i++, f.getEmail());
			preparedStatement.setInt(i++, f.getIdContenido());
			
			int updatedRows = preparedStatement.executeUpdate();

			if (updatedRows == 0) {
				throw new InstanceNotFoundException(f.getEmail(), Usuario.class.getName());
			}

			if (updatedRows > 1) {
				throw new SQLException("Duplicate row for email = '" + 
						f.getEmail() + "' in table 'Usuario_contenido'");
			}     
			
			 return f;


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);			
		}
		
	}
	
	@Override
	public Boolean existsFavorito(String email, Integer idContenido, Connection c) 
			throws DataException {
		logger.debug("Email = {} IdContenido = {}", email, idContenido);
		boolean exist = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {

			queryString = new StringBuilder("SELECT EMAIL, ID_CONTENIDO, FAVORITO " + 
					"FROM USUARIO_CONTENIDO "
					+ "WHERE EMAIL = ? AND ID_CONTENIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setString(i++, email);
			preparedStatement.setInt(i++, idContenido);

			
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;
	}
	
	@Override
	public Boolean esFavorito(String email, Integer idContenido, Connection c) 
			throws DataException {
		logger.debug("Email = {} IdContenido = {}", email, idContenido);
		boolean exist = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {

			queryString = new StringBuilder("SELECT EMAIL, ID_CONTENIDO, FAVORITO " + 
					"FROM USUARIO_CONTENIDO "
					+ "WHERE EMAIL = ? AND ID_CONTENIDO = ? AND FAVORITO = 1 ");

			preparedStatement = c.prepareStatement(queryString.toString());

			int i = 1;
			preparedStatement.setString(i++, email);
			preparedStatement.setInt(i++, idContenido);

			
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

		return exist;
	}


	@Override
	public int deleteByEmail(Connection c, String email) throws DataException {
	    PreparedStatement ps = null;
	    String sql = "DELETE FROM USUARIO_CONTENIDO WHERE EMAIL = ?";
	    try {
	        ps = c.prepareStatement(sql);
	        ps.setString(1, email);
	        int rows = ps.executeUpdate();
	        return rows; // número de filas eliminadas (puede ser 0 si no tenía favoritos)
	    } catch (SQLException e) {
	        logger.warn("Error al eliminar favoritos por email {}: {}", email, e.getMessage());
	        throw new DataException(e);
	    } finally {
	        JDBCUtils.closeStatement(ps);
	    }
	}


}
