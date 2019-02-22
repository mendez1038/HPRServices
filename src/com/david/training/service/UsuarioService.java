package com.david.training.service;


import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.exceptions.MailException;
import com.david.training.model.Favorito;
import com.david.training.model.Usuario;

public interface UsuarioService {

	public Usuario signUp(Usuario u)
		throws DuplicateInstanceException, DataException, MailException;
	
	public void update(Usuario u)
			throws InstanceNotFoundException, DataException;

	public boolean delete(String email)
			throws InstanceNotFoundException, DataException;
	
	public Usuario signIn(String email, String contrasena)
			throws DataException;

	public Usuario findByEmail(String email)
			throws DataException;
	
	public Favorito añadirFavorito(Favorito f)
			throws DataException;
	
	public void eliminarFavorito(Favorito f)
			throws DataException;
}
