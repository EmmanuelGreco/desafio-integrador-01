package com.educacionit.dao.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionDB {
	
	default Connection getConnection() throws SQLException {
		try {
			
			final String DBURL = "jdbc:mysql://localhost/desafio_integrador_01";
		    final String DBUSER = "root";
		    final String DBPASSWORD = "admin";
		    
			Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
			
			return conn;		
		
		} catch (SQLException ex) {
			throw new SQLException(ex.getMessage());					
		}
	}
}
