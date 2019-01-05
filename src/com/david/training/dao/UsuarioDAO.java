package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;


public interface UsuarioDAO {
	
	public Usuario findByEmail(Connection connection, Usuario u)
	throws DataException;
	
	public Usuario exists(Connection connection, Usuario u)
	throws DataException;
	
	public Usuario create(Connection connection, Usuario u)
	throws DataException;
	
	public void update(Connection connection, Usuario u) 
    		throws DataException;
        
    public long delete(Connection connection, Long id) 
    		throws DataException;

}
