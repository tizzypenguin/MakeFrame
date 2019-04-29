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
//		String qry = "SELECT A.COLUMN_NAME, A.DATA_TYPE, B.COMMENTS FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B WHERE A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME AND A.TABLE_NAME = ?;";
		String qry = 
			"SELECT "+
				"UTC.COLUMN_NAME AS COLUMN_NAME, "+
				"UTC.DATA_TYPE AS DATA_TYPE, "+
				"DECODE "+
					"( "+
						"REGEXP_SUBSTR(UTC.DATA_TYPE,'[^(]+'), "+
						"'DATE', 'Date', "+
						"'CHAR', 'String', "+
						"'VARCHAR2', 'String', "+
						"'NUMBER', 'int', "+
						"'TIMESTAMP', 'Timestamp' "+
					") AS DATA_CLASS, "+
				"UCCO.COMMENTS AS COMMENTS, "+
				"PK.YN_PK AS YN_PK "+
			"FROM "+
				"USER_TAB_COLUMNS UTC "+
			"LEFT JOIN "+
				"USER_COL_COMMENTS UCCO "+
			"ON "+
				"UTC.TABLE_NAME = UCCO.TABLE_NAME "+
				"AND UTC.COLUMN_NAME = UCCO.COLUMN_NAME "+
			"LEFT JOIN "+
				"( "+
					"SELECT "+
						"UC.TABLE_NAME, "+
						"UCC.COLUMN_NAME, "+
						"'Y' AS YN_PK "+
					"FROM "+
						"USER_CONSTRAINTS UC "+
					"INNER JOIN "+
						"USER_CONS_COLUMNS UCC "+
					"ON "+
						"UC.CONSTRAINT_NAME = UCC.CONSTRAINT_NAME "+
					"WHERE "+
							"UC.TABLE_NAME = 'MARKET_STOCK_HISTORY' "+
						"AND UC.CONSTRAINT_TYPE = 'P' "+
				") PK "+
			"ON		UTC.TABLE_NAME = PK.TABLE_NAME "+
				"AND UTC.COLUMN_NAME = PK.COLUMN_NAME "+
			"WHERE UTC.TABLE_NAME = 'MARKET_STOCK_HISTORY'; ";

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
