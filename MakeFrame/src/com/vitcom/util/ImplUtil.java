/**
 * ManagerImpl 상세내용을 테이블 데이터에서부터 만드는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;

public class ImplUtil {
	
	private FrameUtil frameUtil;
	
	public ImplUtil() {
		frameUtil = new FrameUtil();
	}
	
	public String getHeader(Map<String, List<Map<String, String>>> tblMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + MakeGUI.pack+".service.impl;"); nextLine(sb);
		nextLine(sb);
		sb.append("import java.util.List;"); nextLine(sb);
		nextLine(sb);
		sb.append("import org.slf4j.Logger;"); nextLine(sb);
		sb.append("import org.slf4j.LoggerFactory;"); nextLine(sb);
		sb.append("import org.springframework.beans.factory.annotation.Autowired;"); nextLine(sb);
		sb.append("import org.springframework.stereotype.Service;"); nextLine(sb);
		nextLine(sb);
		sb.append("import "+MakeGUI.pack+".service."+frameUtil.getClsName("manager")+";"); nextLine(sb);
		sb.append("import "+MakeGUI.pack+".dao."+frameUtil.getClsName("mapper")+";"); nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+";"); nextLine(sb);
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("form", tblName)+";"); nextLine(sb);
		}
		nextLine(sb);
		sb.append("@Service(\""+frameUtil.getClsNameVar("manager")+"\")"); nextLine(sb);
		
		sb.append("public class "+frameUtil.getClsName("impl")+" implements "+frameUtil.getClsName("manager")+" {"); nextLine(sb);
		nextLine(sb);
		sb.append("\tprivate static final Logger logger = LoggerFactory.getLogger("+frameUtil.getClsName("impl")+".class);"); nextLine(sb);
		nextLine(sb);
		sb.append("\t@Autowired"); nextLine(sb);
		sb.append("\tprivate "+frameUtil.getClsName("mapper")+" "+frameUtil.getClsNameVar("mapper")+";"); nextLine(sb);
		return sb.toString();
	}
	public String getInsert(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Override"); nextLine(sb);
		sb.append("\tpublic void insert"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("mapper")+"."+"insert"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getSelect(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Override"); nextLine(sb);
		sb.append("\tpublic List<"+frameUtil.getClsName("vo", tblName)+"> select"+frameUtil.getCamelCase(tblName)+"() {"); nextLine(sb);
		sb.append("\t\treturn "+frameUtil.getClsNameVar("mapper")+".select"+frameUtil.getCamelCase(tblName)+"();"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getSelectOne(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Override"); nextLine(sb);
		sb.append("\tpublic "+frameUtil.getClsName("vo", tblName)+" selectOne"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+") {"); nextLine(sb);
		sb.append("\t\treturn "+frameUtil.getClsNameVar("mapper")+".selectOne"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("form", tblName)+");"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getUpdate(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Override"); nextLine(sb);
		sb.append("\tpublic void update"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("mapper")+"."+"update"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getDelete(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@Override"); nextLine(sb);
		sb.append("\tpublic void delete"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("mapper")+".delete"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("form", tblName)+");"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
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
