package com.vitcom.util;

import java.util.List;
import java.util.Map;

public class VOUtil {
	private FrameUtil util;
	
	public VOUtil() {
		util = new FrameUtil();
	}
	
	public String getVariables(List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< dbList.size(); i++) {
			sb.append("\tprivate "+ dbList.get(i).get("DATA_CLASS") + " " + dbList.get(i).get("COLUMN_NAME").toLowerCase() +";"); nextLine(sb);
		}
		return sb.toString();
	}
	public String getEmptyConstructor(String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic "+className+"() {"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getFullConstructor(String className, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic "+className+"(");
		for(int i=0; i<dbList.size(); i++) {
			sb.append(dbList.get(i).get("DATA_CLASS") + " " + dbList.get(i).get("COLUMN_NAME").toLowerCase()+", ");
		}
		sb.append(") {"); nextLine(sb);
		for(int i=0; i<dbList.size(); i++) {
			sb.append("\t\tthis."+dbList.get(i).get("COLUMN_NAME").toLowerCase()+" = "+dbList.get(i).get("DATA_CLASS")+";"); nextLine(sb);
		}
		nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		
		return sb.toString();
	}
	public String getGetterSetter(List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<dbList.size();i++) {
			String type = dbList.get(i).get("DATA_CLASS");
			String variable = dbList.get(i).get("COLUMN_NAME").toLowerCase();
			
			//getter
			sb.append("\tpublic "+type+" get"+util.capitalize(variable)+"() {"); nextLine(sb);
			sb.append("\t\treturn "+variable+";"); nextLine(sb);
			sb.append("\t}"); nextLine(sb);
			//setter
			sb.append("\tpublic void set"+util.capitalize(variable)+"("+type+" "+variable+") {"); nextLine(sb);
			sb.append("\t\tthis."+variable+" = "+variable+";"); nextLine(sb);
			sb.append("\t}"); nextLine(sb);
		}
		return sb.toString();
	}
	public String getToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\tpublic String toString() {"); nextLine(sb);
		sb.append("\t\treturn ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
