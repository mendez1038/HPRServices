package com.david.training.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.david.training.dao.FavoritoDAO;
import com.david.training.dao.PedidoDAO;
import com.david.training.dao.UsuarioDAO;
import com.david.training.dao.impl.ContenidoDAOImpl;
import com.david.training.dao.impl.FavoritoDAOImpl;
import com.david.training.dao.impl.PedidoDAOImpl;
import com.david.training.dao.impl.UsuarioDAOImpl;
import com.david.training.dao.util.ConnectionManager;
import com.david.training.dao.util.JDBCUtils;
import com.david.training.exceptions.DataException;
import com.david.training.exceptions.DuplicateInstanceException;
import com.david.training.exceptions.InstanceNotFoundException;
import com.david.training.exceptions.MailException;
import com.david.training.model.Usuario;
import com.david.training.service.MailService;
import com.david.training.service.UsuarioService;
import com.david.training.util.PasswordEncryptionUtil;

public class UsuarioServiceImpl implements UsuarioService{

	private UsuarioDAO dao = null;
	private FavoritoDAO favoritoDAO = null;
    private PedidoDAO pedidoDAO = null;

	public static Logger logger = LogManager.getLogger(ContenidoDAOImpl.class);
	public UsuarioServiceImpl() {
		dao = new UsuarioDAOImpl();
	    favoritoDAO = new FavoritoDAOImpl();
	    pedidoDAO = new PedidoDAOImpl();
	}
	
	@Override
	public Usuario signUp(Usuario u) 
			throws DuplicateInstanceException, DataException, MailException{
		boolean commit = false;
		Connection c = null;
		MailService mailService = null;
		try {
		mailService = new MailServiceImpl();
		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		u = dao.create(u, c);
		
		mailService.sendEmail("Gracias por registrarte en HPR, para iniciar sesion utilice este correo y esta contrase�a: "+u.getContrasena(),"Bienvenida!", u.getEmail());
		commit = true;
		return u;
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
	}	
	

	@Override
	public void update(Usuario u) 
			throws InstanceNotFoundException, DataException {
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		c.setAutoCommit(false);
		
		dao.update(u, c);
		commit = true;
		
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
	}

	@Override
    public void eliminarCuenta(String email, String passwordPlano)
            throws InstanceNotFoundException, DataException {

        boolean commit = false;
        try {
        	final Connection c = ConnectionManager.getConnection();
            c.setAutoCommit(false);

            // 1) Re-autenticación mínima
            Usuario u = dao.findByEmail(email, c);

            final boolean ok = PasswordEncryptionUtil.safeMatches(
                    passwordPlano,
                    u.getContrasena(),                   // el valor de BD
                    (nuevoHash) -> {                   // migración opcional si estaba en plano
                        try {
                            dao.updatePasswordHash(c, u.getEmail(), nuevoHash);
                        } catch (DataException e) {
                            // Si falla la migración no bloquees el login/borrado, solo loguea
                            logger.warn("No se pudo migrar el hash de contraseña para {}", u.getEmail(), e);
                        }
                        return null;
                    }
            );

            if (!ok) {
                throw new DataException("Credenciales inválidas");
            }


            // 2) Borrar FAVORITOS
            favoritoDAO.deleteByEmail(c, email);

            // 3) Borrar PEDIDOS (y sus líneas, usando tu DAO existente)
            List<Integer> ids = findPedidoIdsByEmail(c, email);
            for (Integer idPedido : ids) {
                pedidoDAO.delete(c, idPedido); // ya borra LINEAPEDIDO antes
            }

            // 4) Borrar USUARIO
            dao.delete(email, c); // lanza InstanceNotFound si 0 rows

            commit = true;

            JDBCUtils.closeConnection(c, commit);
        } catch (SQLException e) {
            throw new DataException(e);
        } 
    }
	
	 private List<Integer> findPedidoIdsByEmail(Connection c, String email) throws DataException {
	        String sql = "SELECT ID_PEDIDO FROM PEDIDO WHERE EMAIL = ?";
	        try (PreparedStatement ps = c.prepareStatement(sql);
	             ) {
	            ps.setString(1, email);
	            try (ResultSet rs = ps.executeQuery()) {
	                List<Integer> ids = new ArrayList<>();
	                while (rs.next()) ids.add(rs.getInt(1));
	                return ids;
	            }
	        } catch (SQLException e) {
	            throw new DataException(e);
	        }
	    }

	@Override
	public Usuario findByEmail(String email) 
			throws DataException {
		
//		if(!null) {
			//exitocache
//		} else {
//			
			//fallocache		
//			}
 		
		boolean commit = false;
		Connection c = null;
		try {

		c = ConnectionManager.getConnection();
		
		c.setAutoCommit(false);
		Usuario u = dao.findByEmail(email,c);
		//commit =true;
		return u;
		} catch (SQLException e) {
			logger.warn(e.getMessage(),e);
			throw new DataException(e);
		} finally {
			JDBCUtils.closeConnection(c, commit);
			}
		
	}

	@Override
	public Usuario signIn(String email, String contrasena) 
			throws DataException {
		if(logger.isDebugEnabled()) logger.debug("email: {}; contrasena: {}", email, contrasena);
		Connection c = null;
		try {
			c = ConnectionManager.getConnection();
			if (email == null || contrasena == null) {
				return null;
			}

			Usuario u = dao.findByEmail(email, c);

			if(u==null) {
				return u;
			}
			
			String stored = u.getContrasena();
			if(stored==null) {
				throw new DataException("Contraseña no establecida");
			}
			
			boolean ok = false;
	        if (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$")) {
	        	ok = PasswordEncryptionUtil.checkPassword(contrasena, u.getContrasena());
	        } else {
	        	ok = contrasena.equals(stored);
	        }

			if(ok) {
				return u;
			}else {
				if(logger.isDebugEnabled()) logger.debug("Contrasena inorrecta");
				throw new DataException("Datos mal introducidos");
			}

		}catch (SQLException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new DataException(ex);
		}
		finally {
			JDBCUtils.closeConnection(c);
		}
	}

}
