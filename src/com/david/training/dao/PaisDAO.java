package com.david.training.dao;

import java.util.List;

import com.david.training.model.Pais;

public interface PaisDAO {
	
	public String findById(String id) 
		throws Exception;
	
	public List<Pais> findAll()
	throws Exception;
	

}
