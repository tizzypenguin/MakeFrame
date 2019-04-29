package com.vitcom.util;

import java.util.List;
import java.util.Map;

public class VOUtil {
	private FrameUtil util;
	
	public VOUtil() {
		util = new FrameUtil();
	}
	
	
	//VOUtil 로 이동 필요
	public String getEmptyConstructor(String className) {
		String str = "\tpublic "+className+"() {\r\n"
				+ "\t}\r\n";
		return str;
	}
	public String getFullConstructor(String className, List<Map<String, String>> dbList) {
		String str = "\tpublic "+className;
		
		String bracket = "(";
		for(int i=0; i<dbList.size(); i++) {
			bracket = bracket + dbList.get(i).get("DATA_TYPE") + " " + dbList.get(i).get("COLUMN_NAME")+", ";
		}
		bracket = bracket.substring(0, bracket.length()-2);
		bracket+=") {";
		
		String variables = "";
		for(int i=0; i<dbList.size(); i++) {
			variables+="\r\n\t\tthis."+dbList.get(i).get("COLUMN_NAME")+" = "+dbList.get(i).get("DATA_TYPE")+";";
		}
		
		str+=bracket;
		str+=variables;
		str+="\r\n\t}\r\n";
		
		return str;
	}
	public String getGetterSetter(List<Map<String, String>> dbList) {
		String str = "";
		for(int i=0; i<dbList.size();i++) {
			String type = dbList.get(i).get("DATA_TYPE");
			String variable = dbList.get(i).get("COLUMN_NAME");
			String getter = 
					"\tpublic "+type+" get"+util.capitalize(variable)+"() {\r\n"
					+ "\t\treturn "+variable+";\r\n"
					+ "\t}\r\n";
			String setter = 
					"\tpublic void set"+util.capitalize(variable)+"("+type+" "+variable+") {\r\n"
					+ "\t\tthis."+variable+" = "+variable+";\r\n"
					+ "\t}\r\n";
			str += getter;
			str += setter;
		}
		return str;
	}
	public String getToString() {
		String str = 
				"\tpublic String toString() {\r\n"
				+ "\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);\r\n"
				+ "\t}\r\n";
		return str;
	}
	////VOUtil 로 이동 필요
}
