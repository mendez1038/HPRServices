package com.david.training.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.TipoContenido;
import com.david.training.service.impl.TipoContenidoServiceImpl;
import com.david.training.util.ToStringUtil;

public class TipoContenidoServiceTest {
	
	private TipoContenidoService tipoConteniodService = null;
	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class.getName());

	public TipoContenidoServiceTest() {
		tipoConteniodService = new TipoContenidoServiceImpl();
	}

	public void testFindAll() {
		logger.info("Testing findAll ...");
		int pageSize = 3; 	
		try {
			List<TipoContenido> results = null;
			
			String idioma = "es";
			
				results = tipoConteniodService.findAll(idioma);
				logger.info("Found "+results+" results.");
								
					for (TipoContenido tc: results) {
						logger.info("Result : "+ToStringUtil.toString(tc));
						
					}
					
				

			

		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
		logger.info("Test testFindAll finished.\n");
	}

	public static void main(String[] args) {
		TipoContenidoServiceTest test = new TipoContenidoServiceTest();
		test.testFindAll();
	}

}
