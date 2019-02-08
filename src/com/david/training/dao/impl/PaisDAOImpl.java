package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.PaisDAO;

import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;

import com.david.training.model.Pais;


public class PaisDAOImpl implements PaisDAO{

	public static Logger logger = LogManager.getLogger(PaisDAOImpl.class);
	@Override
	public Pais findById(Integer id, String idioma, Connection c) 
			throws Exception {
		logger.debug("Id = {} Idioma ={}", id, idioma);
		Pais pa = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			sql =  new StringBuilder("SELECT P.ID_PAIS, PI.NOMBRE_PAIS, P.CAPITAL "
					+"FROM PAIS P "
					+"INNER JOIN PAIS_IDIOMA PI ON P.ID_PAIS=PI.ID_PAIS "
					+"WHERE P.ID_PAIS = ? "
					+"AND ID_IDIOMA = ? ");

			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++, id);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pa = loadNext(resultSet);
			} else {
				throw new Exception("No se encontró pais con id:"+id);			
			} 
			if (resultSet.next()) {
				throw new Exception("Pais "+id+" duplicado");
			}
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally { 
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return pa;

	}
	private Pais loadNext(ResultSet resultSet) throws Exception {

		int i = 1;
		Integer idPais = resultSet.getInt(i++);
		String nombrePais = resultSet.getString(i++);
		String capital = resultSet.getString(i++);

		Pais pa = new Pais();
		pa.setIdPais(idPais);
		pa.setNombrePais(nombrePais);
		pa.setCapitalPais(capital);
		return pa;
	}
	@Override
	public List<Pais> findAll(String idioma, Connection c) throws Exception {


		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder("SELECT P.ID_PAIS, PI.NOMBRE_PAIS, P.CAPITAL "
					+ "FROM PAIS P "
					+ "INNER JOIN PAIS_IDIOMA PI ON PI.ID_PAIS = P.ID_PAIS "
					+ "WHERE ID_IDIOMA = ? ");
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			List<Pais> paises = new ArrayList<Pais>();
			Pais p = null;
			while (resultSet.next()) {
				p = loadNext(resultSet);
				paises.add(p);
			} return paises;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);

		} 

	}

}
