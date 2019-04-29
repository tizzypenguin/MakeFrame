package com.vitcom.util;

import java.util.List;
import java.util.Map;

public class FrameUtil {
	
	private String capitalize(String str) {
		String capitalized = str.toUpperCase().charAt(0)+str.substring(1,str.length());
		return capitalized;
	}
	private String getCamelCase(String name) {
		name = name.toLowerCase();
		String returnStr = "";
		String [] strArr = name.split("_");
		for(String str:strArr) {
			returnStr+=capitalize(str);
		}
		return returnStr;
	}
	
	public String getClsName(String type, String tbl) {
		switch (type) {
			case "vo":
				break;
			case "form":
				break;
			case "table":
				break;
			case "manager":
				break;
			case "impl":
				break;
			case "controller":
				break;
			case "xml":
				break;
			default:
				break;
		}
		return null;
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
					"\tpublic "+type+" get"+capitalize(variable)+"() {\r\n"
					+ "\t\treturn "+variable+";\r\n"
					+ "\t}\r\n";
			String setter = 
					"\tpublic void set"+capitalize(variable)+"("+type+" "+variable+") {\r\n"
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
