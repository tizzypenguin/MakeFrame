package com.vitcom.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.vitcom.connector.Connector;
import com.vitcom.create.GetVO;
import com.vitcom.frame.MakeGUI;
import com.vitcom.goout.GoOut;

public class Main {
	public static void main(String[] args) {
		
//		GoOut go = new GoOut();
//		Scanner scan = new Scanner(System.in);
//		System.out.println("테이블 명을 입력해주세요");
//		String tableName = scan.nextLine();
//		Connector connector = new Connector();
//		Connection con = null;
//		try {
//			con = connector.getConnection("FINSET1", "FINSET1");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		GetVO getVO = new GetVO();
//		String vo = getVO.getVO(con, tableName);
//		String className = getVO.getClassName(tableName);
//		go.goOut(vo,className);
//		scan.close();
		new MakeGUI();
	}
}
