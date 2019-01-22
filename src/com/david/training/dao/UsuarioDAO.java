package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;

public interface UsuarioDAO {
	


	public Usuario findByEmail( String email, Connection c)
	throws DataException;

	public Boolean exists( String email)
	throws Exception;
	
	public Usuario create( Usuario u)
	throws Exception;
	
	public boolean update( Usuario u) 
			throws DataException; 
				
	public long delete(String email, Connection c)
			throws DataException; 

	public long countAll() 
		throws Exception;
	
	//finByCriteria nombre,fecha,tel?

}
		
		


