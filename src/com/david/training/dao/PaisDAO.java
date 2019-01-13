package com.david.training.dao;

import com.david.training.model.Pais;

public interface PaisDAO {
	
	public Pais findByPais(Integer id) 
		throws Exception;
	
	
}
