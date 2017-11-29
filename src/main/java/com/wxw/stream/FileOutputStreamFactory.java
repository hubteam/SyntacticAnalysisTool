package com.wxw.stream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 用于生成输出流的工厂类
 * @author 王馨苇
 *
 */
public class FileOutputStreamFactory {
   
	File file;
	
	/**
	 * 构造
	 * @param file 文件
	 * @throws FileNotFoundException
	 */
	public FileOutputStreamFactory(File file) throws FileNotFoundException{
		this.file = file;
	}
	/**
	 * 根据文件创建字节流
	 * @return 
	 */
	public OutputStream createOutputStream() throws IOException {
		
		return new FileOutputStream(file);
	}
}
