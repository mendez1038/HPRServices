package com.david.training.dao;

import java.util.List;

import com.david.training.exceptions.DataException;

import com.david.training.model.LineaPedido;



public interface LineaPedidoDAO {
	
	
	public LineaPedido findById( Integer idp, Integer idc) 
        	throws DataException;
	
	public Boolean exists( LineaPedido id) 
    		throws DataException;
	    
	public List<LineaPedido> findByPedido( Integer idPedido) 
        	throws DataException;
  
    public LineaPedido create(LineaPedido lp) 
    		throws DataException;
        
    public long delete( LineaPedido id) 
    		throws DataException;


}
