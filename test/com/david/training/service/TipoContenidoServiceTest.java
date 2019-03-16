package com.david.training.service;

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
			Results<TipoContenido> results = null;
			int startIndex = 1; 
			int total = 1;
			String idioma = "es";
			do {
				results = tipoConteniodService.findAll(idioma, startIndex, pageSize);
				logger.info("Found "+results.getTotal()+" results.");
				if (results.getPage().size()>0) {
					logger.info("Page ["+startIndex+" - "+(startIndex+results.getPage().size()-1)+"] : ");				
					for (TipoContenido tc: results.getPage()) {
						logger.info("Result "+total+": "+ToStringUtil.toString(tc));
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
		TipoContenidoServiceTest test = new TipoContenidoServiceTest();
		test.testFindAll();
	}

}
