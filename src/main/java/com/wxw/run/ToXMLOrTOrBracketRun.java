package com.wxw.run;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.wxw.batch.BatchProcessing;

/**
 * 运行类
 * @author 王馨苇
 *
 */
public class ToXMLOrTOrBracketRun {

	private static String flag = "toxml";
	
	public static class Corpus{
		
		public String name;
		public String encoding;
		public String inputFile;
		public String outputFile;
	}
	
	private static String[] corpusName = {"xml","bracket"};
	
	/**
	 * 得到语料信息
	 * @param config
	 * @return
	 */
	private static Corpus[] getCorporaFromConf(Properties config) {
		Corpus[] corpuses = new Corpus[corpusName.length];
		for (int i = 0; i < corpuses.length; i++) {
			String name = corpusName[i];
			String encoding = config.getProperty(name + "." + "corpus.encoding");
			String inputFile = config.getProperty(name + "." + "corpus.inputFile");
			String outputFile = config.getProperty(name + "." + "corpus.outputFile");
			Corpus corpus = new Corpus();
			corpus.name = name;
			corpus.encoding = encoding;
			corpus.inputFile = inputFile;
			corpus.outputFile = outputFile;
			corpuses[i] = corpus;			
		}
		return corpuses;
	}
	
	/**
	 * 根据语料名称获取某个语料
	 * @param corpora 语料内部类数组，包含了所有语料的信息
	 * @param corpusName 语料的名称
	 * @return
	 */
	private static Corpus getCorpus(Corpus[] corpora, String corpusName) {
		for (Corpus c : corpora) {
            if (c.name.equalsIgnoreCase(corpusName)) {
                return c;
            }
        }
        return null;
	}
	
	/**
	 * 根据配置文件获取特征处理类
	 * @param corpusName 语料名称
	 * @throws IOException
	 * @throws UnsupportedOperationException 
	 */
	private static void runBefore(String corpusName) throws IOException, UnsupportedOperationException{
		//加载语料文件
        Properties config = new Properties();
        InputStream configStream = ToXMLOrTOrBracketRun.class.getClassLoader().getResourceAsStream("com/wxw/run/corpus.properties");
        config.load(configStream);
        Corpus[] corpora = getCorporaFromConf(config);//获取语料

        Corpus corpus = getCorpus(corpora,corpusName);
        runOnCorporaByFlag(corpus);
	}

	/**
	 * 根据特征处理类和命令行输入参数，进行相应的操作
	 * @param corpora 内部类对象，语料信息
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedOperationException 
	 */
	private static void runOnCorporaByFlag(Corpus corpora) throws UnsupportedOperationException, FileNotFoundException, IOException {
		if(flag == "toxml" || flag.equals("toxml")){		
			toXML(corpora);		
		}else if(flag == "tobra" || flag.equals("tobra")){
			toBracket(corpora);
		}
	}
	
	public static void main(String[] args) throws UnsupportedOperationException, IOException, CloneNotSupportedException {
		String cmd = args[0];
		if(cmd.equals("-toxml")){
			flag = "toxml";
			String corpus = args[1];
			runBefore(corpus);
		}else if(cmd.equals("-tobra")){
			flag = "tobra";
			String corpus = args[1];
			runBefore(corpus);
		}
	}

	/**
	 * 将XML格式的语料转成括号表达式的形式
	 * @param corpus 语料信息
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedOperationException 
	 */
	private static void toBracket(Corpus corpus) throws UnsupportedOperationException, FileNotFoundException, IOException {
		BatchProcessing.toBracket(corpus.inputFile, corpus.outputFile, corpus.encoding);
	}

	/**
	 * 将括号表达式的语料转成XML格式
	 * @param corpus 语料信息
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedOperationException 
	 */
	private static void toXML(Corpus corpus) throws UnsupportedOperationException, FileNotFoundException, IOException {
		BatchProcessing.toXML(corpus.inputFile, corpus.outputFile, corpus.encoding);
	}
}
