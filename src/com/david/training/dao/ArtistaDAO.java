package com.david.training.dao;

import java.util.List;

import com.david.training.model.Artista;



public interface ArtistaDAO {
	
	
	public Artista findById(Integer id)
			throws Exception;
	
	
	public List<Artista> findByNombre(String title) 
			throws Exception;

}
