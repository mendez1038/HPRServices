package com.david.training.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.LineaPedido;
import com.david.training.model.Pedido;
import com.david.training.service.impl.PedidoServiceImpl;
import com.david.training.util.ToStringUtil;

public class PedidoServiceTest {
	
	private PedidoService servicio = null;
	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class.getName());

	public PedidoServiceTest() {
		servicio = new PedidoServiceImpl();
	}

	public void testHistorial() {
		logger.info("Testing Historial ...");
		int pageSize = 2; 	
		try {
			Results<Pedido> results = null; 
			String email = "AA@A.COM";
			int startIndex = 1; 
			int total = 1;
			do {
			results = servicio.historial(email, startIndex, pageSize);
			logger.info("Found "+results.getTotal()+" results.");
			if (results.getPage().size()>0) {
				logger.info("Page ["+startIndex+" - "+(startIndex+results.getPage().size()-1)+"] : ");
			for(Pedido p : results.getPage()) {
				logger.info("Result "+total+": "+ToStringUtil.toString(p));
				total++;
			}
				startIndex = startIndex + pageSize;
			}
			} while (results.getPage().size()==pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Test testHistorial finished.\n");
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
		//test.testHistorial();
		test.testHistorialAmpliado();

	}

}
