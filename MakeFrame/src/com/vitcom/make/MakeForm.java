/**
 * Form내용을 만드는 클래스
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

public class MakeForm {

	private String tblName;
	private List<Map<String, String>> pkList;
	FrameUtil frameUtil;

	public MakeForm(String tblName,List<Map<String, String>> dbList) {
		this.tblName = tblName;
		
		//pkList를 가져오기 위해 미리 frameUtil 선언
		frameUtil = new FrameUtil();
		this.pkList = frameUtil.getPkList(dbList);
	}
	
	/**
	 * Form내용을 반환하는 함수
	 * @return
	 */
	public String getForm() {
		VOUtil voUtil = new VOUtil();
		String className = frameUtil.getClsName("form", tblName);
		
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
		sb.append(voUtil.getVariables(pkList));
		nextLine(sb);
		sb.append(voUtil.getEmptyConstructor(className));
		if(MakeGUI.pkMap.get(tblName)) {
			sb.append(voUtil.getFullConstructor(className, pkList));
			nextLine(sb);
			sb.append(voUtil.getGetterSetter(pkList));
		}
		nextLine(sb);
		sb.append(voUtil.getToString());
		sb.append("}");
		
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
