package com.david.training.dao;



import com.david.training.exceptions.DataException;
import com.david.training.model.Pedido;


public interface PedidoDAO {
	
	
	public Pedido findById( Integer id) 
    		throws  DataException;

}
