package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.model.TipoContenido;

public interface TipoContenidoDAO {
	
	public TipoContenido findById(String id, String idioma, Connection c)
			throws Exception;
	
	public List<TipoContenido> findByNombre(String nombre, String idioma, Connection c)
			throws Exception;

}
