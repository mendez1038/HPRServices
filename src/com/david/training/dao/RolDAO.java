package com.david.training.dao;

import java.sql.Connection;
import java.util.List;

import com.david.training.model.Rol;


public interface RolDAO {
	
	
	
	public Rol findById(String id, String idioma, Connection c)
			throws Exception;
	
	public List<Rol> findByNombre(String nombre, String idioma, Connection c)
			throws Exception;


}
