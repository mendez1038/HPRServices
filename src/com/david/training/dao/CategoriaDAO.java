package com.david.training.dao;


import java.sql.Connection;

import com.david.training.model.Categoria;


public interface CategoriaDAO {

	public Categoria findById(Integer id, String idIidioma, Connection c)
		throws Exception;
	//falta
	public Categoria findAll(String idioma, Connection c) 
		throws Exception;
	/* Innecesario
	 * 	public Categoria create(Categoria ca, String idIdioma, Connection c) 
		throws Exception;

	 */

		
		
}
