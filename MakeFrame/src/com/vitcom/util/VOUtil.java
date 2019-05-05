/**
 * VO,Form 상세내용을 테이블 데이터에서부터 만드는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
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
		sb.delete(sb.length()-2, sb.length());
		sb.append(") {"); nextLine(sb);
		for(int i=0; i<dbList.size(); i++) {
			sb.append("\t\tthis."+dbList.get(i).get("COLUMN_NAME").toLowerCase()+" = "+dbList.get(i).get("COLUMN_NAME").toLowerCase()+";"); nextLine(sb);
		}
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
