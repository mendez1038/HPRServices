package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.david.training.dao.ArtistaDAO;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Artista;


public class ArtistaDAOImpl implements ArtistaDAO {

	@Override
	public Artista findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Artista  a = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{

			//metodo connectionmanager
			connection = ConnectionManager.getConnection();



			String sql;
			sql ="SELECT ID_ARTISTA, NOMBRE_ARTISTA, FECHA_NACIMIENTO "
					+"FROM ARTISTA "
					+"WHERE ID_ARTISTA = ? ";

			//STEP 4: Execute a query

			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


			// Establecer parametros
			int i = 1;
			preparedStatement.setInt(i++, id );
			resultSet = preparedStatement.executeQuery(); 

			//STEP 5: Extract data from result set	
			if (resultSet.next()) {
				a = loadNext(resultSet);
			} else {
				throw new Exception("Non se encontrou o artista "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Artista "+id+" duplicado");
			}
				
			
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		}
		
		return a;
		
	}

	@Override
	public List<Artista> findByNombre(String title) throws Exception {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionManager.getConnection();
			String sql = "SELECT ID_ARTISTA, NOMBRE_ARTISTA, FECHA_NACIMIENTO "
					+ "FROM ARTISTA "
					+ "WHERE "
					+ "NOMBRE_ARTISTA LIKE ? ";
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, "%" +title+ "%");
			
			resultSet = preparedStatement.executeQuery();
			
			List<Artista> artistas = new ArrayList<Artista>();
			Artista a = null;
			while (resultSet.next()) {
				a = loadNext(resultSet);
				artistas.add(a);
			} return artistas;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
			JDBCUtils.closeConnection(connection);
		} 


	}
	
	private Artista loadNext(ResultSet resultSet) throws Exception {
		Artista a = new Artista();
		int i = 1;
		Integer idArtista = resultSet.getInt(i++);
		String nombreArtista = resultSet.getString(i++);
		Date fechaNacimiento = resultSet.getDate(i++);
		

		a = new Artista();
		
		a.setIdArtista(idArtista);
		a.setNombreArtista(nombreArtista);
		a.setFechaNacimiento(fechaNacimiento);
		
		
		
		//Descuento d = descuentoDAO.findById(i);
		//c.se
		return a;
	}


}
