package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.model.Usuario;
import com.david.training.service.MailService;
import com.david.training.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioDAO dao = null;
	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	}
	
	@Override
	public Usuario signUp(Usuario u) throws Exception {
		boolean commit = false;
		Connection c = null;
		MailService mailService = null;
		try {
		mailService = new MailServiceImpl();
		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		u = dao.create(u, c);
		
		mailService.sendEmail("Gracias por registrarte en HPR, para iniciar sesion utilice este correo y esta contraseņa: "+u.getContrasena(),"Bienvenida!", u.getEmail());
		commit = true;
		return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}	
	

	@Override
	public void update(Usuario u) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		dao.update(u, c);
		commit = true;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
	}

	@Override
	public boolean delete(String email) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(false);
		dao.delete(email,c);
		commit = true;
		return commit;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

	@Override
	public Usuario findByEmail(String email) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(false);
		Usuario u = dao.findByEmail(email,c);
		//commit =true;
		return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

	

	

	@Override
	public Usuario signIn(String email, String contrasena) throws Exception {
		
		//Validaciones
		
		Usuario u = findByEmail(email);
		if (u==null) {
			return null;
		}
		if (u.getContrasena().equals(contrasena)) {
			return u;
		}
		return null;
	}

}
