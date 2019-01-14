package com.david.training.dao;

import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;

public interface UsuarioDAO {
	


	public Usuario findByEmail( String email)
	throws DataException;


	public Boolean exists( String email)
	throws Exception;
	

	public Usuario create( Usuario u)
	throws Exception;
	
	
	/*
	 * Falta solo este.
	 */
	public boolean update( Usuario u) 
			throws DataException; 
		
		
	public long delete(String email)
			throws DataException; 

	public long countAll() 
		throws Exception;

}
		
		


