package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.CategoriaDAO;
import com.david.training.dao.impl.CategoriaDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Categoria;
import com.david.training.service.CategoriaService;

public class CategoriaServiceImpl implements CategoriaService{

	private CategoriaDAO dao = null;

	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl();
	}

	@Override
	public List<Categoria> findAll(String idioma) 
			throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			List<Categoria> categorias = dao.findAll(idioma, connection);	
			commit = true;
			return categorias;
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

}
