package com.spuerh.hz.study.javatrain.hclinkedlist;

import java.util.Iterator;

/**
 * 功能说明：自定义链表
 * 对外方法：HcLikedList()：构造空链表
 *       	size()：返回链表长度
 *       	isEmpty()：链表是否为空
 *      	contains(Object o): 链表是否包含指定元素
 *       	iterator(): 返回链表的遍历器Iterator
 *       	iterator(int index): 返回链表的遍历器Iterator，从index位置开始到尾端
 *       	toArray()：返回指定类型的数组
 *       	add(T e)：向链表尾端添加一个元素的引用
 *       	addfirst(T e): 向链表首端添加一个元素的引用
 *          add(T e， int index)：向链表index位置添加一个元素的引用
 *          removefirst(): 删除链表表头的引用
 *          removelast(): 删除链表表尾的引用
 *       	remove(Object o)：向链表中删除一个元素的引用
 *          remove(int index)：向链表中删除指定位置的元素
 *       	T firstNode()：返回链表中的第一个元素
 *       	T lastNode()：返回链表中的最后一个元素
 *       	clear()：清空链表
 *       	T get(int index)：获取索引index位置节点的对象
 *          void set(int index，T t)：设置索引index位置的对象
 *          indexof(Object o): 获取对象的索引
 * 内部类：HcIterator：实现Iterator接口
 * 		   HcNode<T>：链表节点
 * 
 * 2014-8-22
 * 
 */
public class HcLikedList2<T>{
	
	private int size = 0;
	private HcNode<T> lastNode = null;
	private HcNode<T> virtrulNode = null;
	
	public HcLikedList2(){
		virtrulNode = new HcNode<T>();
		lastNode = virtrulNode;
	}

	 /**
     * 返回链表长度
     * @return int
     */
	public int size() {
		return size;
	}

	 /**
     * 链表是否为空
     * @return boolean 如果链表包含任何元素返回真
     */
	public boolean isEmpty() {
			return size==0?true:false;
	}

	 /**
     * 链表是否包含指定元素
     * @param Object o
     * @return boolean 如果链表包含指定元素返回真
     */
	public boolean contains(Object o) {
		if(size == 0){
			return false;
		}else{
			HcNode<T> cur_node = virtrulNode.getNext();
			while(true){
					if(o==null){
						if(cur_node.getData()==null)return true;
						else return false;
					}else if(o.equals(cur_node.getData())){
						return true;
					}
					else{
						if(cur_node.getNext() == null){
							return false;
						}
						else{
							cur_node = cur_node.getNext();
					    }
				    }
				}
		}
	}

	 /**
     * 返回链表的遍历器Iterator
     * @return Iterator实现类
     */
	public Iterator<T> iterator() {
		HcIterator hcIterator = new HcIterator(0);
		return hcIterator;
	}

	/**
     * 返回链表的遍历器Iterator，从index位置开始到尾端
     * @param index
     * @return Iterator实现类
     */
	public Iterator<T> iterator(int index) {
		checkElementIndex(index);
		HcIterator hcIterator = new HcIterator(index);
		return hcIterator;
	}
	
	 /**
     * 返回指定类型的数组
     * @return Object[]
     */
	public Object[] toArray() {
		HcNode<T> curNode = null;
		Object[] array = (Object[]) new Object[size];
		if(size != 0 ){
			curNode = virtrulNode.getNext();
			for(int i=0;i<size;i++){
				array[i] = curNode.getData();
				curNode = curNode.getNext();
			}
		}else {
			array = null;
		}
		return array;
	}

	/**
    * 向链表尾端添加一个元素的引用
    * @param  T e
    * @return boolean 添加成功，链表长度加1，则返回为真
    */
	public boolean add(T e) {
		int pre_size = size;
		HcNode<T> newNode = new HcNode<T>(null,lastNode,e);
		lastNode.setNext(newNode);
		newNode.setPrevious(lastNode);
		this.lastNode = newNode;
		size++;
		if(pre_size != size)return true;
		else return false;
	}
	
	/**
	 * 向链表首端添加一个元素的引用
	 * @param  T e
	 * @return boolean 添加成功，链表长度加1，则返回为真
	 */
	public boolean addfirst(T e) {
			int pre_size = size;
			HcNode<T> firstNode = virtrulNode.getNext();
			HcNode<T> newNode = new HcNode<T>(firstNode,virtrulNode,e);
			firstNode.setPrevious(newNode);
			virtrulNode.setNext(newNode);
			size++;
			if(pre_size != size) return true;
			else return false;
	}
	
	/**
	 * 向链表index位置添加一个元素的引用,在原index元素之前
	 * @param  T e,int index
	 * @return boolean 添加成功，链表长度加1，则返回为真
	 */
	public boolean add(T e,int index) {
		int pre_size = size;
		HcNode<T> indexNode = hcnode(index);
		HcNode<T> tmpNode = indexNode.getPrevious();
		HcNode<T> newNode = new HcNode<T>(indexNode,tmpNode,e);
		indexNode.setPrevious(newNode);
		tmpNode.setNext(newNode);
		size++;
		if(pre_size != size)return true;
		else return false;
	}
	
	/**
	 * 删除链表表头的引用
	 * @return boolean 添加成功，链表大小减1，则返回为真
	 */
	public boolean removefirst() {
		if(size == 0){
			return false;
		}else{
			int pre_size = size;
			HcNode<T> firstNode = virtrulNode.getNext();
			if(firstNode.getNext()==null){
				virtrulNode.setNext(null);
				lastNode = virtrulNode;
				firstNode.setPrevious(null);
			}else{
				HcNode<T> secondNode = firstNode.getNext();
				virtrulNode.setNext(secondNode);
				secondNode.setPrevious(virtrulNode);
				firstNode.setNext(null);
				firstNode.setPrevious(null);
			}
			size--;
			if(pre_size != size)return true;
			else return false;
		}
	}

	/**
	 * 删除链表表尾的引用
	 * @return boolean 添加成功，链表大小减1，则返回为真
	 */
	public boolean removelast() {
		if(size == 0){
			return false;
		}else{
			int pre_size = size;
			HcNode<T> tmpNode = lastNode.getPrevious();
			tmpNode.setNext(null);
			lastNode.setPrevious(null);
			lastNode = tmpNode;
			size--;
			if(pre_size != size)return true;
			else return false;
		}
	}

	/**
	 * 向链表中删除一个元素的引用
	 * @param Object o
	 * @return boolean 添加成功，链表大小减1，则返回为真
	 */
	public boolean remove(Object o) {
		if(size == 0){
			return false;
		}else{
			HcNode<T> cur_node = virtrulNode.getNext();
			HcNode<T> cur_pre;
			HcNode<T> cur_next;
			while(true){
				if(o==null){
					if(cur_node.getData()==null){
						cur_pre = cur_node.getPrevious();
						cur_next = cur_node.getNext();
						cur_pre.setNext(cur_next);
						if((cur_node.getNext() == null)){
							this.lastNode = cur_pre;
						}else{
							cur_next.setPrevious(cur_pre);
						}
						if(this.lastNode == virtrulNode){
							this.lastNode = null;
						}
						cur_node.setNext(null);
						cur_node.setPrevious(null);
						size--;
						return true;
					}
					else return false;
				}else if(o.equals(cur_node.getData())){
					cur_pre = cur_node.getPrevious();
					cur_next = cur_node.getNext();
					cur_pre.setNext(cur_next);
					if((cur_node.getNext() == null)){
						this.lastNode = cur_pre;
					}else{
						cur_next.setPrevious(cur_pre);
					}
					if(this.lastNode == virtrulNode){
						this.lastNode = null;
					}
					cur_node.setNext(null);
					cur_node.setPrevious(null);
					size--;
					return true;
				}else{
					if(cur_node.getNext() == null){
						return false;
					}
					else{
						cur_node = cur_node.getNext();
				    }
			    }
			}
		}
	}

	/**
	 * 向链表中指定位置删除一个元素的引用
	 * @param Object o
	 * @return boolean 添加成功，链表大小减1，则返回为真
	 */
	public boolean remove(int index){
		checkElementIndex(index);
		int pre_size = size;
		HcNode<T> cur_node = hcnode(index);
		HcNode<T> pre_node = cur_node.getPrevious();
		HcNode<T> next_node = cur_node.getNext();
		cur_node.setNext(null);
		cur_node.setPrevious(null);
		pre_node.setNext(next_node);
		if(next_node == null){
			lastNode = pre_node;
		}else{
			next_node.setPrevious(pre_node);
		}
		size--;
		if(pre_size != size)return true;
		else return false;
	}
	
	/**
	 * 返回链表中的第一个元素
	 * @return T 
	 */
	public T firstNode() {
		if(size == 0 ) return null;
		else return virtrulNode.getNext().getData();
		
	}
	
	/**
	 * 返回链表中的最后一个元素
	 * @return T 
	 */
    public T lastNode() {
    	return lastNode.getData();
	}
    
    /**
	 * 清空链表
	 */
	public void clear() {
		size = 0;
		virtrulNode.setNext(null);
		lastNode = virtrulNode;
	}

	/**
	 * 获取索引index位置节点的对象
	 * @param  index
	 * @return T
	 */
	public T get(int index) {
		checkElementIndex(index);
	    return hcnode(index).getData();
	}
	
	/**
	 * 设置索引index位置节点的对象
	 * @param  index,T
	 */
	public void set(int index,T t){
		HcNode<T> indexNode = hcnode(index);
		indexNode.setData(t);
	}
	
	/**
	 * 获取对象的index索引
	 * @param  Object o
	 * @return int index
	 */
	public int indexof(Object o) {
		int index = 0;
		HcNode<T> curNode = virtrulNode.getNext();
	    for(;index<size&&(curNode!=null);index++){
	    	if(curNode.getData().equals(o)){
	    		break;
	    	}else{
	    		curNode = curNode.getNext();
	    	}
	    }
	    if(index == size)
	    	throw new IndexOutOfBoundsException();
	    else 
    		return index;
	}

	/**
	 * 检查传入的index是否合法
	 * @param  index
	 */
	private void checkElementIndex(int index) {
		if(index<0||index>=size)
			throw new IndexOutOfBoundsException();	
	}

	/**
	 * 获取索引index位置的节点
	 * @param  index
	 * @return HcNode<T>
	 */
	private HcNode<T> hcnode(int index) {
		 checkElementIndex(index);
	     if (index < (size/2)) {
	    	    HcNode<T> x = virtrulNode.getNext();
	            for (int i = 0; i < index; i++)
	                x = x.getNext();
	            return x;
	        } else {
	        	HcNode<T> x= lastNode;
	            for (int i = size - 1; i > index; i--)
	                x = x.getPrevious();
	            return x;
	        }
	}

	/**
	 * 内部类实现Iterator接口,返回本list的遍历器
	 * 变量：HcNode<T> nextNode;int nextIndex;
	 * 方法： boolean hasNext()
	 * 			 T next()
	 */
	class HcIterator implements Iterator<T> {

		
		private HcNode<T> nextNode;
	    private int nextIndex;
	    
	    private HcIterator(int index){
	    	nextNode = (index == size) ? null : hcnode(index);
            nextIndex = index;
		};

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public T next() {
			T curData = nextNode.getData();
			nextNode = nextNode.getNext();
			nextIndex++;
			return curData;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
	}
	
	/**
	 * 内部类HcNode,节点
	 * 变量： HcNode<T> next;下一个节点
	 * 		 HcNode<T> previous; 上一个节点
	 * 		 T data;数据域
	 */
	@SuppressWarnings("hiding")
	class HcNode<T> {
	       private HcNode<T> next; 
	       private HcNode<T> previous; 
	       private T data;
	    
	       
	    public HcNode(HcNode<T> next,HcNode<T> previous,T data){
	    	this.next = next;
	    	this.previous = previous;
	    	this.data = data;
	    }
	    
	    public HcNode(){
	    	this.next = null;
	    	this.previous = null;
	    	this.data = null;
	    }
	    
		/**
		 * @return the next
		 */
		public HcNode<T> getNext() {
			return next;
		}
		
		/**
		 * @param next the next to set
		 */
		public void setNext(HcNode<T> next) {
			this.next = next;
		}
		
		/**
		 * @return the previous
		 */
		public HcNode<T> getPrevious() {
			return previous;
		}
		
		/**
		 * @param previous the previous to set
		 */
		public void setPrevious(HcNode<T> previous) {
			this.previous = previous;
		}
		
		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}
		
		/**
		 * @param data the data to set
		 */
		public void setData(T data) {
			this.data = data;
		} 
	}
	
	
}
