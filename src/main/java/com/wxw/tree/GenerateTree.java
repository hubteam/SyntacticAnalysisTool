package com.wxw.tree;

import java.util.List;
import java.util.Stack;

/**
 * 根据括号表达式生成树
 * @author 王馨苇
 *
 */
public class GenerateTree {

	/**
	 * 括号表达式生成树
	 * @param treeStr 括号表达式
	 * @return
	 */
	public TreeNode generateTree(List<String> parts){
        Stack<TreeNode> tree = new Stack<TreeNode>();
        for (int i = 0; i < parts.size(); i++) {
			if(!parts.get(i).equals(")") && !parts.get(i).equals(" ")){
				tree.push(new TreeNode(parts.get(i)));
			}else if(parts.get(i).equals(" ")){
				
			}else if(parts.get(i).equals(")")){
				Stack<TreeNode> temp = new Stack<TreeNode>();
				while(!tree.peek().getNodeName().equals("(")){
					if(!tree.peek().getNodeName().equals(" ")){
						temp.push(tree.pop());
					}
				}
				tree.pop();
				TreeNode node = temp.pop();
				while(!temp.isEmpty()){		
					node.addChild(temp.pop());
				}
				tree.push(node);
			}
		}
        TreeNode treeStruct = tree.pop();
        return treeStruct;
	}
}
