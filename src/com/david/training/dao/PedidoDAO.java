package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Pedido;
import com.david.training.service.Results;


public interface PedidoDAO {
	
	
	public Pedido findById( Connection c,Integer id) 
    		throws  InstanceNotFoundException, DataException;
	
	public Results<Pedido> findByUsuario(Connection c,String email, int startIndex, int count)
			throws DataException;
	
	public Pedido create(Connection c,Pedido p)
			throws DuplicateInstanceException, DataException;
	
	public void delete(Connection c, Integer idPedido)
			throws InstanceNotFoundException, DataException;

}
