package com.superh.hz.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainClass {

	public static void main(String[] args) throws IOException {
		Set<Node> set = new HashSet<Node>();
		Node node1 = new Node();
		node1.setId(1);
		node1.setParentId(0);
		node1.setCode("node1");
		Node node2 = new Node();
		node2.setId(2);
		node2.setParentId(1);
		node2.setCode("node2");
		Node node3 = new Node();
		node3.setId(3);
		node3.setParentId(1);
		node3.setCode("node3");
		Node node4 = new Node();
		node4.setId(4);
		node4.setParentId(2);
		node4.setCode("node4");
		set.add(node1);
		set.add(node2);
		set.add(node3);
		set.add(node4);
		
		System.out.println(bean2Json(getTree(set)));
		
		
		
	}
	
	
	public static TreeNode getTree(Set<Node> set) throws IOException{
		
		TreeNode rootNode = new TreeNode();
		
		for(Node node:set){
			if(node.getParentId() == 0){
				rootNode = new TreeNode(node.getId(),0,
						node.getCode(),new ArrayList<TreeNode>());
				break;
			}

		}
		
		buildTree(set,rootNode);
		return rootNode;
		
	}
	
	private static void buildTree(Set<Node> set,TreeNode fatherNode){
		
		for(Node node:set){
			if(node.getParentId() == fatherNode.getId()){
				
				TreeNode treeNode = new TreeNode(node.getId(),node.getParentId(),
						node.getCode(),new ArrayList<TreeNode>());
				
				fatherNode.getChildren().add(treeNode);
				
				buildTree(set,treeNode);
				
			}
		}
		
	}
	
	public static String bean2Json(Object obj) throws IOException {
		if(obj !=null){
			ObjectMapper mapper = new ObjectMapper();
			StringWriter sw = new StringWriter();
			JsonGenerator gen = new JsonFactory().createGenerator(sw);
			mapper.writeValue(gen, obj);
			gen.close();
			return sw.toString();
		}
		return null ;
	}

	
}
