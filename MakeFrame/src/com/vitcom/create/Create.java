package com.vitcom.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.vitcom.connector.Connector;
import com.vitcom.frame.MakeGUI;
import com.vitcom.template.VOTemplate;

public class Create {
	private Connection con;
	
	public Create(Connection con) {
		String qry = "SELECT A.COLUMN_NAME, A.DATA_TYPE, B.COMMENTS FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B WHERE A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME AND A.TABLE_NAME = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
//		ps.setString(1, MakeGUI.table);
//		rs = ps.executeQuery();
//		while(rs.next()) {
//			
//		}
		
//		VOTemplate vo = new VOTemplate();		
	}
}
