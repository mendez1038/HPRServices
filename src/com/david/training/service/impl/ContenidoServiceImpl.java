package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.david.training.dao.ContenidoDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.model.Contenido;
import com.david.training.model.ProductoCriteria;
import com.david.training.service.ContenidoService;


public class ContenidoServiceImpl implements ContenidoService{
	
	private ContenidoDAO dao = null;
	
	public ContenidoServiceImpl()  {
		dao = new ContenidoDAOImpl();
	}

	@Override
	public List<Contenido> miLista(String email, String idioma) throws Exception {
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Contenido> lista = dao.findLista(c, email, idioma);
			
			commit = true; 
			return lista;
		}  catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}

	@Override
	public List<Contenido> favoritos(String email, String idioma) throws Exception {
		
		boolean commit = false;
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			c.setAutoCommit(false);
			List<Contenido> favoritos = dao.findFavoritos(c, email, idioma);
			
			commit = true; 
			return favoritos;
		}  catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}}

	@Override
	public boolean precioDescontado(Contenido c, String idioma) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Contenido> busquedaEstructurada(ProductoCriteria producto, String idioma) throws Exception {
		boolean commit=false;
		Connection c=null;
		try {
		c=ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		List<Contenido> productos = dao.findByCriteria(c, producto, idioma);
		
		return productos;
		
		}catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			JDBCUtils.closeConnection(c, commit);
		}}

}
