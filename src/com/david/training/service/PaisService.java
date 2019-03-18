package com.david.training.service;

import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.Pais;

public interface PaisService {
	
	public List<Pais> findAll(String idioma) 
			throws DataException;

}
