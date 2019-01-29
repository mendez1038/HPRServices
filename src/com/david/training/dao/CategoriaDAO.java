package com.david.training.dao;


import java.sql.Connection;
import java.util.List;
import com.david.training.model.Categoria;


public interface CategoriaDAO {

	public Categoria findById(Integer id, String idIdioma, Connection c)
		throws Exception;
	
	public List<Categoria> findAll(String idioma, Connection c) 
		throws Exception;
	
	public List<Categoria> findByContenido(Integer idContenido, String idioma, Connection c) 
			throws Exception;

		
		
}
