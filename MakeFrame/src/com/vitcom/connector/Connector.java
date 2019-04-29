package com.vitcom.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private String uri = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public Connector() {
		
	}
	public Connector(String uri) {
		this.uri = uri;
	}
	
	public Connection getConnection(String id, String pw) throws SQLException {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(uri, id, pw);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
