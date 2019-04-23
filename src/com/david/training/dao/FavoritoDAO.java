package com.david.training.dao;

import java.sql.Connection;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Favorito;

public interface FavoritoDAO {
	
	public Favorito create(Favorito f, Connection c ) 
			throws DuplicateInstanceException, DataException;
	
	public Favorito updateEliminarFavoritos(Favorito favorito, Connection c)
			throws InstanceNotFoundException, DataException; 
	
	public Favorito updateAnadirFavoritos(Favorito favorito, Connection c)
			throws InstanceNotFoundException, DataException; 
	
	public Boolean existsFavorito( String email, Integer idContenido, Connection c)
			throws DataException; 

}
