package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Usuario;

public interface UsuarioDAO {
	


	public Usuario findByEmail( String email, Connection c)
			throws InstanceNotFoundException, DataException;

	public Boolean exists( String email, Connection c)
			throws DataException;
	
	public Usuario create( Usuario u, Connection c)
			throws DuplicateInstanceException, DataException;
	
	public boolean update( Usuario u, Connection c) 
			throws InstanceNotFoundException, DataException; 
				
	public long delete(String email, Connection c)
			throws InstanceNotFoundException, DataException; 

	public long countAll(Connection c) 
			throws DataException;
	
	void updatePasswordHash(Connection c, String email, String nuevoHash) 
			throws DataException;


	//finByCriteria nombre,fecha,tel?

}
		
		


