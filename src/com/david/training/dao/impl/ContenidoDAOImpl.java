package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public class ContenidoDAOImpl implements ContenidoDAO{



	public ContenidoDAOImpl() {

	}

	public Contenido findById(Connection connection, Integer id, String idioma)
			throws Exception {

		Contenido c = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try{

			//metodo connectionmanager

			sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO "
					+"FROM CONTENIDO C INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+ ""
					+"WHERE C.ID_CONTENIDO = ? AND CI.ID_IDIOMA = ? ");

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Establecer parametros
			int i = 1;
			preparedStatement.setInt(i++, id );
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery(); 

			//STEP 5: Extract data from result set	
			if (resultSet.next()) {
				c = loadNext(resultSet);
			} else {
				throw new Exception("Non se encontrou o contenido "+id);
			}
			if (resultSet.next()) {
				throw new Exception("Contenido"+id+" duplicado");
			}

		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
		return c;		
	}


	public List<Contenido> findByTitulo(Connection connection, String title, String idioma) 
			throws Exception {

		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {
			
			sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO "  
					+ "FROM CONTENIDO C INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+ "WHERE "
					+ "CI.TITULO LIKE ? AND CI.ID_IDIOMA = ? ");
			System.out.println("Creating statement...");
			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i= 1;
			preparedStatement.setString(i++, "%" +title+ "%");
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery();

			List<Contenido> contenidos = new ArrayList<Contenido>();
			Contenido c = null;
			while (resultSet.next()) {
				c = loadNext(resultSet);
				contenidos.add(c);
			} return contenidos;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 

	}

	public Contenido create (Connection connection, Contenido c)
			throws Exception {

		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try {          


			sql = new StringBuilder("INSERT INTO CONTENIDO(ID_CONTENIDO, TITULO, RESTRICCION_EDAD, "
					+ "PORTADA, FECHA_LANZAMIENTO, DESCRIPCION_BREVE, PRECIO, DURACION, ID_DESCUENTO, ID_TIPO_CONTENIDO )"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setInt(i++, c.getIdContenido());
			preparedStatement.setString(i++, c.getTitulo());
			preparedStatement.setString(i++, c.getRestriccionEdad());
			preparedStatement.setString(i++, c.getPortada());
			preparedStatement.setDate(i++, new java.sql.Date(c.getFechaLanzamiento().getTime()));
			preparedStatement.setString(i++, c.getDescripcionBreve());
			preparedStatement.setDouble(i++, c.getPrecio());
			preparedStatement.setInt(i++, c.getDuracion());
			preparedStatement.setInt(i++, c.getIdDescuento());
			preparedStatement.setString(i++, c.getTipoContenido() );

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("No se puede añadir fila a 'ARTISTA'");
			}

			return c;
		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}	
	}



	private Contenido loadNext(ResultSet resultSet) throws Exception {
		Contenido c = new Contenido();
		int i = 1;
		Integer idContenido = resultSet.getInt(i++);
		String titulo = resultSet.getString(i++);
		String restriccionEdad = resultSet.getString(i++);
		String portada = resultSet.getString(i++);
		Date fechaLanzamiento = resultSet.getDate(i++);
		String descripcionBreve = resultSet.getString(i++);
		Double precio = resultSet.getDouble(i++);
		Double precioDescontado = resultSet.getDouble(i++);
		Integer duracion = resultSet.getInt(i++);
		Integer idDescuento = resultSet.getInt(i++);
		String tipoContenido = resultSet.getString(i++);


		c = new Contenido();

		c.setIdContenido(idContenido);
		c.setTitulo(titulo);
		c.setRestriccionEdad(restriccionEdad);
		c.setPortada(portada);
		c.setFechaLanzamiento(fechaLanzamiento);
		c.setDescripcionBreve(descripcionBreve);
		c.setPrecio(precio);
		c.setPrecioDescontado(precioDescontado);
		c.setDuracion(duracion);
		c.setIdDescuento(idDescuento);
		c.setTipoContenido(tipoContenido);




		//Descuento d = descuentoDAO.findById(i);
		//c.se
		return c;
	}

	@Override
	public List<Contenido> findByCriteria(Connection connection, ProductoCriteria producto, String idioma) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Connection connection, Contenido d, String idioma) throws Exception {

		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {          


			queryString = new StringBuilder("UPDATE CONTENIDO "
					+ "SET TITULO = ?, "
					+ "SET RESTRICCION_EDAD = ?, "
					+ "SET PORTADA = ?, "
					+ "SET FECHA_LANZAMIENTO = ?,  "
					+ "SET DESCRIPCION_BREVE = ?, "
					+ "SET PRECIO = ?,  "
					+ "SET FECHA_DURACION = ?,  "
					+ "SET ID_DESCUENTO = ?,  "
					+ "SET ID_TIPO_CONTENIDO = ?  "
					+ "WHERE EMAIL= ? ");

			preparedStatement = connection.prepareStatement(queryString.toString());

			int i = 1;     			

			preparedStatement.setString(i++, d.getTitulo());
			preparedStatement.setString(i++, d.getRestriccionEdad());
			preparedStatement.setString(i++, d.getPortada());
			preparedStatement.setDate(i++, new java.sql.Date(d.getFechaLanzamiento().getTime()));
			preparedStatement.setString(i++, d.getDescripcionBreve());
			preparedStatement.setDouble(i++, d.getPrecio());
			preparedStatement.setInt(i++, d.getDuracion());
			preparedStatement.setInt(i++, d.getIdDescuento());
			preparedStatement.setString(i++, d.getTipoContenido());
			preparedStatement.setInt(i++, d.getIdContenido());


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) 
			{

				throw new SQLException("No se pudo actualizar la tabla 'CONTENIDO'");

			} 
			else { return true;}

			//...


		} catch (SQLException ex) {
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
			
		}

	}

	@Override
	public long delete(Connection connection, Integer id) throws Exception {
		
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;

		try {
	
			queryString = new StringBuilder("DELETE FROM CONTENIDO "
					+ "WHERE ID_CONTENIDO = ? ");

			preparedStatement = connection.prepareStatement(queryString.toString());

			int i =1;
			preparedStatement.setInt(i++, id);

			long removedRows = preparedStatement.executeUpdate(); 

			return removedRows;

		} catch (SQLException e) {
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);
			
		}


	}

	@Override
	public List<Contenido> anadirFavoritos(Connection c, Contenido c2, String idioma) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
