package com.david.training.dao.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager	 {

	private static Logger logger = LogManager.getLogger(ConnectionManager.class.getName());

	private static ResourceBundle dbConfiguration = ResourceBundle.getBundle("DBConfiguration");


	private static final String JDBC_DRIVER = "jdbc.driver.classname";
	private static final String DB_URL = "jdbc.url";
	private static final String USER = "jdbc.user";
	private static final String PASS = "jdbc.password";
	
	private static String url;
	private static String user;
	private static String password;

	private static ComboPooledDataSource dataSource = null;

	static {

		try {
			String driverClassName = dbConfiguration.getString(JDBC_DRIVER);
			url = dbConfiguration.getString(DB_URL);
			user = dbConfiguration.getString(USER);
			password = dbConfiguration.getString(PASS);

			dataSource = new ComboPooledDataSource();
			dataSource.setDriverClass(driverClassName);            
			dataSource.setJdbcUrl(url);
			dataSource.setUser(user);                                  
			dataSource.setPassword(password);
			
		} catch (Exception e) {
			
			logger.fatal(e.getMessage(), e); 
		}

	}

	private ConnectionManager() {}

	public final static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}