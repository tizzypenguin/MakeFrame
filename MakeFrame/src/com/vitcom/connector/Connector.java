/**
 * 해당하는 uri에 대한 Connection객체를 반환하는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
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
	
	/**
	 * 해당하는 uri에 대한 Connection 반환
	 * @param id	db접속 아이디
	 * @param pw	db접속 비밀번호
	 * @return		db와 연결된 Connection 객체
	 * @throws SQLException
	 */
	public Connection getConnection(String id, String pw) throws SQLException {
		return DriverManager.getConnection(uri, id, pw);
	}
}
