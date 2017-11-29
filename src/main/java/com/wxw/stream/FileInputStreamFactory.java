package com.wxw.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 用于生成流的工厂类
 * @author 王馨苇
 *
 */
public class FileInputStreamFactory{

	File file;
	
	/**
	 * 判断文件是否存在
	 * @param file 文件
	 * @throws FileNotFoundException
	 */
	public FileInputStreamFactory(File file) throws FileNotFoundException{
		if(!file.exists()){
			throw new FileNotFoundException("File '" + file + "' cannot be found");
		}
		this.file = file;
	}
	
	/**
	 * 根据文件创建字节流
	 * @return 
	 */
	public InputStream createInputStream() throws IOException {
		
		return new FileInputStream(file);
	}
}
