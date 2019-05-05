/**
 * 테이블 조회 및 각 자바 파일 생성
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vitcom.connector.Connector;
import com.vitcom.file.MakeFile;
import com.vitcom.frame.MakeGUI;
import com.vitcom.make.MakeController;
import com.vitcom.make.MakeForm;
import com.vitcom.make.MakeImpl;
import com.vitcom.make.MakeManager;
import com.vitcom.make.MakeMapper;
import com.vitcom.make.MakeVO;
import com.vitcom.make.MakeXml;

public class Create {
	public Create() {
		//실행 쿼리
		String qry = 
			"SELECT "+
				"UTC.COLUMN_NAME AS COLUMN_NAME, "+
				"UTC.DATA_TYPE AS DATA_TYPE, "+
				"DECODE "+
					"( "+
						"REGEXP_SUBSTR(UTC.DATA_TYPE,'[^(]+'), "+
						"'DATE', 'Date', "+
						"'CHAR', 'String', "+
						"'VARCHAR2', 'String', "+
						"'NUMBER', 'int', "+
						"'TIMESTAMP', 'Timestamp' "+
					") AS DATA_CLASS, "+
				"UCCO.COMMENTS AS COMMENTS, "+
				"PK.YN_PK AS YN_PK "+
			"FROM "+
				"USER_TAB_COLUMNS UTC "+
			"LEFT JOIN "+
				"USER_COL_COMMENTS UCCO "+
			"ON "+
				"UTC.TABLE_NAME = UCCO.TABLE_NAME "+
				"AND UTC.COLUMN_NAME = UCCO.COLUMN_NAME "+
			"LEFT JOIN "+
				"( "+
					"SELECT "+
						"UC.TABLE_NAME, "+
						"UCC.COLUMN_NAME, "+
						"'Y' AS YN_PK "+
					"FROM "+
						"USER_CONSTRAINTS UC "+
					"INNER JOIN "+
						"USER_CONS_COLUMNS UCC "+
					"ON "+
						"UC.CONSTRAINT_NAME = UCC.CONSTRAINT_NAME "+
					"WHERE "+
							"UC.TABLE_NAME = ? "+
						"AND UC.CONSTRAINT_TYPE = 'P' "+
				") PK "+
			"ON		UTC.TABLE_NAME = PK.TABLE_NAME "+
				"AND UTC.COLUMN_NAME = PK.COLUMN_NAME "+
			"WHERE UTC.TABLE_NAME = ? ";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> dbList = null;
		Map<String, String> dbMap = null;
		
		String[] tblArr = MakeGUI.table;
		Map<String, List<Map<String, String>>> tblMap = new HashMap<>();
		
		Connector connector = new Connector(MakeGUI.uri.split("/")[0]);
		
		//각 테이블별 정보 조회 - tblMap에 저장
		for(String tbl: tblArr) {
			dbList = new ArrayList<>();
			try {
				con = connector.getConnection(MakeGUI.uri.split("/")[1], MakeGUI.uri.split("/")[2]);
				ps = con.prepareStatement(qry);
				//? 채워넣기
				ps.setString(1, tbl);
				ps.setString(2, tbl);
				
				//조회 실행
				rs = ps.executeQuery();
				
				//조회 값 저장
				while(rs.next()) {
					//map에 우선 저장
					dbMap = new HashMap<>();
					dbMap.put("COLUMN_NAME", rs.getString(1));
					dbMap.put("DATA_TYPE", rs.getString(2));
					dbMap.put("DATA_CLASS", rs.getString(3));
					dbMap.put("COMMENTS", rs.getString(4));
					dbMap.put("YN_PK", rs.getString(5));
					//list에 map 추가
					dbList.add(dbMap);
				}
				//전체 map에 list 저장
				tblMap.put(tbl, dbList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Iterator<String> iter = tblMap.keySet().iterator();
		//pkMap 확인
		while(iter.hasNext()) {
			String tbl = iter.next();
			boolean isPk = false;
			List<Map<String, String>> dbList2 = tblMap.get(tbl);
			for(Map<String, String> map: dbList2) {
				if("Y".equals(map.get("YN_PK"))) {
					isPk = true;
				}
			}
			MakeGUI.pkMap.put(tbl, isPk);
		}
		
		MakeFile makeFile = new MakeFile();
		
		iter = tblMap.keySet().iterator();
		while(iter.hasNext()) {
			String tbl = iter.next();
			List<Map<String, String>> dbList3 = tblMap.get(tbl);
			MakeVO makeVO = new MakeVO(tbl, dbList3);
			makeFile.makeFile(makeVO.getVO(), "vo", tbl);
			MakeForm makeForm = new MakeForm(tbl, dbList3);
			makeFile.makeFile(makeForm.getForm(), "form", tbl);
			
		}
		MakeXml makeXml = new MakeXml(tblMap);
		makeFile.makeFile(makeXml.getXml(), "xml");
		MakeMapper makeMapper = new MakeMapper(tblMap);
		makeFile.makeFile(makeMapper.getMapper(), "mapper");
		MakeManager makeManager = new MakeManager(tblMap);
		makeFile.makeFile(makeManager.getManager(), "manager");
		MakeImpl makeImpl = new MakeImpl(tblMap);
		makeFile.makeFile(makeImpl.getImpl(), "impl");
		MakeController makeController = new MakeController(tblMap);
		makeFile.makeFile(makeController.getController(), "controller");
	}
}
