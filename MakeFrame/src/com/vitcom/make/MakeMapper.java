package com.vitcom.make;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.util.MapperUtil;

public class MakeMapper {

	//map의 특성상 순서가 없기 때문에 다른 구성요소와 숫자를 만들기 위해 map을 그대로 사용
	private Map<String, List<Map<String, String>>> tblMap;
	
	public MakeMapper(Map<String, List<Map<String, String>>> tblMap) {
		this.tblMap = tblMap;
	}
	
	public String getMapper() {
		MapperUtil mapperUtil = new MapperUtil();
		StringBuilder sb = new StringBuilder();
		sb.append(mapperUtil.getHeader(tblMap));
		nextLine(sb);
		Iterator<String> iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tblName = iter.next();
			sb.append(mapperUtil.getInsert(tblName));
			sb.append(mapperUtil.getSelect(tblName));
			sb.append(mapperUtil.getSelectOne(tblName));
			sb.append(mapperUtil.getUpdate(tblName));
			sb.append(mapperUtil.getDelete(tblName));
		}
		nextLine(sb);
		sb.append(mapperUtil.getFooter());
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}	
}
