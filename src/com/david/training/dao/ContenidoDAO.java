package com.david.training.dao;


import java.sql.Connection;
import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public interface ContenidoDAO {
	
	public Contenido findById(Connection c, Integer id, String idioma)
			throws InstanceNotFoundException, DataException;
	
	public Contenido create (Connection connection, Contenido c)
			throws DuplicateInstanceException, DataException;
	
	public List<Contenido> findByCriteria(Connection c, ProductoCriteria producto, String idioma)
			throws DataException;
	
	public boolean update(Connection connection, Contenido d)
			throws InstanceNotFoundException, DataException;
	
	public long delete (Connection c, Integer id)
			throws InstanceNotFoundException, DataException;
	
	public List<Contenido> findLista(Connection connection, String email, String idioma) 
			throws InstanceNotFoundException, DataException;
	
	public List<Contenido> findFavoritos(Connection connection, String email, String idioma)
		throws InstanceNotFoundException, DataException;
	
	public Contenido findPorId(Connection c, Integer id) 
			throws InstanceNotFoundException, DataException;
	
}

