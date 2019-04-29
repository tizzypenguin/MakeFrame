package com.vitcom.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetVO {
	public String getVO(Connection con, String tableName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String qry = 
				"SELECT 'private ' || DECODE(DATA_TYPE,'DATE', 'DATE'\r\n" + 
						"                             ,'CHAR'    , 'String'\r\n" + 
						"                             ,'VARCHAR2', 'String'\r\n" + 
						"                             ,'NUMBER'  , 'int'\r\n" + 
						"                             ,'TIMPSTAMP'  , 'Timpstamp'\r\n" +
						"                             ,DATA_TYPE)\r\n" + 
						"                  || ' ' || LOWER(A.COLUMN_NAME) || ';' \r\n" + 
						"                  || '                               // ' \r\n" + 
						"                  || B.COMMENTS\r\n" + 
						"  FROM USER_TAB_COLUMNS A\r\n" + 
						"     , USER_COL_COMMENTS B \r\n" + 
						" WHERE 1=1\r\n" + 
						"   AND A.TABLE_NAME  = B.TABLE_NAME\r\n" + 
						"   AND A.COLUMN_NAME = B.COLUMN_NAME\r\n" + 
						"   AND A.TABLE_NAME  = ?\r\n" + 
						"\r\n";
		String returnStr = "public class "+getClassName(tableName)+" {\r\n";
		List<String> typeList = new ArrayList<String>();
		List<String> variableList = new ArrayList<String>(); 
		
		try {
			ps = con.prepareStatement(qry);
			ps.setString(1, tableName);
			rs = ps.executeQuery();
			while(rs.next()) {
				String line = rs.getString(1);
				String [] strArray = line.split(" ");
				typeList.add(strArray[1]);
				variableList.add(strArray[2].substring(0, strArray[2].length()-1));
				returnStr += ("\t"+line+"\r\n");
			}
			returnStr+="\r\n";
			returnStr+=getEmptyConstructor(getClassName(tableName));
			returnStr+=getFullConstructor(getClassName(tableName),typeList,variableList);
			returnStr+="\r\n";
			for(int i=0; i<typeList.size();i++) {
				String type = typeList.get(i);
				String variable = variableList.get(i);
				String getter = 
						"\tpublic "+type+" get"+capitalize(variable)+"() {\r\n"
						+ "\t\treturn "+variable+";\r\n"
						+ "\t}\r\n";
				String setter = 
						"\tpublic void set"+capitalize(variable)+"("+type+" "+variable+") {\r\n"
						+ "\t\tthis."+variable+" = "+variable+";\r\n"
						+ "\t}\r\n";
				returnStr += getter;
				returnStr += setter;
			}
			returnStr+="\r\n";
			returnStr+=getToString();
			returnStr+="}";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	
	private String capitalize(String str) {
		String capitalized = str.toUpperCase().charAt(0)+str.substring(1,str.length());
		return capitalized;
	}
	public String getClassName(String tableName) {
		tableName = tableName.toLowerCase();
		String returnStr = "";
		String [] strArr = tableName.split("_");
		for(String str:strArr) {
			returnStr+=capitalize(str);
		}
		return returnStr;
	}
	private String getToString() {
		String str = 
				"\tpublic String toString() {\r\n"
				+ "\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);\r\n"
				+ "\t}\r\n";
		return str;
	}
	private String getEmptyConstructor(String className) {
		String str = "\tpublic "+className+"() {\r\n"
				+ "\t}\r\n";
		return str;
	}
	private String getFullConstructor(String className, List<String> typeList, List<String> variableList) {
		String str = "\tpublic "+className;
		
		String bracket = "(";
		for(int i=0; i<typeList.size(); i++) {
			bracket=bracket+typeList.get(i)+" "+variableList.get(i)+", ";
		}
		bracket = bracket.substring(0, bracket.length()-2);
		bracket+=") {";
		
		String variables = "";
		for(int i=0; i<typeList.size(); i++) {
			variables+="\r\n\t\tthis."+variableList.get(i)+" = "+variableList.get(i)+";";
		}
		
		str+=bracket;
		str+=variables;
		str+="\r\n\t}\r\n";
		
		return str;
	}
}
