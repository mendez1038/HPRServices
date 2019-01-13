package com.david.training.dao;


import com.david.training.model.Categoria;


public interface CategoriaDAO {

	public Categoria findById(Integer id)
		throws Exception;
	
	public Categoria findAll() 
		throws Exception;
	//innecesario
	public Categoria create(Categoria c) 
		throws Exception;

		
		
}
