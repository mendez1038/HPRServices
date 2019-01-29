package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;

import com.david.training.model.LineaPedido;



public interface LineaPedidoDAO {
	
	
	public LineaPedido findById(Connection c,  Integer idp, Integer idc) 
        	throws DataException;
		    
	public List<LineaPedido> findByPedido(Connection c,  Integer idPedido) 
        	throws DataException;
  
    public LineaPedido create(Connection c, LineaPedido lp) 
    		throws DataException;
        
    public long delete(Connection c,  Integer idPedido, Integer idContenido) 
    		throws DataException;


}
