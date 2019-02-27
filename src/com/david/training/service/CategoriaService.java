package com.david.training.service;

import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.Categoria;

public interface CategoriaService {
	
	public List<Categoria> findAll(String idioma) 
			throws DataException;

}
