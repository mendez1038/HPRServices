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
	public List<ArtistaRol> findByContenido(Integer idContenido, Connection c) 
			throws DataException {
		logger.debug("Id = {}", idContenido);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"");
			
			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, idContenido);

			resultSet = preparedStatement.executeQuery();

			List<ArtistaRol> results = new ArrayList<ArtistaRol>();  
			ArtistaRol ar = null;
			while (resultSet.next()) {
				ar = loadNext (resultSet);
				results.add(ar);
			}
			return null;
		}catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		
	} 
	
	private ArtistaRol loadNext(ResultSet resultSet) 
			throws SQLException, DataException  {
		ArtistaRol ar = new ArtistaRol();
		int i = 1;
		Integer idArtista = resultSet.getInt(i++);
		String idRol = resultSet.getString(i++);


		ar = new ArtistaRol();

		ar.setIdArtista(idArtista);
		ar.setIdRol(idRol);
		return ar;
	}

}
