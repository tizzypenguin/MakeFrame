/**
 * Mapper.xml내용을 만드는 클래스
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.make;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.util.XmlUtil;

public class MakeXml {

	private Map<String, List<Map<String, String>>> tblMap;
	
	public MakeXml(Map<String, List<Map<String, String>>> tblMap) {
		this.tblMap = tblMap; 
	}
	
	/**
	 * Mapper.xml내용을 반환하는 함수
	 * @return
	 */
	public String getXml() {
		XmlUtil xmlUtil = new XmlUtil();
		StringBuilder sb = new StringBuilder();
		sb.append(xmlUtil.getHeader());
		Iterator<String> iter = tblMap.keySet().iterator();
		
		while(iter.hasNext()) {
			String tblName = iter.next();
			List<Map<String, String>> dbList = tblMap.get(tblName);
			sb.append(xmlUtil.getInsert(tblName, dbList));
			sb.append(xmlUtil.getSelect(tblName, dbList));
			sb.append(xmlUtil.getSelectOne(tblName, dbList));
			sb.append(xmlUtil.getUpdate(tblName, dbList));
			sb.append(xmlUtil.getDelete(tblName, dbList));
		}
		sb.append(xmlUtil.getFooter()); nextLine(sb);
		return sb.toString();
	}
	private void nextLine(StringBuilder sb) {
		sb.append("\r\n");
	}
}
