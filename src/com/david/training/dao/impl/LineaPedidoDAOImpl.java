package com.david.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.LineaPedidoDAO;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;

public class LineaPedidoDAOImpl implements LineaPedidoDAO {

    private static final Logger logger = LogManager.getLogger(LineaPedidoDAOImpl.class);

    @Override
    public LineaPedido findById(Connection c, Integer idPedido, Integer idContenido)
            throws InstanceNotFoundException, DataException {
        logger.debug("Buscando linea de pedido: pedido={}, contenido={}", idPedido, idContenido);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ID_PEDIDO, ID_CONTENIDO, PRECIO_UNIDAD FROM LINEAPEDIDO "
                       + "WHERE ID_PEDIDO = ? AND ID_CONTENIDO = ?";
            ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idPedido);
            ps.setInt(2, idContenido);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new InstanceNotFoundException(
                    "LineaPedido not found (" + idPedido + "," + idContenido + ")",
                    LineaPedido.class.getName());
            }

            return loadNext(rs);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DataException(e);
        } finally {
            JDBCUtils.closeResultSet(rs);
            JDBCUtils.closeStatement(ps);
        }
    }

    @Override
    public List<LineaPedido> findByPedido(Connection c, Integer idPedido) throws DataException {
        logger.debug("Buscando líneas del pedido {}", idPedido);
        List<LineaPedido> results = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT ID_PEDIDO, ID_CONTENIDO, PRECIO_UNIDAD FROM LINEAPEDIDO WHERE ID_PEDIDO = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(loadNext(rs));
                }
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DataException(e);
        }
        return results;
    }

    @Override
    public LineaPedido create(Connection c, LineaPedido lp)
            throws DuplicateInstanceException, DataException {
        logger.debug("Creando línea de pedido: {}", lp);
        try (PreparedStatement ps = c.prepareStatement(
                "INSERT INTO LINEAPEDIDO(ID_PEDIDO, ID_CONTENIDO, PRECIO_UNIDAD) VALUES (?, ?, ?)")) {

            int i = 1;
            ps.setInt(i++, lp.getIdPedido());
            ps.setInt(i++, lp.getIdContenido());
            ps.setDouble(i++, lp.getPrecioUnidad());

            ps.executeUpdate();
            return lp;

        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) { // clave duplicada
                throw new DuplicateInstanceException(
                    "LineaPedido(" + lp.getIdPedido() + "," + lp.getIdContenido() + ")",
                    LineaPedido.class.getName());
            }
            logger.warn(e.getMessage(), e);
            throw new DataException(e);
        }
    }

    @Override
    public long delete(Connection c, LineaPedido id)
            throws InstanceNotFoundException, DataException {
        logger.debug("Borrando línea de pedido: {}", id);
        try (PreparedStatement ps = c.prepareStatement(
                "DELETE FROM LINEAPEDIDO WHERE ID_PEDIDO = ? AND ID_CONTENIDO = ?")) {

            ps.setInt(1, id.getIdPedido());
            ps.setInt(2, id.getIdContenido());
            int removed = ps.executeUpdate();

            if (removed == 0) {
                throw new InstanceNotFoundException(id, LineaPedido.class.getName());
            }
            return removed;
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
            throw new DataException(e);
        }
    }

    private LineaPedido loadNext(ResultSet rs) throws SQLException {
        LineaPedido lp = new LineaPedido();
        lp.setIdPedido(rs.getInt("ID_PEDIDO"));
        lp.setIdContenido(rs.getInt("ID_CONTENIDO"));
        lp.setPrecioUnidad(rs.getDouble("PRECIO_UNIDAD"));
        return lp;
    }
}
