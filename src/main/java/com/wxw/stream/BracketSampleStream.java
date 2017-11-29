package com.wxw.stream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wxw.tobracket.XMLToBracket;
import com.wxw.toxml.BracketToXML;

/**
 * 样本流处理类
 * @author 王馨苇
 *
 */
public class BracketSampleStream extends SampleStream{

	private Logger logger = Logger.getLogger(XMLSampleStream.class.getName());
	
	public BracketSampleStream(PlainTextByTreeStream samples){
		super(samples);
	}

	/**
	 * 读取一段括号表达式文本，并处理
	 * @return
	 * @throws IOException 
	 */
	@Override
	public String read() throws IOException {
		String sentence = samples.read();
//		System.out.println("123"+sentence);
		String xml = null;
		if(sentence != null){
			if(sentence.compareTo("") != 0){
				try{
					BracketToXML btx = new BracketToXML();
					xml = btx.bracketToXML(sentence);
//					XMLToBracket xtb = new XMLToBracket();
//					xml = xtb.bracketFormat(sentence);
				}catch(Exception e){
					if (logger.isLoggable(Level.WARNING)) {						
	                    logger.warning("Error during parsing, ignoring sentence: " + sentence);
	                }	
				}
				return xml;
			}else {
				return xml;
			}
		}
		else{
			return null;
		}
	}
}
