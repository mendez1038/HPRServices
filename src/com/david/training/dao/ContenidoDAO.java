package com.david.training.dao;


import java.util.List;

import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;


public interface ContenidoDAO {
	
	public Contenido findById(Integer id)
			throws Exception;
	
	public List<Contenido> findByTitulo(String title) 
			throws Exception;
	
	//innecesario
	public Contenido create (Contenido c)
			throws Exception;
	//Falta
	public List<Contenido> findByCriteria(ProductoCriteria producto)
			throws Exception;
	//Falta
	public boolean update(Contenido d)
			throws Exception;
	//Falta
	public long delete (Integer id)
			throws Exception;
	
	
}

