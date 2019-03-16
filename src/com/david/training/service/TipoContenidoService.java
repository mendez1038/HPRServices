package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.model.TipoContenido;

public interface TipoContenidoService {
	public Results<TipoContenido> findAll(String idioma, int startIndex, int count) 
			throws DataException;

}
