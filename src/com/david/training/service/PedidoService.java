package com.david.training.service;

import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.LineaPedido;
import com.david.training.model.LineaPedidoId;
import com.david.training.model.Pedido;


public interface PedidoService {
	
	// Amosa os pedidos realizados polo usuario
	public Results<Pedido> historial(String email, int startIndex, int count)
			throws DataException;
	// Detalla o historial cos productos comprados en cada pedido
	public List<LineaPedido> historialAmpliado(Integer id)
			throws DataException;
	
	public Pedido carrito (Pedido p)
			throws DuplicateInstanceException, DataException;
	
	public LineaPedido carritoAmplidado(LineaPedido lp)
			throws DuplicateInstanceException, DataException;
	
	public void eliminarLineaPedido(LineaPedidoId id) 
			throws InstanceNotFoundException, DataException;
	

}
