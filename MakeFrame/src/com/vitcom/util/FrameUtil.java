/**
 * 공통으로 쓰이는 클래스  
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vitcom.frame.MakeGUI;

public class FrameUtil {
	
	public String capitalize(String str) {
		String capitalized = str.toUpperCase().charAt(0)+str.substring(1,str.length());
		return capitalized;
	}
	public String getCamelCase(String name) {
		name = name.toLowerCase();
		String returnStr = "";
		String [] strArr = name.split("_");
		for(String str:strArr) {
			returnStr+=capitalize(str);
		}
		return returnStr;
	}
	public String getClsName(String type) {
		return getClsName(type, null);
	}
	
	public String getClsName(String type, String tbl) {
		String returnStr = "";
		switch (type) {
			case "vo":
				returnStr = getCamelCase(tbl) + "VO";
				break;
			case "form":
				returnStr = getCamelCase(tbl) + "Form";
				break;
			//형식 유지를 위해 테이블도 추가
			case "table":
				returnStr = tbl;
				break;
			case "controller":
				returnStr = getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Controller";
				break;
			case "manager":
				returnStr = getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Manager";
				break;
			case "impl":
				returnStr = getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "ManagerImpl";
				break;
			case "mapper":
				returnStr = getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Mapper";
				break;
			case "xml":
				returnStr = getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Mapper";
				break;
			default:
				break;
		}
		return returnStr;
	}
	public String getFileName(String type, String tbl) {
		String returnStr = "";
		String [] packArr = MakeGUI.pack.split("\\."); 
		String prefix = packArr[0]+"/"+packArr[1]+"/"+packArr[2]+"/";
		switch (type) {
			case "vo":
				returnStr = prefix+"model/"+getCamelCase(tbl) + "VO.java";
				break;
			case "form":
				returnStr = prefix+"model/"+getCamelCase(tbl) + "Form.java";
				break;
			case "controller":
				returnStr = prefix+getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Controller.java";
				break;
			case "manager":
				returnStr = prefix+"service/"+getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Manager.java";
				break;
			case "impl":
				returnStr = prefix+"service/impl/"+getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "ManagerImpl.java";
				break;
			case "mapper":
				returnStr = prefix+"dao/"+getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Mapper.java";
				break;
			case "xml":
				returnStr = prefix+"dao/"+getCamelCase(MakeGUI.pack.split("\\.")[MakeGUI.pack.split("\\.").length-1]) + "Mapper.xml";
				break;
			default:
				break;
		}
		return returnStr;
	}
	public String getClsNameVar(String type) {
		return getClsNameVar(type, null);
	}
	public String getClsNameVar(String type, String tbl) {
		String returnStr = getClsName(type, tbl);
		returnStr = returnStr.toLowerCase().charAt(0)+returnStr.substring(1,returnStr.length());
		return returnStr;
	}
	
	public List<Map<String, String>> getPkList(List<Map<String, String>> dbList) {
		List<Map<String, String>> pkList = new ArrayList<>();
		for(Map<String, String> dbMap : dbList) {
			if("Y".equals(dbMap.get("YN_PK"))) {
				pkList.add(dbMap);
			}
		}
		return pkList;
	}
}
