package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.model.Usuario;
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
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(false);
		
		UsuarioDAO dao = new UsuarioDAOImpl();
		u = dao.create(u);
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
	public void editar(Usuario u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eliminar(String email) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(true);
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
	public Usuario buscarPorEmail(String email) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(true);
		Usuario u = dao.findByEmail(email,c);
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
	public List<Usuario> buscarPorNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countAll() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
