package com.david.training.service;

import java.util.List;

import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;

public interface ContenidoService {
	
	
	//Lista de productos comprados polo usuario
	public List<Contenido> miLista(String email, String idioma)
			throws Exception;
	//Lista de produtos marcados como favoritos 
	public List<Contenido> favoritos (String email, String idioma)
			throws Exception;
	//Obtencion do precio descontado, e dicir o diñeiro que se rebaixa 
	public void precioDescontado (Contenido c)
			throws Exception;
	//filtros de busqueda
	public List<Contenido> busquedaEstructurada (ProductoCriteria producto, String idioma)
			throws Exception;
	//Calculo do precio descontado
	public Double sacarPrecioDescontado (Integer id)
			throws Exception;
	//Falta añadir/eliminar favoritos

}
