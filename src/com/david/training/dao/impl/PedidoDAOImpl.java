package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.PedidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;
import com.david.training.model.Pedido;
import com.david.training.service.Results;





public class PedidoDAOImpl implements PedidoDAO{
	
	private LineaPedidoDAO lineaPedidoDAO = null;
	
	public PedidoDAOImpl() {
		lineaPedidoDAO = new LineaPedidoDAOImpl();
	}

	public static Logger logger = LogManager.getLogger(PedidoDAOImpl.class);	
	@Override
	public Pedido findById(Connection c, Integer id) 
			throws InstanceNotFoundException, DataException {
		logger.debug("Id = {}", id);
		Pedido p = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			
			queryString = new StringBuilder(
					"SELECT ID_PEDIDO, FECHA_PEDIDO, PRECIO_TOTAL, EMAIL " +
							"FROM PEDIDO  " +
					"WHERE ID_PEDIDO = ? ");

			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			int i = 1;                
			preparedStatement.setInt(i++, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				p = loadNext(c, resultSet);				
			}else {
				throw new InstanceNotFoundException("Pedido with id " + id + 
						"not found", Pedido.class.getName());
			}

			return p;

		} catch (SQLException e1) {
			logger.warn(e1.getMessage(),e1);
			throw new DataException(e1);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	private Pedido loadNext(Connection c, ResultSet resultSet) 
			throws DataException, SQLException {

		int i = 1;

		Integer idPedido = resultSet.getInt(i++);
		Date fechaPedido = resultSet.getDate(i++);
		Double precioTotal = resultSet.getDouble(i++);
		String email = resultSet.getString(i++);

		Pedido p = new Pedido();

		p.setIdPedido(idPedido);
		p.setFechaPedido(fechaPedido);
		p.setPrecioTotal(precioTotal);
		p.setEmail(email);

		List<LineaPedido> lineasPedido = lineaPedidoDAO.findByPedido(c, idPedido);
		p.setLineas(lineasPedido);
		return p;
	}

	@Override
	public Results<Pedido> findByUsuario(Connection c, String email, int startIndex, int count) 
			throws DataException {
		logger.debug("Email = {}, StartIndex = {}, Count = {}", email, startIndex, count);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {
			queryString = new StringBuilder(
					"SELECT P.ID_PEDIDO, P.FECHA_PEDIDO, P.PRECIO_TOTAL, P.EMAIL " + 
					"FROM PEDIDO P " +
					"WHERE P.EMAIL = ? " +
					"ORDER BY P.FECHA_PEDIDO DESC, P.ID_PEDIDO DESC");

			preparedStatement = c.prepareStatement(queryString.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;                
			preparedStatement.setString(i++, email);
			resultSet = preparedStatement.executeQuery();

			List<Pedido> pedidos = new ArrayList<Pedido>();  
			int currentCount = 0;

			if ((startIndex >= 1) && resultSet.absolute(startIndex)) { 
				do {
					pedidos.add(mapPedido(resultSet));
	                currentCount++;
				} while ((currentCount < count) && resultSet.next());
			}

			int total = JDBCUtils.getTotalRows(resultSet);
			return new Results<Pedido>(pedidos, startIndex, total);  

		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);
		}

	}

	@Override
	public Pedido create(Connection c, Pedido p) 
			throws DuplicateInstanceException, DataException {
		logger.debug("Pedido = {}", p);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder queryString = null;
		try {          
			queryString = new StringBuilder(
					"INSERT INTO PEDIDO(FECHA_PEDIDO, PRECIO_TOTAL, EMAIL) "
					+ "VALUES (?, ?, ?)");

			preparedStatement = c.prepareStatement(queryString.toString(),Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setDate(i++, new java.sql.Date(p.getFechaPedido().getTime()));
			preparedStatement.setDouble(i++, p.getPrecioTotal());
			preparedStatement.setString(i++, p.getEmail());


			int insertedRows = preparedStatement.executeUpdate();

			if (insertedRows == 0) {
				throw new SQLException("Can not add row to table 'PEDIDO'");
			}
			
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				Integer pk = resultSet.getInt(1); 
				p.setIdPedido(pk);
			} else {
				throw new DataException("Unable to fetch autogenerated primary key");
			}
			List<LineaPedido> lineas = p.getLineas();

			for (LineaPedido lp: lineas) {
				lp.setIdPedido(p.getIdPedido());
				lineaPedidoDAO.create(c, lp);
			}
			
			return p;
		} catch (SQLException ex) {
			logger.warn(ex.getMessage(),ex);
			throw new DataException(ex);
		} finally {
			JDBCUtils.closeResultSet(resultSet);
			JDBCUtils.closeStatement(preparedStatement);			
		}
	}

	
	public void delete(Connection c, Integer idPedido)
	        throws InstanceNotFoundException, DataException {
	    // 1) Borra líneas
	    try (PreparedStatement ps1 = c.prepareStatement(
	            "DELETE FROM LINEAPEDIDO WHERE ID_PEDIDO = ?")) {
	        ps1.setInt(1, idPedido);
	        ps1.executeUpdate();
	    } catch (SQLException e) { throw new DataException(e); }

	    // 2) Borra pedido
	    try (PreparedStatement ps2 = c.prepareStatement(
	            "DELETE FROM PEDIDO WHERE ID_PEDIDO = ?")) {
	        ps2.setInt(1, idPedido);
	        int removed = ps2.executeUpdate();
	        if (removed == 0) {
	            throw new InstanceNotFoundException(idPedido, Pedido.class.getName());
	        }
	    } catch (SQLException e) { throw new DataException(e); }
	}


	@Override
	public Pedido findByIdAndEmail(Connection c, Integer id, String email)
	        throws InstanceNotFoundException, DataException {
	    String sql = "SELECT ID_PEDIDO, FECHA_PEDIDO, PRECIO_TOTAL, EMAIL " +
	                 "FROM PEDIDO WHERE ID_PEDIDO = ? AND EMAIL = ?";
	    try (PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.setString(2, email);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (!rs.next()) {
	                throw new InstanceNotFoundException(
	                    "Pedido not found or not owned by user", Pedido.class.getName());
	            }
	            Pedido p = mapPedido(rs); // ver punto 2
	            p.setLineas(lineaPedidoDAO.findByPedido(c, p.getIdPedido()));
	            return p;
	        }
	    } catch (SQLException e) { throw new DataException(e); }
	}
	
	// Para listados (NO carga líneas)
	private Pedido mapPedido(ResultSet rs) throws SQLException {
	    Pedido p = new Pedido();
	    p.setIdPedido(rs.getInt("ID_PEDIDO"));
	    p.setFechaPedido(rs.getDate("FECHA_PEDIDO"));
	    p.setPrecioTotal(rs.getDouble("PRECIO_TOTAL"));
	    p.setEmail(rs.getString("EMAIL"));
	    return p;
	}

	@Override
	public boolean comprado(Connection c, String email, Integer idContenido) throws DataException {
	    final String sql =
	        "SELECT 1 " +
	        "FROM PEDIDO p " +
	        "JOIN LINEAPEDIDO lp ON lp.ID_PEDIDO = p.ID_PEDIDO " +
	        "WHERE p.EMAIL = ? AND lp.ID_CONTENIDO = ? " +
	        "LIMIT 1";
	    try (PreparedStatement ps = c.prepareStatement(sql)) {
	        ps.setString(1, email);
	        ps.setInt(2, idContenido);
	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }
	    } catch (SQLException e) {
	        throw new DataException(e);
	    }
	}


	
}
