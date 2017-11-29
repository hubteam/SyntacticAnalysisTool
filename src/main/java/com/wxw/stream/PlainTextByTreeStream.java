package com.wxw.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 读取文件
 * @author 王馨苇
 *
 */
public class PlainTextByTreeStream{

	private final FileChannel channel;//缓冲道
	private final String encoding;
	private FileInputStreamFactory inputStreamFactory;
	private BufferedReader in;
	
	/**
	 * 构造
	 * @param inputStreamFactory 输入文件样本流
	 * @param charsetName 编码
	 * @throws UnsupportedOperationException 异常
	 * @throws IOException 异常
	 */
	public PlainTextByTreeStream(FileInputStreamFactory inputStreamFactory, String charsetName) throws UnsupportedOperationException, IOException{
		this(inputStreamFactory,Charset.forName(charsetName));
	}
	
	/**
	 * 构造
	 * @param inputStreamFactory 输入文件样本流
	 * @param charsetName 编码
	 * @throws UnsupportedOperationException 异常
	 * @throws IOException 异常
	 */
	public PlainTextByTreeStream(FileInputStreamFactory inputStreamFactory, Charset charsetName) throws UnsupportedOperationException, IOException{
		this.encoding = charsetName.name();
		this.inputStreamFactory  = inputStreamFactory;
		this.channel = null;
		this.reset();
	}
	/**
	 * 关闭文件
	 */
	public void close() throws IOException {
		if (this.in != null && this.channel == null) {
			this.in.close();
		} else if (this.channel != null) {
			this.channel.close();
		}
	}

	/**
	 * 读取训练语料若干行括号表达式表示树，拼接在一行形成括号表达式
	 * @return 拼接后的结果
	 */
	public String read() throws IOException {
		// 一次读取n行,jie
		String line = null;
		String readContent = "";
		while((line = this.in.readLine()) != null){
			if(line != "" && !line.equals("")){
				line = line.replaceAll("\n","");
				readContent += line.trim();
			}else{
				break;
			}
		}
		return readContent;
	}

	
	
	/**
	 * 重置读取的位置
	 */
	public void reset() throws IOException, UnsupportedOperationException {
		
		if (this.inputStreamFactory != null) {
			this.in = new BufferedReader(
					new InputStreamReader(this.inputStreamFactory.createInputStream(), this.encoding));
		}else if (this.channel == null) {
			this.in.reset();
		} else {
			this.channel.position(0L);
			this.in = new BufferedReader(Channels.newReader(this.channel, this.encoding));
		}
	}
}