package com.david.training.dao;



import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.Pedido;


public interface PedidoDAO {
	
	
	public Pedido findById( Integer id) 
    		throws  DataException;
	
	public List<Pedido> findByUsuario(String email)
			throws Exception;
	
	public Pedido create(Pedido p)
			throws Exception;

}
