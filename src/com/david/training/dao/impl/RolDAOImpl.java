package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.david.training.dao.RolDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;

import com.david.training.model.Rol;

public class RolDAOImpl implements RolDAO{

	@Override
	public Rol findById(String id, String idioma, Connection c) throws Exception {
		Rol r = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			sql =  new StringBuilder("SELECT R.ID_ROL, IR.NOMBRE_ROL "
					+"FROM ROL R "
					+"INNER JOIN IDIOMA_ROL IR ON R.ID_ROL=IR.ID_ROL "
					+ "INNER JOIN IDIOMA I ON I.ID_IDIOMA = IR.ID_IDIOMA "
					+"WHERE R.ID_ROL = ? "
					+"AND I.ID_IDIOMA = ? ");

			System.out.println("Creating statement...");
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


			int i = 1;
			preparedStatement.setString(i++, id);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				r = loadNext(resultSet);
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
		return r;


	}

	@Override
	public List<Rol> findByNombre(String nombre, String idioma, Connection c) throws Exception {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {

			sql = new StringBuilder("SELECT R.ID_ROL, IR.NOMBRE_ROL "
					+ "FROM ROL R "
					+ "INNER JOIN IDIOMA_ROL IR ON IR.ID_ROL = R.ID_ROL "
					+ "INNER JOIN IDIOMA I ON I.ID_IDIOMA = IR.ID_IDIOMA "
					+ "WHERE "
					+ "IR.NOMBRE_ROL LIKE ? "
					+ "AND I.ID_IDIOMA = ? ");
			System.out.println("Creating statement...");
			preparedStatement = c.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, "%" +nombre+ "%");
			preparedStatement.setString(i++, idioma);

			resultSet = preparedStatement.executeQuery();

			List<Rol> roles = new ArrayList<Rol>();
			Rol r = null;
			while (resultSet.next()) {
				r = loadNext(resultSet);
				roles.add(r);
			} return roles;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);

		} 

	}
	private Rol loadNext(ResultSet resultSet) throws Exception {

		int i = 1;
		String idRol = resultSet.getString(i++);
		String nombreRol = resultSet.getString(i++);

		Rol r = new Rol();
		r.setIdRol(idRol);
		r.setNombreRol(nombreRol);
		return r;
	}





}
