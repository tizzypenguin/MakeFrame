/**
 * Mapper.xml 상세내용을 테이블 데이터에서부터 만드는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.util;

import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;

public class XmlUtil {
	private FrameUtil frameUtil;
	
	public XmlUtil() {
		frameUtil = new FrameUtil();
	}
	
	public String getHeader() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); nextLine(sb);
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" "); nextLine(sb);
		sb.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"); nextLine(sb);
		nextLine(sb);
		sb.append("<mapper namespace=\""+MakeGUI.pack+".dao."+frameUtil.getClsName("xml")+"\">"); nextLine(sb);
		return sb.toString();
	}
	public String getInsert(String tblName, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<insert id=\"insert"+frameUtil.getCamelCase(tblName)+"\" parameterType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+"\">"); nextLine(sb);
		sb.append("\t\tINSERT INTO "+tblName); nextLine(sb);
		sb.append("\t\t\t("); nextLine(sb);
		for(int i = 0; i<dbList.size(); i++) {
			sb.append("\t\t\t\t"+dbList.get(i).get("COLUMN_NAME")+","); nextLine(sb);
		}
		//마지막 콤마 삭제
		sb.deleteCharAt(sb.length()-3);
		sb.append("\t\t\t)"); nextLine(sb);
		sb.append("\t\tVALUES"); nextLine(sb);
		sb.append("\t\t\t("); nextLine(sb);
		for(int i = 0; i<dbList.size(); i++) {
			sb.append("\t\t\t\t#{"+dbList.get(i).get("COLUMN_NAME").toLowerCase()+"},"); nextLine(sb);
		}
		//마지막 콤마 삭제
		sb.deleteCharAt(sb.length()-3);
		sb.append("\t\t\t)"); nextLine(sb);
		sb.append("\t</insert>"); nextLine(sb);
		return sb.toString();
	}
	public String getSelect(String tblName, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t<select id=\"select"+frameUtil.getCamelCase(tblName)+"\" resultType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+"\">"); nextLine(sb);
		sb.append("\t\tSELECT"); nextLine(sb);
		for(int i = 0; i<dbList.size(); i++) {
			sb.append("\t\t\t"+dbList.get(i).get("COLUMN_NAME")+","); nextLine(sb);
		}
		//마지막 콤마 삭제
		sb.deleteCharAt(sb.length()-3);
		sb.append("\t\tFROM"); nextLine(sb);
		sb.append("\t\t\t"+tblName); nextLine(sb);
		sb.append("\t</select>"); nextLine(sb);
		
		return sb.toString();
	}
	public String getSelectOne(String tblName, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> pkList = frameUtil.getPkList(dbList);
		sb.append("\t<select id=\"selectOne"+frameUtil.getCamelCase(tblName)+"\" parameterType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("form", tblName)+"\" resultType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+"\">"); nextLine(sb);
		sb.append("\t\tSELECT"); nextLine(sb);
		for(int i = 0; i<dbList.size(); i++) {
			sb.append("\t\t\t"+dbList.get(i).get("COLUMN_NAME")+","); nextLine(sb);
		}
		//마지막 콤마 삭제
		sb.deleteCharAt(sb.length()-3);
		sb.append("\t\tFROM"); nextLine(sb);
		sb.append("\t\t\t"+tblName); nextLine(sb);
		sb.append("\t\t<where>"); nextLine(sb);
		for(int i=0; i<pkList.size(); i++) {
			sb.append("\t\t\tAND "+pkList.get(i).get("COLUMN_NAME")+" = #{"+pkList.get(i).get("COLUMN_NAME").toLowerCase()+"}"); nextLine(sb);
		}
		sb.append("\t\t</where>"); nextLine(sb);
		sb.append("\t</select>"); nextLine(sb);
		
		return sb.toString();
	}
	public String getUpdate(String tblName, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> pkList = frameUtil.getPkList(dbList);
		sb.append("\t<update id=\"update"+frameUtil.getCamelCase(tblName)+"\" parameterType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("vo", tblName)+"\">"); nextLine(sb);
		sb.append("\t\tUPDATE "+tblName+" SET"); nextLine(sb);
		for(int i=0; i<dbList.size(); i++) {
			sb.append("\t\t\t"+dbList.get(i).get("COLUMN_NAME")+" = #{"+dbList.get(i).get("COLUMN_NAME").toLowerCase()+"},"); nextLine(sb);
		}
		//마지막 콤마 삭제
		sb.deleteCharAt(sb.length()-3);
		sb.append("\t\t<where>"); nextLine(sb);
		for(int i=0; i<pkList.size(); i++) {
			sb.append("\t\t\tAND "+pkList.get(i).get("COLUMN_NAME")+" = #{"+pkList.get(i).get("COLUMN_NAME").toLowerCase()+"}"); nextLine(sb);
		}
		sb.append("\t\t</where>"); nextLine(sb);
		sb.append("\t</update>"); nextLine(sb);
		return sb.toString();
	}
	public String getDelete(String tblName, List<Map<String, String>> dbList) {
		StringBuilder sb = new StringBuilder();
		List<Map<String, String>> pkList = frameUtil.getPkList(dbList);
		sb.append("\t<delete id=\"delete"+frameUtil.getCamelCase(tblName)+"\" parameterType=\""+MakeGUI.pack+".model."+frameUtil.getClsName("form", tblName)+"\">"); nextLine(sb);
		sb.append("\t\tDELETE FROM "+tblName); nextLine(sb);
		sb.append("\t\t<where>"); nextLine(sb);
		for(int i=0; i<pkList.size(); i++) {
			sb.append("\t\t\tAND "+pkList.get(i).get("COLUMN_NAME")+" = #{"+pkList.get(i).get("COLUMN_NAME").toLowerCase()+"}"); nextLine(sb);
		}
		sb.append("\t\t</where>"); nextLine(sb);
		sb.append("\t</delete>"); nextLine(sb);
		return sb.toString();
	}
	public String getFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append("</mapper>");
		return sb.toString();
	}
	
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
