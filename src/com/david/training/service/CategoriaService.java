package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.model.Categoria;

public interface CategoriaService {
	
	public Results<Categoria> findAll(String idioma, int startIndex, int count) 
			throws DataException;

}
