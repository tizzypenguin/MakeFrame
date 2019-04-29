package com.vitcom.goout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GoOut {
	
	public void goOut(String vo,String className) {
		String path  = getClass().getClassLoader().getResource("").getPath().substring(1);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(path+"/"+className+".java"));
			out.write(vo.getBytes());
		} catch (IOException e) {
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
