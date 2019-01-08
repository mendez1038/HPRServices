package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.IdLineaPedido;
import com.david.training.model.LineaPedido;



public interface LineaPedidoDAO {
	
	
	public LineaPedido findById(Connection connection, IdLineaPedido id) 
        	throws DataException;
	
	public Boolean exists(Connection connection, IdLineaPedido id) 
    		throws DataException;
	    
    public List<LineaPedido> findByTicket(Connection connection, Long idTicket) 
        	throws DataException;
  
    public LineaPedido create(Connection connection, LineaPedido c) 
    		throws DataException;
        
    public long delete(Connection connection, IdLineaPedido id) 
    		throws DataException;


}
