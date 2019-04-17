package com.david.training.service;

import com.david.training.exceptions.DataException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public interface ContenidoService {
	
	
	//Lista de productos comprados polo usuario
	public Results<Contenido> miLista(String email, String idioma, int startIndex, int count )
			throws DataException;
	//Lista de produtos marcados como favoritos 
	public Results<Contenido> favoritos (String email, String idioma, int startIndex, int count)
			throws DataException;
	//Obtencion do precio descontado, e dicir o diñeiro que se rebaixa 
	public void precioDescontado (Contenido c)
			throws DataException;
	//filtros de busqueda
	public Results<Contenido> busquedaEstructurada (ProductoCriteria producto, String idioma, int startIndex, int count)
			throws DataException;
	//Calculo do precio descontado
	public Double sacarPrecioDescontado (Integer id)
			throws DataException;
	
	public Contenido findPorId(Integer id, String idioma)
			throws InstanceNotFoundException, DataException;
	
	public Results<Contenido> findAllByRebajas (String idioma, int startIndex, int count)
			throws DataException;
	
	public Results<Contenido> findAllByDate (String idioma, int startIndex, int count)
			throws DataException;

	public Results<Contenido> findAllByVentas (String idioma, int startIndex, int count)
			throws DataException;
}
