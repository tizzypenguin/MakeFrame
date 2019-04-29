package com.vitcom.make;

import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;
import com.vitcom.util.FrameUtil;

public class MakeVO {
	
	private List<Map<String, String>> dbList;
	
	public MakeVO(List<Map<String, String>> dbList) {
		this.dbList = dbList;
	}
	public String getVO() {
		FrameUtil util = new FrameUtil();
		String className = "";//util.getClassName(MakeGUI.table) + "VO";
		
		StringBuilder sb = new  StringBuilder();
		
		sb.append("package "+ MakeGUI.pack + ".model;"); nextLine(sb);
		nextLine(sb);
		sb.append("import java.sql.Timestamp;"); nextLine(sb);
		nextLine(sb);
		sb.append("import org.apache.commons.lang.builder.ToStringBuilder;"); nextLine(sb);
		sb.append("import org.apache.commons.lang.builder.ToStringStyle;"); nextLine(sb);
		nextLine(sb);
		sb.append("public class " + className + " {"); nextLine(sb);
		nextLine(sb);
		sb.append(util.getEmptyConstructor(className));
		sb.append(util.getFullConstructor(className, dbList));
		nextLine(sb);
		sb.append(util.getGetterSetter(dbList));
		nextLine(sb);
		sb.append(util.getToString());
		sb.append("}");
		
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("/r/n");
	}
}
