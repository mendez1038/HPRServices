package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.david.training.dao.PaisDAO;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;

import com.david.training.model.Pais;


		public class PaisDAOImpl implements PaisDAO{
		
			@Override
			public Pais findByPais(Integer id) 
					throws Exception {
				Pais pa = null;
		
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
		
				try {
					connection = ConnectionManager.getConnection();
		
					String sql;
					sql =  "SELECT ID_PAIS, NOMBRE_PAIS, CAPITAL "
							+"FROM PAID "
							+"WHERE ID_PAIS = ? ";
		
					System.out.println("Creating statement...");
					preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		
					int i = 1;
					preparedStatement.setInt(i++, id);
		
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
					throw new DataException(ex);
				} finally { 
					JDBCUtils.closeResultSet(resultSet);
					JDBCUtils.closeStatement(preparedStatement);
					JDBCUtils.closeConnection(connection);
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
		
		
		
		
		
		}
