package com.spuerh.hz.study.javatrain.hclinkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 功能说明：测试自定义链表
 * 
 * 2014-8-22
 * 
 */
public class TestLinkedList2 {
	static int i=1;
	@Before
	public void beforetest(){
		System.out.println("====================测试用例"+i+"========================");
	}
	
	@After
	public void aftertest(){
		System.out.println("====================测试用例"+i+"========================");
		i++;
	}
	@Test
	public void testAddandRemove(){
		
		System.out.println("测试add方法和remove方法：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		System.out.println("链表是否为空：" + list.isEmpty());
		System.out.println("链表长度：" + list.size());
		System.out.println("链表为空时的firstnode：" + list.firstNode());
		System.out.println("链表为空时的lastnode：" + list.lastNode());

		System.out.println("添加元素aaa是否成功：" + list.add("aaa"));
		System.out.println("链表长度：" + list.size());
		System.out.println("链表是否为空：" + list.isEmpty());
		System.out.println("一个元素的firstnode：" + list.firstNode());
		System.out.println("一个元素的lastnode：" + list.lastNode());
		
		System.out.println("添加元素bbb是否成功：" + list.add("bbb"));
		System.out.println("是否包含元素ccc：" + list.contains("ccc"));
		System.out.println("添加元素ccc是否成功：" + list.add("ccc"));
		System.out.println("是否包含元素ccc：" + list.contains("ccc"));	
		System.out.println("添加元素ddd是否成功：" + list.add("ddd"));
		System.out.println("链表长度：" + list.size());
		System.out.println("多个元素的firstnode：" + list.firstNode());
		System.out.println("多个元素的lastnode：" + list.lastNode());
		
		System.out.println("删除元素aaa是否成功：" + list.remove("aaa"));
		System.out.println("删除元素ddd是否成功：" + list.remove("ddd"));
		System.out.println("删除元素后的firstnode：" + list.firstNode());
		System.out.println("删除元素后的lastnode：" + list.lastNode());
		
		System.out.println("测试add方法和remove方法：结束");
	}
	
	@Test
	public void testAddParam(){
		
		System.out.println("测试add方法(带规则的)：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		System.out.println("是否包含元素ggg：" + list.contains("ggg"));
		System.out.println("链表长度：" + list.size());
		System.out.println("2位置的元素：" + list.get(2));
		System.out.println("ccc的index：" + list.indexof("ccc"));
		list.add("ggg",0);
		System.out.println("是否包含元素ggg：" + list.contains("ggg"));
		System.out.println("链表长度：" + list.size());
		System.out.println("2位置的元素：" + list.get(2));
		System.out.println("ccc的index：" + list.indexof("ccc"));

		System.out.println("修改元素后的firstnode：" + list.firstNode());
		System.out.println("修改元素后的lastnode：" + list.lastNode());

		System.out.println("测试add方法(带规则的)：结束");
	}

	public void testRemoveParam(){
		
		System.out.println("测试remove方法(带规则的)：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		System.out.println("是否包含元素aaa：" + list.contains("aaa"));
		System.out.println("是否包含元素fff：" + list.contains("fff"));
		System.out.println("eee的index：" + list.indexof("eee"));
		System.out.println("链表长度：" + list.size());
		list.remove(0);
		System.out.println("是否包含元素aaa：" + list.contains("aaa"));
		System.out.println("是否包含元素fff：" + list.contains("fff"));
		System.out.println("eee的index：" + list.indexof("eee"));
		System.out.println("表头节点是：" + list.firstNode());
		System.out.println("表尾节点是：" + list.lastNode());
		System.out.println("链表长度：" + list.size());
		
		System.out.println("测试remove方法(带规则的)：结束");
	}
	
	public void testIndexOf(){
		
		System.out.println("测试IndexOf方法：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		System.out.println("第六个元素：" + list.get(5));
		System.out.println("第二个元素：" + list.get(1));
		list.remove("aaa");
		System.out.println("删除aaa后第二个元素：" + list.get(1));
		list.remove("fff");
		System.out.println("添加fff后第四个元素：" + list.get(3));
		System.out.println("测试IndexOf方法：结束");
	}

	public void testNull(){
		
		System.out.println("测试null的可兼容性：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		System.out.println("是否为空：" + list.isEmpty());
		System.out.println("长度：" + list.size());
		System.out.println("添加元素null是否成功：" + list.add(null));
		System.out.println("长度：" + list.size());
		System.out.println("是否为空：" + list.isEmpty());
		System.out.println("添加元素bbb是否成功：" + list.add("bbb"));
		System.out.println("是否包含元素null：" + list.contains(null));	
		System.out.println("删除元素null是否成功：" + list.remove(null));
		System.out.println("长度：" + list.size());
		
		System.out.println("测试null的可兼容性：结束");
	}

	public void testIterator(){
		
		System.out.println("测试Iterator()的方法：开始");
		
		HcLikedList2<String> list = new HcLikedList2<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		Iterator<String> iterator = list.iterator(0);
		while(iterator.hasNext()){
			System.out.println("测试Iterator遍历：" + iterator.next());
		}
		
		System.out.println("测试Iterator()的方法：结束");
	}
	
	public void testptList(){
		System.out.println("测试普通的List");
		List<String> list = new LinkedList<String>();
		System.out.println("长度" + list.size());
	}
}
