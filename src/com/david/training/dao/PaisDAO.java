package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Pais;
import com.david.training.service.Results;

public interface PaisDAO {
	
	public Pais findById(Integer id, String idioma, Connection c) 
		throws InstanceNotFoundException, DataException;
	
	public Results<Pais> findAll(String idioma, Connection c, int startIndex, int count)
			throws DataException;
}
