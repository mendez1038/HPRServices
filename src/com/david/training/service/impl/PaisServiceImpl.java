package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.david.training.dao.PaisDAO;
import com.david.training.dao.impl.PaisDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Pais;
import com.david.training.service.PaisService;
import com.david.training.service.Results;

public class PaisServiceImpl implements PaisService{
	
	private PaisDAO dao = null;

	public PaisServiceImpl() {
		dao = new PaisDAOImpl();
	}

	@Override
	public Results<Pais> findAll(String idioma, int startIndex, int count) 
			throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			Results<Pais> paises = dao.findAll(idioma, connection, startIndex, count);
			commit = true;
			return paises;
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}
	

}
