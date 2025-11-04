package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.model.Favorito;


public interface FavoritoService {
	
	
	
	public Favorito anadirFavorito(Favorito f)
			throws DataException;
	
	public void upadteEliminarFavorito(Favorito f)
			throws DataException;
	
	public Boolean exists(String email, Integer idContenido)
			throws DataException;
	
	public Boolean esFavorito(String email, Integer idContenido)
			throws DataException;

}
