package com.wxw.toxml;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将括号表达式转成XML格式
 * @author 王馨苇
 *
 */
public class BracketToXML {

	/**
	 * 将括号表达式转成XML格式
	 * @param treeStr 将括号表达式
	 * @return
	 */
	public String bracketToXML(String treeStr){
		String xml = "";
		List<String> parts = stringToList(treeStr);
        Stack<String> str = new Stack<String>();
        for (int i = 0; i < parts.size(); i++) {
			if(!parts.get(i).equals(")") && !parts.get(i).equals(" ")){
				str.push(parts.get(i));
			}else if(parts.get(i).equals(" ")){
				
			}else if(parts.get(i).equals(")")){
				Stack<String> strtmp = new Stack<String>();
				while(!str.peek().equals("(")){
					if(!str.peek().equals(" ")){
						strtmp.push(str.pop());
					}
				}
				str.pop();
				String xmltmp = "";
				String node = strtmp.pop();
				while(!strtmp.isEmpty()){
					
					xmltmp = xmltmp + strtmp.pop();
				}
				if(xmltmp.replaceAll(" ","").startsWith("<")){
					String[] splitByLine = xmltmp.split("\n");
					String allline = "";
					for (int j = 0; j < splitByLine.length; j++) {
						allline += "    "+splitByLine[j]+"\n";
					}
					xml = "<"+node+">"+"\n"+allline+"</"+node+">"+"\n";
				}else{
					xml = "<"+node+">"+xmltmp+"</"+node+">"+"\n";
				}
				str.push(xml);
			}
		}
        
        String completexml = str.pop();
		return completexml;
	}
	
	/**
	 * 将字符串转成列表
	 * @param treeStr 字符串
	 * @return
	 */
	public List<String> stringToList(String treeStr){
		List<String> parts = new ArrayList<String>();
        for (int index = 0; index < treeStr.length(); ++index) {
            if (treeStr.charAt(index) == '(' || treeStr.charAt(index) == ')' || treeStr.charAt(index) == ' ') {
                parts.add(Character.toString(treeStr.charAt(index)));
            } else {
                for (int i = index + 1; i < treeStr.length(); ++i) {
                    if (treeStr.charAt(i) == '(' || treeStr.charAt(i) == ')' || treeStr.charAt(i) == ' ') {
                        parts.add(treeStr.substring(index, i));
                        index = i - 1;
                        break;
                    }
                }
            }
        }
        return parts;
	}
}

