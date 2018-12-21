package com.david.training.dao;

import com.david.training.model.Usuario;

public interface UsuarioDAO {
	
	public Usuario findByEmail()
	throws Exception;
	
	public Usuario exists()
	throws Exception;
	
	public Usuario create()
	throws Exception;

}
