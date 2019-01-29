
package com.david.training.dao;





import java.sql.Connection;
import java.util.List;

import com.david.training.model.Descuento;

public interface DescuentoDAO {
	
	public Descuento findById(Integer id, String idioma, Connection c)
		throws Exception;
	
	public List<Descuento> findByPorcentaje (Integer porcentaje, String idioma, Connection c)
			throws Exception;
	
	public List<Descuento> findAll(Connection c)
			throws Exception;
	
	

}
