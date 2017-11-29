package com.wxw.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;

import com.wxw.run.ToXMLOrTOrBracketRun;
import com.wxw.stream.BracketSampleStream;
import com.wxw.stream.FileInputStreamFactory;
import com.wxw.stream.PlainTextByTreeStream;
import com.wxw.stream.SampleStream;
import com.wxw.tobracket.XMLToBracket;
import com.wxw.toxml.BracketToXML;

import junit.framework.TestCase;

/**
 * 测试括号表达式到XML，在从XML到括号表达式的转换是否正确
 * @author 王馨苇
 *
 */
public class Bracket2XML2Bracket extends TestCase{

	private PlainTextByTreeStream toxmlStream;
	private SampleStream toxmlsampleStream;
	private BufferedReader in;
	
	@Before
	public void setUp() throws UnsupportedOperationException, FileNotFoundException, IOException{
        URL url = ToXMLOrTOrBracketRun.class.getClassLoader().getResource("com/wxw/testcorpus/inputFortoxml.txt");
		toxmlStream = new PlainTextByTreeStream(new FileInputStreamFactory(new File(url.getFile())), "utf8");
		toxmlsampleStream = new BracketSampleStream(toxmlStream);
		this.in = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(url.getFile())), "utf8"));
	}
	
	public void test() throws IOException{
		
		// 一次读取n行,jie
		String line = null;
		String readContent = "";
		while((line = this.in.readLine()) != null){
			if(line != "" && !line.equals("")){
				readContent += line+"\n";
			}else{
				break;
			}
		}
		System.out.println(readContent);
		String xml = toxmlsampleStream.read();
		//括号到xml
//		BracketToXML btx = new BracketToXML();
//		String xml = btx.bracketToXML(bracketBef);
		System.out.println(xml);
		XMLToBracket xtb = new XMLToBracket();
		xml = xml.replaceAll("\\s+", "");
		String bracketAfter = xtb.bracketFormat(xml);
		System.out.println(bracketAfter);
		assertEquals(readContent, bracketAfter);
	}
	
	@After
	public void tearDown() throws IOException{
		toxmlStream.close();
		toxmlsampleStream.close();
	}
}
