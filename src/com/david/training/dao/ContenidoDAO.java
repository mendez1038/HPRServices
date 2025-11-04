package com.david.training.dao;


import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;
import com.david.training.service.Results;

public interface ContenidoDAO {
	
	public Contenido findById(Connection c, Integer id, String idioma)
			throws InstanceNotFoundException, DataException;
	
	public Contenido create (Connection connection, Contenido c)
			throws DuplicateInstanceException, DataException;
	
	public Results<Contenido> findByCriteria(Connection c, ProductoCriteria producto, String idioma, int startIndex, int count)
			throws DataException;
	
	public boolean update(Connection connection, Contenido d)
			throws InstanceNotFoundException, DataException;
	
	public long delete (Connection c, Integer id)
			throws InstanceNotFoundException, DataException;
	
	Results<Contenido> findBiblioteca(Connection c,String email,String idioma,int offset, int limit) 
			throws DataException;
	
	public Results<Contenido> findFavoritos(Connection connection, String email, String idioma, int startIndex, int count)
		throws InstanceNotFoundException, DataException;
	
	public Contenido findPorId(Connection c, Integer id) 
			throws InstanceNotFoundException, DataException;
	
	public Results<Contenido> findAllByRebajas(Connection connection, String idioma, int startIndex, int count)
			throws InstanceNotFoundException, DataException;
		
	public Results<Contenido> findAllByDate(Connection connection, String idioma, int startIndex, int count)
			throws InstanceNotFoundException, DataException;
	
	public Results<Contenido> findAllByVentas(Connection connection, String idioma, int startIndex, int count)
			throws InstanceNotFoundException, DataException;
	
}

