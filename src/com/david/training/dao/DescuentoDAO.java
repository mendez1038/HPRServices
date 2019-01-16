package com.david.training.dao;



import java.util.List;

import com.david.training.model.Descuento;

public interface DescuentoDAO {
	
	public Descuento findById(Integer id)
		throws Exception;
	
	
	public Descuento create (Descuento d)
			throws Exception;
	
	public long  delete (Integer id)
			throws Exception;
	//Falta test
	public boolean update(Descuento d)
			throws Exception;
	
	public List<Descuento> findAll()
			throws Exception;
	
	

}
