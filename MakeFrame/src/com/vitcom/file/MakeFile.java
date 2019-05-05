/**
 * 해당하는 uri에 대한 Connection객체를 반환하는 클래스 
 * @author		Tizzypenguin
 * @since		2019.05.05
 * @version		1.0
 */
package com.vitcom.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.vitcom.frame.MakeGUI;
import com.vitcom.util.FrameUtil;

public class MakeFile {

	/**
	 * 실제 파일을 만드는 함수(vo, form을 제외하고 사용)
	 * @param text
	 * @param type
	 */
	public void makeFile(String text, String type) {
		makeFile(text, type, null);
	}
	/**
	 * 실제 파일을 만드는 함수(vo, form에서 사용)
	 * @param text	java혹은 xml 파일 내용을 저장한 String
	 * @param type	vo, controller 등
	 * @param tbl	테이블 명(vo, form)
	 */
	public void makeFile(String text, String type, String tbl) {
		FrameUtil frameUtil = new FrameUtil();
		FileOutputStream out = null;
		try {
			if(MakeGUI.path == null || "".equals(MakeGUI.path)) {
				throw new Exception();
			}
			File file = new File(MakeGUI.path+"/"+frameUtil.getFileName(type, tbl));
			if(!file.exists()) {
				new File(file.getAbsolutePath().substring(0,file.getAbsolutePath().lastIndexOf("\\"))).mkdirs();
				file = new File(MakeGUI.path+"/"+frameUtil.getFileName(type, tbl));
			}
			out = new FileOutputStream(file);
			out.write(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
