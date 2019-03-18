package com.david.training.service;

import java.util.List;

import com.david.training.exceptions.DataException;
import com.david.training.model.TipoContenido;

public interface TipoContenidoService {
	public List<TipoContenido> findAll(String idioma) 
			throws DataException;

}
