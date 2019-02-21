package com.david.training.service;

import java.util.List;

import com.david.training.model.LineaPedido;
import com.david.training.model.LineaPedidoId;
import com.david.training.model.Pedido;


public interface PedidoService {
	
	// Amosa os pedidos realizados polo usuario
	public List<Pedido> historial(String email)
			throws Exception;
	// Detalla o historial cos productos comprados en cada pedido
	public List<LineaPedido> historialAmpliado(Integer id)
			throws Exception;
	
	public Pedido carrito (Pedido p)
			throws Exception;
	public LineaPedido carritoAmplidado(LineaPedido lp)
			throws Exception;
	void eliminarLineaPedido(LineaPedidoId id) 
			throws Exception;
	

}
