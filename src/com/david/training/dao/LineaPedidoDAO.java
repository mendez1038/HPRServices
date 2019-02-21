package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;
import com.david.training.model.LineaPedidoId;




public interface LineaPedidoDAO {
	
	
	public LineaPedido findById(Connection c,  LineaPedidoId id) 
        	throws DataException;
		    
	public List<LineaPedido> findByPedido(Connection c,  Integer idPedido) 
        	throws DataException;
  
    public LineaPedido create(Connection c, LineaPedido lp) 
    		throws DuplicateInstanceException, DataException;
        
    public long delete(Connection c,  LineaPedidoId id) 
    		throws InstanceNotFoundException, DataException;


}
