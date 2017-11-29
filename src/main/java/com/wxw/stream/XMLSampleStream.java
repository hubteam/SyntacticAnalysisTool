package com.wxw.stream;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wxw.tobracket.XMLToBracket;

/**
 * 样本流处理类
 * @author 王馨苇
 *
 */
public class XMLSampleStream extends SampleStream{

	private Logger logger = Logger.getLogger(XMLSampleStream.class.getName());
	public XMLSampleStream(PlainTextByTreeStream samples){
		super(samples);
	}
	
	/**
	 * 读取一段XML格式文本，并处理
	 * @return
	 * @throws IOException 
	 */
	@Override
	public String read() throws IOException {
		String sentence = samples.read();
//		System.out.println("212"+sentence);
		String bracket = null;
		if(sentence != null){
			if(sentence.compareTo("") != 0){
				try{
					XMLToBracket btx = new XMLToBracket();
					bracket = btx.bracketFormat(sentence)+"\n";
				}catch(Exception e){
					if (logger.isLoggable(Level.WARNING)) {						
	                    logger.warning("Error during parsing, ignoring sentence: " + sentence);
	                }	
				}
				return bracket;
			}else {
				return bracket;
			}
		}
		else{
			return null;
		}
	}
}
