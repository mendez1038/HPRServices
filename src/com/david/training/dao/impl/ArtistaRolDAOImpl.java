package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.ArtistaRolDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.ArtistaRol;


public class ArtistaRolDAOImpl implements ArtistaRolDAO{
	
	public static Logger logger = LogManager.getLogger(ArtistaRolDAOImpl.class);
	
	public ArtistaRolDAOImpl() {
	
	}

	@Override
	public List<ArtistaRol> findByContenido(Integer idContenido, String idioma, Connection c) 
			throws DataException {
		logger.debug("Id = {} idioma = {}", idContenido, idioma);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<ArtistaRol> lista = new ArrayList<>();
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					 "SELECT A.ID_ARTISTA, A.NOMBRE_ARTISTA, R.ID_ROL, IR.NOMBRE_ROL " +
				                "FROM CONTENIDO_ROL_ARTISTA CRA " +
				                "INNER JOIN ARTISTA A ON CRA.ID_ARTISTA = A.ID_ARTISTA " +
				                "INNER JOIN ROL R ON CRA.ID_ROL = R.ID_ROL " +
				                "LEFT JOIN IDIOMA_ROL IR ON R.ID_ROL = IR.ID_ROL AND IR.ID_IDIOMA = ? " + 
				                "WHERE CRA.ID_CONTENIDO = ? " +
				                "ORDER BY IR.NOMBRE_ROL, A.NOMBRE_ARTISTA");
			
			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setString(i++,idioma);            
			preparedStatement.setInt(i++, idContenido);

			resultSet = preparedStatement.executeQuery();

			 while (resultSet.next()) {
	                ArtistaRol ar = new ArtistaRol();
	                ar.setIdArtista(resultSet.getInt("ID_ARTISTA"));
	                ar.setIdRol(resultSet.getString("ID_ROL"));
	                ar.setNombreArtista(resultSet.getString("NOMBRE_ARTISTA"));
	                ar.setNombreRol(resultSet.getString("NOMBRE_ROL"));
	                lista.add(ar);
	            }
		}catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return lista;
		
	}

}
