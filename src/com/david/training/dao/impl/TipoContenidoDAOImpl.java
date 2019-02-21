package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.TipoContenidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.TipoContenido;

public class TipoContenidoDAOImpl implements TipoContenidoDAO{

	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);

	@Override
	public TipoContenido findById(String id, String idioma, Connection c) 
			throws InstanceNotFoundException, DataException {
		logger.debug("Id = {} Idioma = {}", id, idioma);
		TipoContenido tc = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			sql =  new StringBuilder("SELECT TC.ID_TIPO_CONTENIDO, TCI.NOMBRE_CONTENIDO "
					+"FROM TIPO_CONTENIDO TC "
					+"INNER JOIN TIPO_CONTENIDO_IDIOMA TCI ON TC.ID_TIPO_CONTENIDO=TCI.ID_TIPO_CONTENIDO "
					+ "INNER JOIN IDIOMA I ON I.ID_IDIOMA = TCI.ID_IDIOMA "
					+"WHERE TC.ID_TIPO_CONTENIDO = ? "
					+"AND I.ID_IDIOMA = ? ");

			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


			int i = 1;
			preparedStatement.setString(i++, id);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				tc = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException("Tipo contenido with id " + id + 
						"not found", TipoContenido.class.getName());
			}
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally { 
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return tc;

	}

	@Override
	public List<TipoContenido> findByNombre(String nombre, String idioma, Connection c) 
			throws DataException {
		logger.debug("Nombre = {} Idioma = {}", nombre, idioma);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			sql = new StringBuilder("SELECT TC.ID_TIPO_CONTENIDO, TCI.NOMBRE_CONTENIDO "
					+ "FROM TIPO_CONTENIDO  TC "
					+ "INNER JOIN TIPO_CONTENIDO_IDIOMA TCI ON TCI.ID_TIPO_CONTENIDO = TC.ID_TIPO_CONTENIDO "
					+ "INNER JOIN IDIOMA I ON TCI.ID_IDIOMA = I.ID_IDIOMA "
					+ "WHERE "
					+ "TCI.NOMBRE_CONTENIDO LIKE ? "
					+ "AND I.ID_IDIOMA = ? ");

			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, "%" +nombre+ "%");
			preparedStatement.setString(i++, idioma);

			resultSet = preparedStatement.executeQuery();

			List<TipoContenido> tipos = new ArrayList<TipoContenido>();
			TipoContenido tc = null;
			while (resultSet.next()) {
				tc = loadNext(resultSet);
				tipos.add(tc);
			} return tipos;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);

		} 

	}

	private TipoContenido loadNext(ResultSet resultSet) 
			throws SQLException, DataException {

		int i = 1;
		String idTipoContenido = resultSet.getString(i++);
		String nombreTipoContenido = resultSet.getString(i++);


		TipoContenido tc = new TipoContenido();
		tc.setIdTipoContenido(idTipoContenido);
		tc.setNombreContenido(nombreTipoContenido);

		return tc;
	}

}
