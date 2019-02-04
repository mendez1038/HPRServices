package com.david.training.service;

import java.util.List;

import com.david.training.model.LineaPedido;
import com.david.training.model.Pedido;

public interface PedidoService {
	
	
	public List<Pedido> historial(String email)
			throws Exception;
	
	public List<LineaPedido> historialAmpliado(Integer id)
			throws Exception;
	
	public List<LineaPedido> carrito (LineaPedido lp)
			throws Exception;

}
