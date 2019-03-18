package com.david.training.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.model.Categoria;
import com.david.training.service.impl.CategoriaServiceImpl;
import com.david.training.util.ToStringUtil;

public class CategoriaServiceTest {

	private CategoriaService categoriaService = null;
	private static Logger logger = LogManager.getLogger(ContenidoServiceTest.class.getName());

	public CategoriaServiceTest() {
		categoriaService = new CategoriaServiceImpl();
	}

	public void testFindAll() {
		logger.info("Testing findAll ...");	
		try {
			List<Categoria> results = null;
			String idioma ="es";
			results = categoriaService.findAll(idioma);
			for (Categoria cat: results) {
			logger.info("Result "+ToStringUtil.toString(cat));
									}
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
		}
		logger.info("Test testFindAll finished.\n");
	}

	public static void main(String[] args) {
		CategoriaServiceTest test = new CategoriaServiceTest();
		test.testFindAll();
	}
}
