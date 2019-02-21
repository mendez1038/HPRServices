package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Artista;
import com.david.training.model.Categoria;
import com.david.training.model.Contenido;
import com.david.training.model.Pais;
import com.david.training.model.ProductoCriteria;


public class ContenidoDAOImpl implements ContenidoDAO{

	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	public ContenidoDAOImpl() {

	}

	public Contenido findPorId(Connection connection, Integer id)
			throws InstanceNotFoundException, DataException {
		logger.debug("Id = {} ",id);
		Contenido c = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try{
			sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, "
					+ "C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, "
					+ "CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, "
					+ "C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO, "
					+ "D.PORCENTAJE "
					+"FROM CONTENIDO C INNER JOIN DESCUENTO D ON C.ID_DESCUENTO = D.ID_DESCUENTO "
					+ "INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+"WHERE C.ID_CONTENIDO = ? ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setInt(i++, id );
			resultSet = preparedStatement.executeQuery(); 

			if (resultSet.next()) {
				c = loadNext(resultSet);
			} else {
				throw new InstanceNotFoundException("Products with id " + id + 
						"not found", Contenido.class.getName());
				}
			return c;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}
				
	}

	public Contenido findById(Connection connection, Integer id, String idioma)
			throws InstanceNotFoundException, DataException {
				logger.debug("Id = {} idioma = {)", id, idioma);
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				StringBuilder sql = null;
				Contenido c = null;
				try{
					sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO "
							+"FROM CONTENIDO C INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
							+"WHERE C.ID_CONTENIDO = ? AND CI.ID_IDIOMA = ? ");
					
					preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
					int i = 1;
					preparedStatement.setInt(i++, id );
					preparedStatement.setString(i++, idioma);
					resultSet = preparedStatement.executeQuery(); 
					if (resultSet.next()) {
						c = loadNext(resultSet);
					} else {
						throw new InstanceNotFoundException("Products with id " + id + 
								"not found", Contenido.class.getName());
						}
					
					return c;
				} catch (SQLException ex) {
					logger.warn(ex.getMessage(), ex);
					throw new DataException(ex);
				} finally {            
					JDBCUtils.closeResultSet(resultSet);
					JDBCUtils.closeStatement(preparedStatement);
				}
						
	}


	public Contenido create (Connection connection, Contenido c)
			throws DuplicateInstanceException, DataException {
		logger.debug("Contenido = {}", c);
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
				throw new SQLException("No se puede añadir fila a 'CONTENIDO'");
			}

			return c;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}	
	}

	private Contenido loadNext(ResultSet resultSet) throws SQLException, DataException {
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
		Integer porcentaje = resultSet.getInt(i++);

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
		c.setPorcentaje(porcentaje);
		return c;
	}

	@Override
	public List<Contenido> findByCriteria(Connection connection, ProductoCriteria pc, String idioma) 
			throws DataException {
		logger.debug("Producto = {} Idioma = {}", pc, idioma);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, "
					+ "C.FECHA_LANZAMIENTO, CI.DESCRIPCION_BREVE, C.PRECIO,"
					+ "C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO, "
					+ "D.PORCENTAJE, CI.ID_IDIOMA "	  
					+ "FROM CONTENIDO C "
					+ "INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+ "INNER JOIN DESCUENTO D ON D.ID_DESCUENTO = C.ID_DESCUENTO ");
			boolean first = true;

			if(!pc.getCategoria().isEmpty()) {
				queryString.append(" INNER JOIN CONTENIDO_CATEGORIA CC on C.ID_CONTENIDO=CC.ID_CONTENIDO INNER JOIN CATEGORIA CA on CC.ID_CATEGORIA=CA.ID_CATEGORIA ");
			}

			if(!pc.getPais().isEmpty()) {
				queryString.append(" inner join pais_contenido pc on c.id_contenido=pc.id_contenido inner join pais p on pc.id_pais=c.id_pais ");
			}
			
			if(pc.getA()!=null) {
				queryString.append(" inner join contenido_rol_artista cra on c.id_contenido=cra.id_contenido inner join artista a on cra.id_artista=p.id_artista ");
			}
			
			if (pc.getIdContenido()!=null) {
				addClause(queryString, first, " C.ID_CONTENIDO = ? ");
				first = false;
			}
			if (pc.getTitulo()!=null) {
				addClause(queryString, first, " UPPER(CI.TITULO) LIKE ? ");
				first = false;
			}
			
			if (pc.getRestriccionEdad()!=null) {
				addClause(queryString, first, " C.RESTRICCION_EDAD = ? ");
				first = false;
			}
			
			if (pc.getPortada()!=null) {
				addClause(queryString, first, " C.PORTADA = ? ");
				first = false;
			}

			if (pc.getFechaLanzamiento()!=null) {
				addClause(queryString, first, " C.FECHA_LANZAMIENTO = ? ");
				first = false;
			}
			
			if (pc.getDescripcionBreve()!=null) {
				addClause(queryString, first, " UPPER(CI.DESCRIPCION_BREVE) = ? ");
				first = false;
			}
			
			if (pc.getPrecio()!=null) {
				addClause(queryString, first, " C.PRECIO = ? ");
				first = false;
			}
			
			if (pc.getPrecioDescontado()!=null) {
				addClause(queryString, first, " C.PRECIO_DESCONTADO = ? ");
				first = false;
			}

			if (pc.getDuracion()!=null) {
				addClause(queryString, first, " C.DURACION = ? ");
				first = false;
			}
			
			if(pc.getIdDescuento()!=null) {
				addClause(queryString, first, " C.ID_DESCUENTO = ? ");
				first = false;}

			if(pc.getTipoContenido()!=null) {
				addClause(queryString, first, " C.TIPO_CONTENIDO = ? ");
				first = false;}
			
			if(pc.getPorcentaje()!=null) {
				addClause(queryString, first, " D.PORCENTAJE = ? ");
				first = false;}

			if(idioma!=null) {
				addClause(queryString, first, "CI.ID_IDIOMA = ? ");
				first = false;}

			if (!pc.getCategoria().isEmpty()) {
				addClause(queryString, first,addCategoria(pc.getCategoria()).toString());	
				first = false;
			}

			if (!pc.getPais().isEmpty()) {
				addClause(queryString, first,addPais(pc.getPais()).toString());	
				first = false;
			}
			
			if (pc.getA()!=null) {
				addClause(queryString, first,addArtista(pc.getA()).toString());	
				first = false;
			}


			preparedStatement = connection.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			if(pc.getIdContenido()!=null) 
				preparedStatement.setInt(i++, pc.getIdContenido());
			if(pc.getTitulo()!=null) 
				preparedStatement.setString(i++, "%" +  pc.getTitulo() + "%");
			if(pc.getRestriccionEdad()!=null) 
				preparedStatement.setString(i++, pc.getRestriccionEdad());
			if(pc.getPortada()!=null) 
				preparedStatement.setString(i++, "%" +  pc.getPortada() + "%");
			if(pc.getFechaLanzamiento()!=null) {
				preparedStatement.setDate(i++, new java.sql.Date(pc.getFechaLanzamiento().getTime()));}
			if(pc.getDescripcionBreve()!=null) 
				preparedStatement.setString(i++, "%" +  pc.getDescripcionBreve() + "%");
			if(pc.getPrecio()!=null) {
				preparedStatement.setDouble(i++, pc.getPrecio());}
			if(pc.getPrecioDescontado()!=null) {
				preparedStatement.setDouble(i++, pc.getPrecioDescontado());}
			if(pc.getDuracion()!=null) {
			preparedStatement.setInt(i++, pc.getDuracion());}
			if(pc.getIdDescuento()!=null) {
				preparedStatement.setInt(i++, pc.getIdDescuento());}
			if(pc.getTipoContenido()!=null) {
			preparedStatement.setString(i++, pc.getTipoContenido());}
			if(pc.getPorcentaje()!=null) {
				preparedStatement.setInt(i++, pc.getPorcentaje());}
			if(idioma!=null)
			preparedStatement.setString(i++, idioma);

			resultSet = preparedStatement.executeQuery();

			List<Contenido> results = new ArrayList<Contenido>();                        
			Contenido e = null;

			while (resultSet.next()) {
				e=loadNext(resultSet);
				results.add(e);	
			}
			return results;
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	@Override
	public boolean update(Connection connection, Contenido d) throws Exception {
		logger.debug("Contenido = {}", d);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder("UPDATE CONTENIDO ");
			boolean first = true;
			if(d.getPrecioDescontado()!=null) {
				addUpdate(queryString,first,"PRECIO_DESCONTADO = ? ");
				first = false;
			}
			queryString.append("WHERE ID_CONTENIDO = ? ");

			preparedStatement = connection.prepareStatement(queryString.toString());

			int i = 1;     			
			if(d.getPrecioDescontado()!=null)
				preparedStatement.setDouble(i++, d.getPrecioDescontado());
			preparedStatement.setInt(i++, d.getIdContenido());

			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) 
			{

				throw new SQLException("No se pudo actualizar la tabla 'CONTENIDO'");

			} 
			else { return true;}

			//...


		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {

			JDBCUtils.closeStatement(preparedStatement);			

		}

	}

	@Override
	public long delete(Connection connection, Integer id) 
			throws InstanceNotFoundException, DataException {
		logger.debug("Id = {}", id);
		PreparedStatement preparedStatement = null;
		StringBuilder queryString = null;

		try {
			queryString = new StringBuilder("DELETE FROM CONTENIDO "
					+ "WHERE ID_CONTENIDO = ? ");

			preparedStatement = connection.prepareStatement(queryString.toString());
			
			int i =1;
			preparedStatement.setInt(i++, id);

			long removedRows = preparedStatement.executeUpdate(); 

			if (removedRows == 0) {
				throw new InstanceNotFoundException(id,Contenido.class.getName());
			} 
			
			return removedRows;

		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeStatement(preparedStatement);

		}
	}

	@Override
	public List<Contenido> findLista(Connection connection, String email, String idioma) 
			throws DataException {
		logger.debug("Email = {} Idioma = {}", email, idioma);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try{
			sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, "
					+ "CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO, "
					+ "D.PORCENTAJE "
					+"FROM CONTENIDO C INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+ "INNER JOIN LINEAPEDIDO LP ON LP.ID_CONTENIDO = C.ID_CONTENIDO "
					+ "INNER JOIN PEDIDO P ON P.ID_PEDIDO = LP.ID_PEDIDO "
					+ "INNER JOIN DESCUENTO D ON D.ID_DESCUENTO = C.ID_DESCUENTO "
					+"WHERE P.EMAIL = ? AND CI.ID_IDIOMA = ?"
					+ "ORDER BY P.FECHA_PEDIDO DESC ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, email);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery(); 

			List<Contenido> contenidos = new ArrayList<Contenido>();
			Contenido c = null;
			while (resultSet.next()) {
				c = loadNext(resultSet);
				contenidos.add(c);
			} return contenidos;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 	

	}

	@Override
	public List<Contenido> findFavoritos(Connection connection, String email, String idioma) 
			throws DataException {
		logger.debug("Email = {} Idioma = {}", email, idioma);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder sql = null;
		try{
			sql = new StringBuilder("SELECT C.ID_CONTENIDO, CI.TITULO, C.RESTRICCION_EDAD, C.PORTADA, C.FECHA_LANZAMIENTO, "
					+ "CI.DESCRIPCION_BREVE, C.PRECIO, C.PRECIO_DESCONTADO, C.DURACION, C.ID_DESCUENTO, C.ID_TIPO_CONTENIDO, "
					+ "D.PORCENTAJE "
					+"FROM CONTENIDO C INNER JOIN CONTENIDO_IDIOMA CI ON C.ID_CONTENIDO = CI.ID_CONTENIDO "
					+ "INNER JOIN USUARIO_CONTENIDO UC ON UC.ID_CONTENIDO = C.ID_CONTENIDO "
					+ "INNER JOIN DESCUENTO D ON D.ID_DESCUENTO = C.ID_DESCUENTO "
					+"WHERE UC.EMAIL = ? AND CI.ID_IDIOMA = ? AND FAVORITO = 1 ");

			preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++, email);
			preparedStatement.setString(i++, idioma);
			resultSet = preparedStatement.executeQuery(); 

			List<Contenido> contenidos = new ArrayList<Contenido>();
			Contenido c = null;
			while (resultSet.next()) {
				c = loadNext(resultSet);
				contenidos.add(c);
			} return contenidos;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		} finally {            
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		} 	

	}
	private void addClause(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? "WHERE ": " AND ").append(clause);
	}

	private StringBuilder addCategoria(List<Categoria> categorias) {
		//Creamos la query en base al número de categorias que haya marcado el usuario
		boolean inner = true;
		StringBuilder lista = new StringBuilder();
		for (Categoria c : categorias) {
			lista.append(inner ? " (CA.ID_CATEGORIA LIKE "+c.getIdCategoria() : " OR " + c.getIdCategoria());
			inner=false;	
		}
		lista.append(" ) ");
		return lista;

	}

	private StringBuilder addPais(List<Pais> paises) {
		//Creamos la query en base al número de idioma que haya marcado el usuario
		boolean inner = true;
		StringBuilder lista = new StringBuilder();
		for (Pais p : paises) {
			lista.append(inner ? " (P.ID_PAIS LIKE "+ p.getIdPais() : " OR " + p.getIdPais());
			inner=false;	
		}
		lista.append(" ) ");
		return lista;
	}
	private StringBuilder addArtista(Artista a) {
		//Creamos la query en base al número de idioma que haya marcado el usuario
		boolean inner = true;
		StringBuilder lista = new StringBuilder();
		 if(a != null) {
			lista.append(inner ? " (P.NOMBRE_ARTISTA LIKE "+ a.getNombreArtista() : " OR " + a.getNombreArtista());
			inner=false;	
		}
		lista.append(" ) ");
		return lista;
	}

	private void addUpdate(StringBuilder queryString, boolean first, String clause) {
		queryString.append(first? " SET ": " , ").append(clause);
	}

	
}
