package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Pais;

public interface PaisDAO {
	
	public Pais findById(Integer id, String idioma, Connection c) 
		throws InstanceNotFoundException, DataException;
	
	public List<Pais> findAll(String idioma, Connection c)
			throws DataException;
}
