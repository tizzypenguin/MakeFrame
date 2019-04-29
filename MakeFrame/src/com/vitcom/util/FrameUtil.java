package com.vitcom.util;

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
	
	public String getClsName(String type, String tbl) {
		String returnStr = "";
		switch (type) {
			case "vo":
				returnStr += getCamelCase(tbl) + "VO";
				break;
			case "form":
				returnStr += getCamelCase(tbl) + "Form";
				break;
			//형식 유지를 위해 테이블도 추가
			case "table":
				returnStr += tbl;
				break;
			case "controller":
				returnStr += getCamelCase(MakeGUI.pack.split(".")[MakeGUI.pack.split(".").length-1]) + "Controller";
				break;
			case "manager":
				returnStr += getCamelCase(MakeGUI.pack.split(".")[MakeGUI.pack.split(".").length-1]) + "Manager";
				break;
			case "impl":
				returnStr += getCamelCase(MakeGUI.pack.split(".")[MakeGUI.pack.split(".").length-1]) + "ManagerImpl";
				break;
			case "dao":
				returnStr += getCamelCase(MakeGUI.pack.split(".")[MakeGUI.pack.split(".").length-1]) + "Mapper";
				break;
			case "xml":
				returnStr += getCamelCase(MakeGUI.pack.split(".")[MakeGUI.pack.split(".").length-1]) + "Mapper";
				break;
			default:
				break;
		}
		return returnStr;
	}
}
