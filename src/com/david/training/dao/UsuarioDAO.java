package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Favorito;
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
	
	public Favorito createFavoritos(Connection connection, Favorito f)
			throws DuplicateInstanceException, DataException;
	
	public Favorito updateFavoritos(Favorito favorito, Connection c)
			throws InstanceNotFoundException, DataException; 
	
	public Boolean existsFavorito( String email, Integer idContenido, Connection c)
			throws DataException;
	
	//finByCriteria nombre,fecha,tel?

}
		
		


