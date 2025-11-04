package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.ArtistaRol;


public interface ArtistaRolDAO {
	
	public List<ArtistaRol> findByContenido(Integer idContenido, String idioma, Connection c) 
			throws DataException;

}
