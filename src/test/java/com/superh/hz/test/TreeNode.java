package com.superh.hz.test;

import java.util.List;

public class TreeNode {
	private int id;
	private int parentId;
	private String code;
	private List<TreeNode> children;
	
	public TreeNode(int id, int parentId, String code, List<TreeNode> children) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.code = code;
		this.children = children;
	}
	
	public TreeNode() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the children
	 */
	public List<TreeNode> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
}
