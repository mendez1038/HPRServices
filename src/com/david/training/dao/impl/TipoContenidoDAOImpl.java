package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.david.training.dao.TipoContenidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;

import com.david.training.model.TipoContenido;

public class TipoContenidoDAOImpl implements TipoContenidoDAO{

	@Override
	public TipoContenido findById(String id, String idioma, Connection c) throws Exception {
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

			System.out.println("Creating statement...");
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


			int i = 1;
			preparedStatement.setString(i++, id);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				tc = loadNext(resultSet);
			} else {
				throw new Exception("No se encontró pais con id:"+id);			
			} 
			if (resultSet.next()) {
				throw new Exception("Pais "+id+" duplicado");
			}
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally { 
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return tc;

	}

	@Override
	public List<TipoContenido> findByNombre(String nombre, String idioma, Connection c) throws Exception {
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
			System.out.println("Creating statement...");
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
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);

		} 

	}

	private TipoContenido loadNext(ResultSet resultSet) throws Exception {

		int i = 1;
		String idTipoContenido = resultSet.getString(i++);
		String nombreTipoContenido = resultSet.getString(i++);


		TipoContenido tc = new TipoContenido();
		tc.setIdTipoContenido(idTipoContenido);
		tc.setNombreContenido(nombreTipoContenido);

		return tc;
	}

}
