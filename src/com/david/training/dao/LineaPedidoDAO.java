package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;

public interface LineaPedidoDAO {
    
    // Buscar una línea concreta del pedido (por PK compuesta)
    public LineaPedido findById(Connection c, Integer idPedido, Integer idContenido)
        throws InstanceNotFoundException, DataException;
		    
    // Buscar todas las líneas de un pedido
    public List<LineaPedido> findByPedido(Connection c, Integer idPedido)
        throws DataException;
  
    // Insertar nueva línea
    public LineaPedido create(Connection c, LineaPedido lp)
        throws DuplicateInstanceException, DataException;
        
    // Eliminar una línea concreta
    public long delete(Connection c, LineaPedido id)
        throws InstanceNotFoundException, DataException;
}
