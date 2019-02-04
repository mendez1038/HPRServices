package com.david.training.service;

import java.util.ArrayList;
import java.util.List;

import com.david.training.model.LineaPedido;
import com.david.training.model.Pedido;
import com.david.training.service.impl.PedidoServiceImpl;

public class PedidoServiceTest {
	
	private PedidoService servicio = null;
	
	public PedidoServiceTest() {
		servicio = new PedidoServiceImpl();
	}

	public void testHistorial() {
	
		try {
			
			List<Pedido> historial = new ArrayList<Pedido>(); 
			String email = "AA@A.COM";
			historial = servicio.historial(email);
			for(Pedido p : historial) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testHistorialAmpliado() {
		
	try {
			
			List<LineaPedido> ampliacion = new ArrayList<LineaPedido>(); 
			Integer id = 1;
			ampliacion = servicio.historialAmpliado(id);
			for(LineaPedido lp : ampliacion) {
				System.out.println(lp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		PedidoServiceTest test = new PedidoServiceTest();
		test.testHistorial();
		test.testHistorialAmpliado();

	}

}
