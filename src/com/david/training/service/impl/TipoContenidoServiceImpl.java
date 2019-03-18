package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.TipoContenidoDAO;
import com.david.training.dao.impl.TipoContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.model.TipoContenido;
import com.david.training.service.TipoContenidoService;

public class TipoContenidoServiceImpl implements TipoContenidoService{
	
	private TipoContenidoDAO dao = null;

	public TipoContenidoServiceImpl() {
		dao = new TipoContenidoDAOImpl();
	}

	@Override
	public List<TipoContenido> findAll(String idioma) 
			throws DataException {
		boolean commit = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			List<TipoContenido> tipos = dao.findAll(idioma, connection);
			commit = true;
			return tipos;
		} catch (SQLException e){
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(connection, commit);
		}
	}

}
