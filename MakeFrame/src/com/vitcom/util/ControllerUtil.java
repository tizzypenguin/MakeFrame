package com.vitcom.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;

public class ControllerUtil {
	
	private FrameUtil frameUtil;
	
	public ControllerUtil() {
		frameUtil = new FrameUtil();
	}
	
	public String getHeader(Map<String, List<Map<String, String>>> tblMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("package " + MakeGUI.pack+";"); nextLine(sb);
		nextLine(sb);
		sb.append("import org.slf4j.Logger;"); nextLine(sb);
		sb.append("import org.slf4j.LoggerFactory;"); nextLine(sb);
		sb.append("import org.springframework.beans.factory.annotation.Autowired;"); nextLine(sb);
		sb.append("import org.springframework.stereotype.Controller;"); nextLine(sb);
		sb.append("import org.springframework.ui.Model;"); nextLine(sb);
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;"); nextLine(sb);
		nextLine(sb);
		sb.append("import "+MakeGUI.pack+".service."+frameUtil.getClsName("manager")+";"); nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+";"); nextLine(sb);
			sb.append("import "+MakeGUI.pack+".model."+frameUtil.getClsName("form", tblName)+";"); nextLine(sb);
		}
		nextLine(sb);
		sb.append("@Controller"); nextLine(sb);
		sb.append("@RequestMapping(\"/"+MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]+"\")"); nextLine(sb);
		sb.append("public class "+frameUtil.getClsName("controller")+" {"); nextLine(sb);
		nextLine(sb);
		sb.append("\tprivate static final Logger logger = LoggerFactory.getLogger("+frameUtil.getClsName("controller")+".class);"); nextLine(sb);
		nextLine(sb);
		sb.append("\t@Autowired"); nextLine(sb);
		sb.append("\tprivate "+frameUtil.getClsName("manager")+" "+frameUtil.getClsNameVar("manager")+";"); nextLine(sb);
		return sb.toString();
	}
	public String getInsert(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@RequestMapping(\"/insert"+frameUtil.getCamelCase(tblName)+".json\")"); nextLine(sb);
		sb.append("\tpublic String insert"+frameUtil.getCamelCase(tblName)+"(Model model, "+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("manager")+"."+"insert"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		sb.append("\t\treturn \"jsonView\";"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getSelect(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@RequestMapping(\"/select"+frameUtil.getCamelCase(tblName)+".json\")"); nextLine(sb);
		sb.append("\tpublic String select"+frameUtil.getCamelCase(tblName)+"(Model model) {"); nextLine(sb);
		sb.append("\t\tmodel.addAttribute(\"list\", "+frameUtil.getClsNameVar("manager")+".select"+frameUtil.getCamelCase(tblName)+"()"+");"); nextLine(sb);
		sb.append("\t\treturn \"jsonView\";"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getSelectOne(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@RequestMapping(\"/selectOne"+frameUtil.getCamelCase(tblName)+".json\")"); nextLine(sb);
		sb.append("\tpublic String selectOne"+frameUtil.getCamelCase(tblName)+"(Model model, "+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+") {"); nextLine(sb);
		sb.append("\t\tmodel.addAttribute(\"vo\", "+frameUtil.getClsNameVar("manager")+".selectOne"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("form", tblName)+")"+");"); nextLine(sb);
		sb.append("\t\treturn \"jsonView\";"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getUpdate(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@RequestMapping(\"/update"+frameUtil.getCamelCase(tblName)+".json\")"); nextLine(sb);
		sb.append("\tpublic String update"+frameUtil.getCamelCase(tblName)+"(Model model, "+frameUtil.getClsName("vo", tblName)+" "+frameUtil.getClsNameVar("vo", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("manager")+"."+"update"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("vo", tblName)+");"); nextLine(sb);
		sb.append("\t\treturn \"jsonView\";"); nextLine(sb);
		sb.append("\t}"); nextLine(sb);
		return sb.toString();
	}
	public String getDelete(String tblName) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t@RequestMapping(\"/delete"+frameUtil.getCamelCase(tblName)+".json\")"); nextLine(sb);
		sb.append("\tpublic String delete"+frameUtil.getCamelCase(tblName)+"(Model model, "+frameUtil.getClsName("form", tblName)+" "+frameUtil.getClsNameVar("form", tblName)+") {"); nextLine(sb);
		sb.append("\t\t"+frameUtil.getClsNameVar("manager")+".delete"+frameUtil.getCamelCase(tblName)+"("+frameUtil.getClsNameVar("form", tblName)+");"); nextLine(sb);
		sb.append("\t\treturn \"jsonView\";"); nextLine(sb);
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
