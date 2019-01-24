package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.model.Pais;

public interface PaisDAO {
	
	public Pais findById(Integer id, String idioma, Connection c) 
		throws Exception;
	
	public List<Pais> findByNombre(String nombre, String idioma, Connection c)
			throws Exception;
}
