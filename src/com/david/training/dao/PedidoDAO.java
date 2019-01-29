package com.david.training.dao;



import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.Pedido;


public interface PedidoDAO {
	
	
	public Pedido findById( Connection c,Integer id) 
    		throws  DataException;
	
	public List<Pedido> findByUsuario(Connection c,String email)
			throws Exception;
	
	public Pedido create(Connection c,Pedido p)
			throws Exception;
	
	public void delete(Connection c, Integer idPedido)
			throws Exception;

}
