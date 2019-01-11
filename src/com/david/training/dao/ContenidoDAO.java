package com.david.training.dao;


import java.util.List;

import com.david.training.model.Contenido;


public interface ContenidoDAO {
	
	public Contenido findById(Integer id)
			throws Exception;
	
	public List<Contenido> findByTitulo(String title) 
			throws Exception;
	
	public List<Contenido> findByContenidoCriteria()
		throws Exception;
	
	public Contenido create (Contenido c)
			throws Exception;
	
	
}

