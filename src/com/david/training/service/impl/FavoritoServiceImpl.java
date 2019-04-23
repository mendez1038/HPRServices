package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.FavoritoDAO;
import com.david.training.dao.impl.FavoritoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Favorito;
import com.david.training.service.FavoritoService;

public class FavoritoServiceImpl implements FavoritoService{
	
	private FavoritoDAO dao = null;
	public static Logger logger = LogManager.getLogger(FavoritoDAOImpl.class);
	public FavoritoServiceImpl() {
		dao = new FavoritoDAOImpl();
	}
	
	@Override
	public Favorito añadirFavorito(Favorito f) 
			throws DataException {
		boolean commit = false;
		Connection c = null;
		try {
		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		if (dao.existsFavorito(f.getEmail(), f.getIdContenido(), c)) {
			f = dao.updateAnadirFavoritos(f, c);
		} else {
		f = dao.create(f,c);
		}
		commit = true;
		return f;
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

	@Override
	public void upadteEliminarFavorito(Favorito f) throws DataException {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		dao.updateEliminarFavoritos(f, c);
		commit = true;
		
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
		
	}

}
