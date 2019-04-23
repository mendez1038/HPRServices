package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.model.Favorito;


public interface FavoritoService {
	
	
	
	public Favorito añadirFavorito(Favorito f)
			throws DataException;
	
	public void upadteEliminarFavorito(Favorito f)
			throws DataException;
	
	

}
