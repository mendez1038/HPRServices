package com.david.training.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.david.training.dao.IdLineaPedido;
import com.david.training.dao.LineaPedidoDAO;
import com.david.training.exceptions.DataException;
import com.david.training.model.LineaPedido;

public class LineaPedidoDAOImpl implements LineaPedidoDAO{

	@Override
	public LineaPedido findById(Connection connection, IdLineaPedido id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(Connection connection, IdLineaPedido id) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineaPedido> findByTicket(Connection connection, Long idTicket) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaPedido create(Connection connection, LineaPedido c) throws DataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long delete(Connection connection, IdLineaPedido id) throws DataException {
		// TODO Auto-generated method stub
		return 0;
	}

}
