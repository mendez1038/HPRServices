package com.david.training.service;

import java.util.List;

import com.david.training.model.Usuario;

public interface UsuarioService {

	public Usuario signUp(Usuario u)
		throws Exception;
	
	public void editar(Usuario u)
			throws Exception;

	public boolean eliminar(String email)
			throws Exception;
	
	public Usuario buscarPorEmail(String email)
			throws Exception;
	
	public Boolean exists( String email)
			throws Exception;
	
	public long countAll() 
			throws Exception;

	public List<Usuario>  buscarPorNombre(String nombre )
			throws Exception;


}
