package com.superh.hz.study.javatrain.hclinkedlist;

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
public class TestLinkedList {
	static int i=1;
	@Before
	public void beforetest(){
		System.out.println("====================测试用例"+i+"========================");
	}

	@After
	public void aftertest(){
		System.out.println("====================测试用例"+i+"========================");
		System.out.println();
		i++;
	}

	//测试普通的add和remove方法
	public void testAddandRemove(){
		
		System.out.println("测试add方法和remove方法：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
		System.out.println("链表是否为空：" + list.isEmpty());
		System.out.println("链表长度：" + list.size());
		System.out.println("链表为空时的firstnode：" + list.first());
		System.out.println("链表为空时的lastnode：" + list.last());

		System.out.println("添加元素aaa是否成功：" + list.add("aaa"));
		System.out.println("链表长度：" + list.size());
		System.out.println("链表是否为空：" + list.isEmpty());
		System.out.println("只含一个元素的firstnode：" + list.first());
		System.out.println("只含一个元素的lastnode：" + list.last());
		
		System.out.println("添加元素bbb是否成功：" + list.add("bbb"));
		System.out.println("是否包含元素ccc：" + list.contains("ccc"));
		System.out.println("添加元素ccc是否成功：" + list.add("ccc"));
		System.out.println("是否包含元素ccc：" + list.contains("ccc"));	
		System.out.println("添加元素ddd是否成功：" + list.add("ddd"));
		System.out.println("链表长度：" + list.size());
		System.out.println("多个元素的firstnode：" + list.first());
		System.out.println("多个元素的lastnode：" + list.last());
		
		System.out.println("删除元素aaa是否成功：" + list.remove("aaa"));
		System.out.println("删除元素ddd是否成功：" + list.remove("ddd"));
		System.out.println("删除元素aaa、ddd后的firstnode：" + list.first());
		System.out.println("删除元素aaa、ddd后的lastnode：" + list.last());
		
		System.out.println("测试add方法和remove方法：结束");
	}
	
	//测试指定位置的add方法
	@Test
	public void testAddParam(){
		
		System.out.println("测试add方法(带规则的)：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		list.add("eee");
		list.add("fff");
		System.out.println("是否包含元素ggg：" + list.contains("ggg"));
		System.out.println("是否包含元素hhh：" + list.contains("hhh"));
		System.out.println("链表长度：" + list.size());
		System.out.println("2位置的元素：" + list.get(2));
		System.out.println("ccc的index：" + list.indexof("ccc"));
		list.add("ggg",0);
		list.add("hhh",7);
		System.out.println("修改后是否包含元素ggg：" + list.contains("ggg"));
		System.out.println("修改后是否包含元素hhh：" + list.contains("hhh"));
		System.out.println("链表长度：" + list.size());
		System.out.println("2位置的元素：" + list.get(2));
		System.out.println("ccc的index：" + list.indexof("ccc"));

		System.out.println("修改元素后的firstnode：" + list.first());
		System.out.println("修改元素后的lastnode：" + list.last());

		System.out.println("测试add方法(带规则的)：结束");
	}

	//测试指定位置的remove方法
	@Test
	public void testRemoveParam(){
		
		System.out.println("测试remove方法(带规则的)：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
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
		list.removefirst();
		list.removelast();
		list.remove(0);
		System.out.println("修改后是否包含元素aaa：" + list.contains("aaa"));
		System.out.println("修改后是否包含元素bbb：" + list.contains("bbb"));
		System.out.println("修改后是否包含元素fff：" + list.contains("fff"));
		System.out.println("eee的index：" + list.indexof("eee"));
		System.out.println("表头节点是：" + list.first());
		System.out.println("表尾节点是：" + list.last());
		System.out.println("链表长度：" + list.size());
		
		System.out.println("测试remove方法(带规则的)：结束");
	}
	
	//测试get(Index)方法
	public void testGetindex(){
		
		System.out.println("测试get(Index)方法：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
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
		System.out.println("删除aaa和fff后第四个元素：" + list.get(3));
		list.add("ggg",0);
		System.out.println("删除aaa和fff，添加ggg后第二个元素：" + list.get(1));
		list.set(3, "hhh");
		System.out.println("修改后3位置的元素" + list.get(3));
		
		System.out.println("测试get(Index)方法：结束");
	}

	//测试null是否可以作为元素
	@Test
	public void testNull(){
		
		System.out.println("测试null的可兼容性：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
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

	//测试Iterator
	public void testIterator(){
		
		System.out.println("测试Iterator()的方法：开始");
		
		HcLikedList<String> list = new HcLikedList<String>();
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
