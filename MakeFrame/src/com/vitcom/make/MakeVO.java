/**
 * VO내용을 만드는 클래스
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.make;

import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;
import com.vitcom.util.FrameUtil;
import com.vitcom.util.VOUtil;

public class MakeVO {

	private String tblName;
	private List<Map<String, String>> dbList;
	
	public MakeVO(String tblName,List<Map<String, String>> dbList) {
		this.tblName = tblName;
		this.dbList = dbList;
	}
	
	/**
	 * VO내용을 반환하는 함수
	 * @return
	 */
	public String getVO() {
		FrameUtil frameUtil = new FrameUtil();
		VOUtil voUtil = new VOUtil();
		String className = frameUtil.getClsName("vo", tblName);
		
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
		sb.append(voUtil.getVariables(dbList));
		nextLine(sb);
		sb.append(voUtil.getEmptyConstructor(className));
		sb.append(voUtil.getFullConstructor(className, dbList));
		nextLine(sb);
		sb.append(voUtil.getGetterSetter(dbList));
		nextLine(sb);
		sb.append(voUtil.getToString());
		sb.append("}");
		
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
