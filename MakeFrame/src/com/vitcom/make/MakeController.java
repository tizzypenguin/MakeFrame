package com.vitcom.make;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.util.ControllerUtil;

public class MakeController {
	//map의 특성상 순서가 없기 때문에 다른 구성요소와 숫자를 만들기 위해 map을 그대로 사용
	private Map<String, List<Map<String, String>>> tblMap;
	
	public MakeController(Map<String, List<Map<String, String>>> tblMap) {
		this.tblMap = tblMap;
	}
	
	public String getController() {
		ControllerUtil controllerUtil = new ControllerUtil();
		StringBuilder sb = new StringBuilder();
		sb.append(controllerUtil.getHeader(tblMap));
		nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append(controllerUtil.getInsert(tblName));
			sb.append(controllerUtil.getSelect(tblName));
			sb.append(controllerUtil.getSelectOne(tblName));
			sb.append(controllerUtil.getUpdate(tblName));
			sb.append(controllerUtil.getDelete(tblName));
		}
		nextLine(sb);
		sb.append(controllerUtil.getFooter());
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}	
}
