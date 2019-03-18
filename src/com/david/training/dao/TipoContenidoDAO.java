package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.TipoContenido;

public interface TipoContenidoDAO {
	
	public TipoContenido findById(String id, String idioma, Connection c)
			throws InstanceNotFoundException, DataException;
	
	public List<TipoContenido> findByNombre(String nombre, String idioma, Connection c)
			throws DataException;
	
	public List<TipoContenido> findAll (String idioma, Connection c)
			throws DataException;

}
