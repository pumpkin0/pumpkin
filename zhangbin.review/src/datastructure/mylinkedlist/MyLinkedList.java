package datastructure.mylinkedlist;
/**
 * 单链表的实现
 * @author zhang
 *
 */
public class MyLinkedList {
	//节点的定义
	class Node{
		private Node next;
		private int data;
		public Node(int data) {
			this.next = null;
			this.data = data;
		}
		public void add(Node newNode) {
			if(this.next == null)
				this.next = newNode;
			else
				this.next.add(newNode);
		}
	}
	
	private Node root;
	//记录元素的个数
	//private static int count = 0;
	/*
	 * 增加一个新的节点
	 */
	public void add(int data) {
		Node newNode = new Node(data);
					
		if(root == null) {
			root = newNode;
		}else {
			root.add(newNode);
		}
		//count++;
	}
	
	/**
	 * 删除元素
	 * @param data
	 */
	public void delete(int data) {
		Node temp = root;
		while(temp.next != null) {
			if(temp.next.data == data) {
				if(temp.next.next == null) {
					temp.next = null;
					return;
				}
				temp.next = temp.next.next;
			}
			temp = temp.next;
		}
	}
	
	/**
	 * 查到对应的元素
	 */
	public Integer get(int index) {
		if(root == null)
			return null;
		Node temp = root;
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.data;
	}
	
	/**
	 * 修改某位置的元素,index从0开始
	 */
	public void update(int index, int data) {
		if(root == null)
			return;
		Node temp = root;
		for(int i = 0; i < index; i++) {
			if(i != 0) {
				temp = temp.next;
			}
			temp.data = data;
		}
		
	}
	
	/**
	 * 在某一个索引位置之后插入一个元素
	 */
	public void insert(int index, int data) {
		if(root == null)
			return;
		Node newNode = new Node(data);
		Node temp = root;
		for(int i = 0; i < index; i++) {
			//找到要插入元素的位置
			temp = temp.next;
		}
		newNode.next = temp.next;
		temp.next = newNode;
	}
	
	
	/**
	 * 打印方法
	 */
	public void print() {
		if(root == null)
			return;
		Node pivot = root;
		while(pivot != null) {
			System.out.print(pivot.data + " ");
			pivot = pivot.next;
			
		}
		
	}
	
	
	
	
}
