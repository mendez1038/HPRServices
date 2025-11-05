package com.david.training.service.impl;

import com.david.training.dao.PedidoDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.LineaPedidoDAO;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Pedido;
import com.david.training.model.PedidoCriteria;
import com.david.training.model.Contenido;
import com.david.training.model.LineaPedido;
import com.david.training.service.PedidoService;
import com.david.training.service.Results;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PedidoServiceImpl implements PedidoService {

    private final PedidoDAO pedidoDAO;
    private final LineaPedidoDAO lineaPedidoDAO;
    private ContenidoDAO contenidoDAO = null;
    public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);

    public PedidoServiceImpl(PedidoDAO pedidoDAO, LineaPedidoDAO lineaPedidoDAO) {
        this.pedidoDAO = pedidoDAO;
        this.lineaPedidoDAO = lineaPedidoDAO;
        contenidoDAO = new ContenidoDAOImpl();
    }

    @Override
    public Results<Pedido> historial(String email, int startIndex, int count) throws DataException {
    	Connection c = /* tu fábrica de conexiones */ null;
    	boolean commit = false;
    	try  {
        	c = ConnectionManager.getConnection();
        	Results<Pedido> historial = pedidoDAO.findByUsuario(c, email, startIndex, count); // listado SIN líneas;
        	commit = true;
            return historial;
        } catch (Exception e) {
            throw (e instanceof DataException) ? (DataException) e : new DataException(e);
        } finally {
            JDBCUtils.closeConnection(c, commit);
        }
    }

    @Override
    public Results<Pedido> historial(String email, PedidoCriteria criteria, int startIndex, int count)
            throws DataException {
        // Si aún no tienes DAO por criteria, puedes reutilizar findByUsuario y aplicar filtros arriba/abajo.
        return historial(email, startIndex, count);
    }

    @Override
    public Pedido detalle(Integer idPedido, String email)
            throws InstanceNotFoundException, DataException {
    	Connection c = /* tu fábrica de conexiones */ null;
    	boolean commit = false;
        try  {
        	c = ConnectionManager.getConnection();
            // Seguridad por propiedad:
            Pedido p = pedidoDAO.findByIdAndEmail(c, idPedido, email);
            commit = true;
            // Ya cargas líneas dentro del DAO en findByIdAndEmail; si no, harías:
            // p.setLineas(lineaPedidoDAO.findByPedido(c, idPedido));
            return p;
        } catch (InstanceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw (e instanceof DataException) ? (DataException) e : new DataException(e);
        } finally {
            JDBCUtils.closeConnection(c, commit);
        }
    }

    @Override
    public List<LineaPedido> lineasDePedido(Integer idPedido, String email)
            throws InstanceNotFoundException, DataException {
    	Connection c = /* tu fábrica de conexiones */ null;
    	boolean commit = false;
        try {
        	c = ConnectionManager.getConnection();
            // Validación de propiedad antes de exponer líneas
            pedidoDAO.findByIdAndEmail(c, idPedido, email);
            commit = true;
            return lineaPedidoDAO.findByPedido(c, idPedido);
        } catch (InstanceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw (e instanceof DataException) ? (DataException) e : new DataException(e);
        } finally {
            JDBCUtils.closeConnection(c, commit);
        }
    }

    @Override
    public boolean comprado(String email, Integer idContenido) throws DataException {
        boolean commit = false;
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(false);
            boolean ok = pedidoDAO.comprado(c, email, idContenido);
            commit = true;
            return ok;
        } catch (SQLException e) {
        	logger.warn(e.getMessage(),e);
            throw new DataException(e);
        } finally {
            JDBCUtils.closeConnection(c, commit);
        }
    }
    
    @Override
    public Pedido checkout(String email, java.util.List<Integer> idsContenido)
            throws DataException {

        logger.debug("checkout() -> email={}, ids={}", email, idsContenido);

        if (idsContenido == null || idsContenido.isEmpty()) {
            throw new DataException("El carrito está vacío");
        }

        boolean commit = false;
        Connection c = null;

        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(false);

            // Idioma da igual para el precio, pero si tu findById lo pide, usa "es" o lo que toque.
            String idioma = "es";

            // 1) Filtrar contenidos ya comprados
            java.util.List<Integer> elegibles = new java.util.ArrayList<>();
            for (Integer idContenido : idsContenido) {
                if (!pedidoDAO.comprado(c, email, idContenido)) {
                    elegibles.add(idContenido);
                }
            }

            if (elegibles.isEmpty()) {
                throw new DataException("Todos los productos del carrito ya están comprados.");
            }

            // 2) Calcular precios actuales y construir líneas
            java.util.List<LineaPedido> lineas = new java.util.ArrayList<>();
            double total = 0.0;

            for (Integer idContenido : elegibles) {
                // Cargas el contenido para obtener el precio actual
                Contenido cont = contenidoDAO.findById(c, idContenido, idioma);
                double precioUnidad;

                if (cont.getPrecioDescontado() != null && cont.getPrecioDescontado() > 0) {
                    precioUnidad = cont.getPrecioDescontado();
                } else {
                    precioUnidad = cont.getPrecio();
                }

                LineaPedido lp = new LineaPedido();
                lp.setIdContenido(idContenido);
                lp.setPrecioUnidad(precioUnidad);
                lineas.add(lp);

                total += precioUnidad;
            }

            if (lineas.isEmpty()) {
                throw new DataException("No hay productos válidos para comprar.");
            }

            // 3) Crear el pedido y las líneas (tu PedidoDAO ya inserta las líneas)
            Pedido p = new Pedido();
            p.setEmail(email);
            p.setFechaPedido(new java.util.Date());
            p.setPrecioTotal(total);
            p.setLineas(lineas);

            p = pedidoDAO.create(c, p);

            commit = true;
            return p;

        } catch (SQLException | InstanceNotFoundException e) {
            logger.warn(e.getMessage(), e);
            throw new DataException(e);
        } finally {
            JDBCUtils.closeConnection(c, commit);
        }
    }


}
