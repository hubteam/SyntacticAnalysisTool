package com.wxw.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树中的节点类
 * @author  王馨苇
 *
 */
public class TreeNode {	

	private String nodename;

	private List<TreeNode> children = new ArrayList<TreeNode>();
		
	public TreeNode(){
			
	}
	
	public TreeNode(String nodename){
		this.nodename = nodename;
	}
	/**
	 * 添加孩子节点
	 * @param children 孩子节点的名称
	 */
	public void addChild(String children){
		this.children.add(new TreeNode(children));
	}
	
	/**
	 * 添加孩子节点
	 * @param children 孩子节点
	 */
	public void addChild(TreeNode children){
		this.children.add(children);
	}
	
	/**
	 * 添加若干孩子节点
	 * @param children 孩子节点数组
	 */
	public void addChild(TreeNode[] children){
		for (TreeNode treeNode : children) {
			this.addChild(treeNode);
		}
	}
	
	/**
	 * 添加若干孩子节点
	 * @param children 孩子节点名称组成的数组
	 */
	public void addChild(String[] children){
		for (String treeNode : children) {
			this.addChild(treeNode);
		}
	}
		
	/**
	 * 判断是否是叶子节点
	 * @return
	 */
	public boolean isLeaf(){
		return this.children.size() == 0;
	}
		
	/**
	 * 统计当前父节点下，孩子节点的个数
	 * @return
	 */
	public int numChildren(){
		return this.children.size();
	}
	
	/**
	 * 返回节点名称
	 * @return
	 */
	public String getNodeName(){
		return this.nodename;
	}

	/**
	 * 返回孩子节点列表
	 * @return
	 */
	public List<TreeNode> getChildren(){
		return this.children;
	}

	/**
	 * 重写toString方法，输出有缩进和换行的括号表达式
	 */
	@Override
	public String toString() {
		if(this.children.size() == 0){
			return " "+this.nodename;
		}else{
			String treestr = "";
			treestr = "("+this.nodename;			
			for (TreeNode node:this.children) {
				treestr += node.toString();
			}
			treestr += ")";
			return treestr;
		}
	}
	
	/**
	 * 输出有缩进和换行的括号表达式
	 * @param level 缩进的空格数
	 */
	public static String printTree(TreeNode tree, int level){
		
		if(tree.getChildren().size() == 1 && tree.getChildren().get(0).getChildren().size() == 0){
			return "("+tree.getNodeName()+" "+tree.getChildren().get(0).getNodeName()+")";
		}else if(tree.getChildren().size() == 1 && tree.getChildren().get(0).getChildren().size() == 1 && tree.getChildren().get(0).getChildren().get(0).getChildren().size() == 0){
			return "("+tree.getNodeName()+" "+"("+tree.getChildren().get(0).getNodeName()+" "+tree.getChildren().get(0).getChildren().get(0).getNodeName()+")"+")";
		}else if(tree.getChildren().size() > 1 && childrenHasOne(tree)){
			String str = "";
			str += "("+tree.getNodeName();
			str += " "+"("+tree.getChildren().get(0).getNodeName()+" "+tree.getChildren().get(0).getChildren().get(0).getNodeName()+")"+"\n";
			String s = "";
			for (int i = 1; i < tree.getChildren().size(); i++) {
//				s+="\n";
				for (int j = 0; j < level; j++) {
					s += "  ";
				}
				s += printTree(tree.getChildren().get(i),level+1);
				if(i == tree.getChildren().size()-1){
					s += ")";
				}else{
					s += "\n";
				}
			}
			return str + s;
		}
		else if(tree.getChildren().size() > 1  && childrenOnlyOne(tree)){
			String str = "";
			str += "("+tree.getNodeName();
			for (int i = 0; i < tree.getChildren().size(); i++) {
				if(tree.getChildren().get(i).getChildren().size() == 1 && tree.getChildren().get(0).getChildren().get(0).getChildren().size() == 0){
					if(i == tree.getChildren().size()-1){
						str += " "+"("+tree.getChildren().get(i).getNodeName()+" "+tree.getChildren().get(i).getChildren().get(0).getNodeName()+")"+")";
						return str;
					}else{
						str += " "+"("+tree.getChildren().get(i).getNodeName()+" "+tree.getChildren().get(i).getChildren().get(0).getNodeName()+")";
					}
				}
			}
			return str;
		}
		
		else{
			String treeStr = "";
			treeStr = "("+tree.getNodeName();
			treeStr += "\n";
			for (int i = 0; i < tree.getChildren().size(); i++) {
				for (int j = 0; j < level; j++) {
					treeStr += "  ";
				}
				treeStr += printTree(tree.getChildren().get(i),level+1);
				if(i == tree.getChildren().size()-1){
					treeStr += ")";
				}else{
					treeStr += "\n";
				}
			}
			return treeStr;
		}
		
	}
	
	/**
	 * 判断节点下，是否所有的节点都是子节点都是叶子节点
	 * @param tree
	 * @return
	 */
	private static boolean childrenOnlyOne(TreeNode tree){
		boolean flag = false;
		for (int i = 0; i < tree.getChildren().size(); i++) {
			if(tree.getChildren().get(i).getChildren().size() == 1 && tree.getChildren().get(0).getChildren().get(0).getChildren().size() == 0){
				flag = true;
			}else if(tree.getChildren().get(i).getChildren().size() > 1 || (tree.getChildren().get(1).getChildren().size() == 1) && tree.getChildren().get(1).getChildren().get(0).getChildren().size() > 0){
				flag = false;
				break;
			}
	   }
	   return flag;
    }
	
	public static boolean childrenHasOne(TreeNode tree){
		if(tree.getChildren().get(0).getChildren().size() == 1 && tree.getChildren().get(0).getChildren().get(0).getChildren().size() == 0){
			if(tree.getChildren().get(1).getChildren().size() > 1 || (tree.getChildren().get(1).getChildren().size() == 1) && tree.getChildren().get(1).getChildren().get(0).getChildren().size() > 0){
				return true;
			}
		}
		return false;
	}
}
