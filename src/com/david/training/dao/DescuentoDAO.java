package com.david.training.dao;



import com.david.training.model.Descuento;

public interface DescuentoDAO {
	
	public Descuento findById(Integer id)
		throws Exception;
	
	public Descuento create (Descuento d)
			throws Exception;
	
	

}
