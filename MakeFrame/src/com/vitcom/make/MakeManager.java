/**
 * Manager내용을 만드는 클래스
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.make;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.util.ManagerUtil;

public class MakeManager {
	//map의 특성상 순서가 없기 때문에 다른 구성요소와 숫자를 만들기 위해 map을 그대로 사용
	private Map<String, List<Map<String, String>>> tblMap;
	
	public MakeManager(Map<String, List<Map<String, String>>> tblMap) {
		this.tblMap = tblMap;
	}
	
	/**
	 * Impl내용을 반환하는 함수
	 * @return
	 */
	public String getManager() {
		ManagerUtil managerUtil = new ManagerUtil();
		StringBuilder sb = new StringBuilder();
		sb.append(managerUtil.getHeader(tblMap));
		nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append(managerUtil.getInsert(tblName));
			sb.append(managerUtil.getSelect(tblName));
			sb.append(managerUtil.getSelectOne(tblName));
			sb.append(managerUtil.getUpdate(tblName));
			sb.append(managerUtil.getDelete(tblName));
		}
		nextLine(sb);
		sb.append(managerUtil.getFooter());
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}	
}
