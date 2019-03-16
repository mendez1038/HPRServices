package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.david.training.dao.TipoContenidoDAO;
import com.david.training.dao.impl.TipoContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.TipoContenido;
import com.david.training.service.Results;
import com.david.training.service.TipoContenidoService;

public class TipoContenidoServiceImpl implements TipoContenidoService{
	
	private TipoContenidoDAO dao = null;

	public TipoContenidoServiceImpl() {
		dao = new TipoContenidoDAOImpl();
	}

	@Override
	public Results<TipoContenido> findAll(String idioma, int startIndex, int count) 
			throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			Results<TipoContenido> tipos = dao.findAll(idioma, connection, startIndex, count);
			commit = true;
			return tipos;
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

}
