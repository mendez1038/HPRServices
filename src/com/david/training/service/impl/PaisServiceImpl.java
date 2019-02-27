package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.PaisDAO;
import com.david.training.dao.impl.PaisDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.Pais;
import com.david.training.service.PaisService;

public class PaisServiceImpl implements PaisService{
	
	private PaisDAO dao = null;

	public PaisServiceImpl() {
		dao = new PaisDAOImpl();
	}

	@Override
	public List<Pais> findAll(String idioma) throws DataException {
		Connection connection = null;

		try {

			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(true);

			return dao.findAll(idioma, connection);

		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection);
		}
	}
	

}
