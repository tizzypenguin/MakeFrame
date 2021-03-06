/**
 * Manager 상세내용을 테이블 데이터에서부터 만드는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;

public class ManagerUtil {
	
	private FrameUtil frameUtil;
	
	public ManagerUtil() {
		frameUtil = new FrameUtil();
	}
	
	public String getHeader(Map<String, List<Map<String, String>>> tblMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + MakeGUI.pack+".service;"); nextLine(sb);
		nextLine(sb);
		sb.append("import java.util.List;"); nextLine(sb);
		nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+";"); nextLine(sb);
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("form", tblName)+";"); nextLine(sb);
		}
		nextLine(sb);
		sb.append("public interface "+frameUtil.getClsName("manager")+" {"); nextLine(sb);
		return sb.toString();
	}
	public String getInsert(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tvoid insert"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		return sb.toString();
	}
	public String getSelect(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tList<"+frameUtil.getClsName("vo", tblName)+"> select"+frameUtil.getCamelCase(tblName)+"();"); nextLine(sb);
		return sb.toString();
	}
	public String getSelectOne(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t"+frameUtil.getClsName("vo", tblName)+" selectOne"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+");"); nextLine(sb);
		return sb.toString();
	}
	public String getUpdate(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tvoid update"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		return sb.toString();
	}
	public String getDelete(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\tvoid delete"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+");"); nextLine(sb);
		return sb.toString();
	}
	public String getFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append("}");
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
