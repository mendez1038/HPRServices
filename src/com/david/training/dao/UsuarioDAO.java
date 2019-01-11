package com.david.training.dao;

import com.david.training.exceptions.DataException;
import com.david.training.model.Usuario;

public interface UsuarioDAO {
	


	public Usuario findByEmail( String email)
	throws DataException;


	public Usuario exists( Usuario u)
	throws Exception;
	

	public Usuario create( Usuario u)
	throws Exception;
	
	public void update( Usuario u) 
			throws DataException; 
		
		
	public long delete(String email)
			throws DataException; 



}
		
		


