package com.vitcom.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.vitcom.frame.MakeGUI;
import com.vitcom.util.FrameUtil;

public class MakeFile {

	public void makeFile(String text, String type) {
		makeFile(text, type, null);
	}
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
