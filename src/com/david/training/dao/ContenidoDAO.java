package com.david.training.dao;


import java.sql.Connection;
import java.util.List;

import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;


public interface ContenidoDAO {
	
	public Contenido findById(Connection c, Integer id, String idioma)
			throws Exception;
	
	public List<Contenido> findByTitulo(Connection c, String title, String idioma) 
			throws Exception;
	
	
	public Contenido create (Connection connection, Contenido c)
			throws Exception;
	//Falta
	public List<Contenido> findByCriteria(Connection c, ProductoCriteria producto, String idioma)
			throws Exception;
	
	public boolean update(Connection connection, Contenido d)
			throws Exception;
	
	public long delete (Connection c, Integer id)
			throws Exception;
	
	public List<Contenido> anadirFavoritos(Connection connection, Integer idContenido)
			throws Exception;
	
	public List<Contenido> findLista(Connection connection, String email, String idioma) 
			throws Exception;
	
	public List<Contenido> findFavoritos(Connection connection, String email, String idioma)
		throws Exception;
	
	public Contenido findPorId(Connection c, Integer id) 
			throws Exception;
	
}

