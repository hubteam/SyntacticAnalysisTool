package com.wxw.stream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class PlainTextByTreeWriter {
	private final String encoding;
	private FileOutputStreamFactory outputStreamFactory;
	private BufferedWriter out;
	
	/**
	 * 构造
	 * @param inputStreamFactory 输入文件样本流
	 * @param charsetName 编码
	 * @throws UnsupportedOperationException 异常
	 * @throws IOException 异常
	 */
	public PlainTextByTreeWriter(FileOutputStreamFactory outputStreamFactory, String charsetName) throws UnsupportedOperationException, IOException{
		this(outputStreamFactory,Charset.forName(charsetName));
	}
	
	/**
	 * 构造
	 * @param inputStreamFactory 输入文件样本流
	 * @param charsetName 编码
	 * @throws UnsupportedOperationException 异常
	 * @throws IOException 异常
	 */
	public PlainTextByTreeWriter(FileOutputStreamFactory outputStreamFactory, Charset charsetName) throws UnsupportedOperationException, IOException{
		this.encoding = charsetName.name();
		this.outputStreamFactory  = outputStreamFactory;
		this.out = new BufferedWriter(
				new OutputStreamWriter(this.outputStreamFactory.createOutputStream(), this.encoding));
	}
	/**
	 * 关闭文件
	 */
	public void close() throws IOException {
		if (this.out != null) {
			this.out.close();
		} 
	}

	/**
	 * 读取训练语料若干行括号表达式表示树，拼接在一行形成括号表达式
	 * @return 拼接后的结果
	 */
	public void writeTree(String tree) throws IOException {
		this.out.write(tree);
		this.out.newLine();
//		this.out.flush();
	}
	
	public void flush() throws IOException{
		this.out.flush();
	}
}