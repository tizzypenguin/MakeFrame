package com.vitcom.make;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.util.ImplUtil;

public class MakeImpl {
	//map의 특성상 순서가 없기 때문에 다른 구성요소와 숫자를 만들기 위해 map을 그대로 사용
	private Map<String, List<Map<String, String>>> tblMap;
	
	public MakeImpl(Map<String, List<Map<String, String>>> tblMap) {
		this.tblMap = tblMap;
	}
	
	public String getImpl() {
		ImplUtil implUtil = new ImplUtil();
		StringBuilder sb = new StringBuilder();
		sb.append(implUtil.getHeader(tblMap));
		nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append(implUtil.getInsert(tblName));
			sb.append(implUtil.getSelect(tblName));
			sb.append(implUtil.getSelectOne(tblName));
			sb.append(implUtil.getUpdate(tblName));
			sb.append(implUtil.getDelete(tblName));
		}
		nextLine(sb);
		sb.append(implUtil.getFooter());
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}	
}
