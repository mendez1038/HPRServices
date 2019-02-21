package com.david.training.service;



import com.david.training.model.Favorito;
import com.david.training.model.Usuario;

public interface UsuarioService {

	public Usuario signUp(Usuario u)
		throws Exception;
	
	public void update(Usuario u)
			throws Exception;

	public boolean delete(String email)
			throws Exception;
	
	public Usuario signIn(String email, String contrasena)
			throws Exception;

	public Usuario findByEmail(String email)
			throws Exception;
	
	public Favorito añadirFavorito(Favorito f)
			throws Exception;
	
	public void eliminarFavorito(Favorito f)
			throws Exception;
}
