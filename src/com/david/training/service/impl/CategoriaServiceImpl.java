package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.david.training.dao.CategoriaDAO;
import com.david.training.dao.impl.CategoriaDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Categoria;
import com.david.training.service.CategoriaService;
import com.david.training.service.Results;

public class CategoriaServiceImpl implements CategoriaService{

	private CategoriaDAO dao = null;

	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl();
	}

	@Override
	public Results<Categoria> findAll(String idioma, int startIndex, int count) 
			throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			Results<Categoria> categorias = dao.findAll(idioma, connection, startIndex, count);	
			commit = true;
			return categorias;
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

}
