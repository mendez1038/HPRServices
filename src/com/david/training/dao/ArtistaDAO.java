package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.model.Artista;



public interface ArtistaDAO {
	
	
	public Artista findById(Integer id, Connection c)
			throws Exception;
	
	
	public List<Artista> findByNombre(String title, Connection c) 
			throws Exception;
	
	public List<Artista> findAll (Connection c)
			throws Exception;
	
	

}
