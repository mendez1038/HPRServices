package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;

public interface UsuarioDAO {
	

	public Usuario findByEmail(Connection connection, String email)
	throws DataException;


	
	public Usuario exists(Connection connection, Usuario u)
	throws Exception;
	
	public Usuario create( Usuario u)
	throws Exception;
	
	public void update(Connection connection, Usuario u) 
			throws DataException; 
		
		
	public long delete(Connection connection, Long id)
			throws DataException; 
}
		
		


