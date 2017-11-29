package com.wxw.tobracket;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wxw.tree.GenerateTree;
import com.wxw.tree.TreeNode;
/**
 * 将xml形式转成括号表达式形式
 * @author 王馨苇
 *
 */
public class XMLToBracket {

	private GenerateTree tree = new GenerateTree();
	/**
	 * 将xml格式的表达式转成括号表达式的形式
	 * @param xml形式的字符串
	 * @return
	 */
	public String bracketFormat(String xml){
		List<String> xmlList = xmlStrToxmlList(xml);
		List<String> xmlBracket = xmlListToBracketList(xmlList);
		TreeNode node = tree.generateTree(xmlBracket);
		//返回一棵树的括号表达式的样子		
		return TreeNode.printTree(node, 1);
	}
	
	/**
	 * 将写为一行的xml样式的字符串转换成列表
	 * @param xmlLine 一行表示的xml字符串
	 * @return
	 */
	public List<String> xmlStrToxmlList(String xmlLine){		
		List<String> xmlList = new ArrayList<String>();
		for (int i = 0; i < xmlLine.length(); i++) {
			String tempnode = "";
			if(xmlLine.charAt(i) == '<'){
				for (int j = i; j < xmlLine.length(); j++) {
					tempnode += xmlLine.charAt(j)+"";
					if(xmlLine.charAt(j) == '>'){
						i = j;
						break;
					}
				}
				xmlList.add(tempnode);
			}else{
				for (int j = i; j < xmlLine.length(); j++) {
					if(xmlLine.charAt(j) == '<'){
						i = j-1;
						break;
					}else{
						tempnode += xmlLine.charAt(j)+"";
					}
				}
				xmlList.add(tempnode);
			}
		}		
		return xmlList;
	}
	
	/**
	 * 将XML格式的列表转换成括号的列表
	 * @param xmlList XML格式的列表表达
	 * @return
	 */
	public List<String> xmlListToBracketList(List<String> xmlList){
		List<String> bracketList = new ArrayList<String>();
		for (int i = 0; i < xmlList.size(); i++) {
			if(xmlList.get(i).charAt(0) == '<'){
				if(xmlList.get(i).charAt(1) == '/'){
					bracketList.add(")");
				}else{
					bracketList.add("(");
					bracketList.add(xmlList.get(i).substring(1, xmlList.get(i).length()-1));	
				}				
			}else{
				bracketList.add(xmlList.get(i));
			}
		}
		return bracketList;
	}
	
	@Test
	public void test(){
		String xml = "<ROOT><IP><NP><NR>徐国义</NR></NP><VP><ADVP><AD>已然</AD></ADVP><VP><VPT><VV>记</VV><AD>不</AD><VA>清</VA></VPT><IP><QP><CD>多少</CD><CLP><M>次</M></CLP></QP><VP><VV>目送</VV><NP><NN>弟子</NN></NP><IP><VP><VV>摘金</VV></VP></IP></VP></IP></VP></VP><PU>。</PU></IP></ROOT>";
		List<String> xmlStrToxmlList = xmlStrToxmlList(xml);
		for (int i = 0; i < xmlStrToxmlList.size(); i++) {
			System.out.println(xmlStrToxmlList.get(i));
		}
		List<String> xmlListToBracketList = xmlListToBracketList(xmlStrToxmlList);
		for (int i = 0; i < xmlListToBracketList.size(); i++) {
			System.out.print(xmlListToBracketList.get(i));
		}
		System.out.println();
		System.out.println(bracketFormat(xml));
	}
}

