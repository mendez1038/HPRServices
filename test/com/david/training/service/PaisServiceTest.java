package com.david.training.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.Pais;
import com.david.training.service.impl.PaisServiceImpl;
import com.david.training.util.ToStringUtil;

public class PaisServiceTest {

	private PaisService paisService = null;
	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class.getName());

	public PaisServiceTest() {
		paisService = new PaisServiceImpl();
	}

	public void testFindAll() {
		logger.info("Testing findAll ...");
		int pageSize = 5; 	
		try {
			List<Pais> results = null;
			String idioma ="es";
				results = paisService.findAll(idioma);
							
					for (Pais pais: results) {
						logger.info("Result "+ToStringUtil.toString(pais));
			
					}
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
		logger.info("Test testFindAll finished.\n");
	}

	public static void main(String[] args) {
		PaisServiceTest test = new PaisServiceTest();
		test.testFindAll();
	}

}
