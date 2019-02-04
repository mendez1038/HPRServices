package com.david.training.service;

import java.util.List;

import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public interface ContenidoService {
	
	public List<Contenido> miLista(String email, String idioma)
			throws Exception;
	
	public List<Contenido> favoritos (String email, String idioma)
			throws Exception;
	
	public boolean precioDescontado (Contenido c, String idioma)
			throws Exception;
	
	public List<Contenido> busquedaEstructurada (ProductoCriteria producto, String idioma)
			throws Exception;
	
	//añadir eliminar favoritos

}
