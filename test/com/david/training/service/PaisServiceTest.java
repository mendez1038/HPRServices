package com.david.training.service;

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
			Results<Pais> results = null;
			int startIndex = 1; 
			int total = 1;
			String idioma ="es";
			do {
				results = paisService.findAll(idioma, startIndex, pageSize);
				logger.info("Found "+results.getTotal()+" results.");
				if (results.getPage().size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.getPage().size()-1)+"] : ");				
					for (Pais pais: results.getPage()) {
						logger.info("Result "+total+": "+ToStringUtil.toString(pais));
						total++;
					}
					startIndex = startIndex + pageSize;
				}

			} while (results.getPage().size()==pageSize);

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
