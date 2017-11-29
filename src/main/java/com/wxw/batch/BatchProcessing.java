package com.wxw.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.wxw.stream.BracketSampleStream;
import com.wxw.stream.FileInputStreamFactory;
import com.wxw.stream.FileOutputStreamFactory;
import com.wxw.stream.PlainTextByTreeStream;
import com.wxw.stream.PlainTextByTreeWriter;
import com.wxw.stream.SampleStream;
import com.wxw.stream.XMLSampleStream;

/**
 * 批处理进行XML格式和括号表达式之间的相互转换
 * @author 王馨苇
 *
 */
public class BatchProcessing {

	/**
	 * 批处理将括号表达式文本转成XML格式
	 * @param inputpath 输入路径
	 * @param outputpath 输出路径
	 * @param charSet 编码格式
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedOperationException 
	 */
	public static void toXML(String inputpath, String outputpath, String encoding) throws UnsupportedOperationException, FileNotFoundException, IOException{
		PlainTextByTreeStream lineStream = new PlainTextByTreeStream(new FileInputStreamFactory(new File(inputpath)), encoding);
		SampleStream sampleStream = new BracketSampleStream(lineStream);
		PlainTextByTreeWriter outputStream = new PlainTextByTreeWriter(new FileOutputStreamFactory(new File(outputpath)), encoding);
		BatchProcessing.batchProcess(sampleStream,outputStream);
	}
	
	/**
	 * 批处理实现XML格式和
	 * @param lineStream 输入流
	 * @param outputpath 文件输出路径
	 * @throws IOException 
	 */
	private static void batchProcess(SampleStream lineStream, PlainTextByTreeWriter output) throws IOException {
		String content = "";
		while((content = lineStream.read()) != null){
			System.out.println(content);
			output.writeTree(content);
		}
		output.flush();
		output.close();
		lineStream.close();
	}

	/**
	 * 批处理将XML格式文本转成括号表达式
	 * @param inputpath 输入路径
	 * @param outputpath 输出路径
	 * @param charSet 编码格式
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedOperationException 
	 */
	public static void toBracket(String inputpath, String outputpath, String encoding) throws UnsupportedOperationException, FileNotFoundException, IOException{
		PlainTextByTreeStream inputStream = new PlainTextByTreeStream(new FileInputStreamFactory(new File(inputpath)), encoding);
		SampleStream sampleStream = new XMLSampleStream(inputStream);
		PlainTextByTreeWriter outputStream = new PlainTextByTreeWriter(new FileOutputStreamFactory(new File(outputpath)), encoding);
		BatchProcessing.batchProcess(sampleStream,outputStream);
	}
}
