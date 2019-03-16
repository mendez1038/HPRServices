package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.model.Pais;

public interface PaisService {
	
	public Results<Pais> findAll(String idioma, int startIndex, int count) 
			throws DataException;

}
