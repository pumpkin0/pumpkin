package datastructure.binarysearchtree;

import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> {
	/*
	 * 定义节点
	 */
	class TreeNode{
		public Object data;
		public TreeNode parent;
		public TreeNode left;
		public TreeNode right;
		public int state;
		public TreeNode(Object data) {
			this.data = data;
		}
		
	}
	//定义一个根节点
	TreeNode root;
	/**
	 * 插入一个元素
	 * @param i
	 * @return
	 */
	public boolean insert(T i) {
		TreeNode node = new TreeNode(i);
		if(root == null) {
			root = node;
			return true;
		}
		TreeNode current = root;
		//插入元素的过程
		while(true) {
			if(i.compareTo((T)current.data) < 0) {
				if(current.left==null) {
					current.left=node;
					node.parent = current;
					return true;
				}
				else
					current = current.left;
			}
			else {
				if(current.right == null) {
					current.right = node;
					node.parent = current;
					return true;
				}	
				else
					current = current.right;
			}
		}
	}
	/**
	 *判断是否包含某一个元素
	 * @param i
	 * @return
	 */
	public boolean contains(T i) {
		if(root == null)
			return false;
		TreeNode current = root;
		while(true) {
			if(i.compareTo((T)current.data) == 0)
				return true;
			if(i.compareTo((T)current.data) < 0) {
				if(current.left != null)
					current = current.left;
				else
					return false;
			}else {
				if(current.right != null)
					current = current.right;
				else
					return false; 
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 添加一个递归的中序遍历的方法
	 */
	public void Midprint() {
		if(root == null)
			return;
		printSelf(root);
	}
	//错误，晚上修改 2018.4.15 15:43
	//已修改，应该用if，错用while
	//这是个中序遍历的方式
	private void printSelf(TreeNode node) {
		if(node.left != null) {
			printSelf(node.left);
		}
		System.out.println(node.data);
		if(node.right != null) {
			printSelf(node.right);
		}
	}
	
	/**
	 * 添加一个递归的前序遍历的方法
	 */
	public void Preprint() {
		if(root == null)
			return;
		printSelf2(root);
	}
	//错误，晚上修改 2018.4.15 15:43
	//已修改，应该用if，错用while
	//前序遍历
	private void printSelf2(TreeNode node) {
		System.out.println(node.data);
		if(node.left != null) {
			printSelf2(node.left);
		}
		if(node.right != null) {
			printSelf2(node.right);
		}
	}
	
	
	/**
	 * 非递归的中序遍历的方法
	 */
	
	public void midOrderWithoutRecur() {
		LinkedList<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		TreeNode current;
		while(stack.peek()!= null) {
			current = stack.peek();
			if(current.state == 0) {
				if(current.left != null)
					stack.push(current.left);
				current.state = 1;
			}
			else if(current.state == 1) {
				System.out.println(current.data);
				if(current.right != null)
					stack.push(current.right);
				current.state = 2;
			}
			else if(current.state == 2) {
				stack.pop();
				current.state = 0;
			}
			
		}
	}
	
		
	/**
	 * 迭代器的实现,找到其的后继节点
	 */
	public TreeNode successor(T i) {
		TreeNode findPosition = findPosition(i);
		return successor0(findPosition);
	}
	
	/**
	 *  迭代器的实现
	 */
	private  TreeNode successor0(TreeNode node) {
		if(node == null)
			return null;
		TreeNode current;
		if(node.right != null) {
			current = node.right;
			if(current.left != null) {
				current = current.left;
			}
			return current;
		}
		else if(node.parent != null ) {
			current = node.parent;
			
			//如果是父亲节点的右儿子
			while(current.left != node) {
				node = current;
				current = node.parent;
			}
			return current;
		}
		return null;
	}
	
	/**
	 *寻找某个元素的位置
	 */
	private TreeNode findPosition(T i) {
		if(root == null)
			return null;
		TreeNode current = root;
		while(true) {
			if(i.compareTo((T)current.data) == 0)
				return current;
			if(i.compareTo((T)current.data) < 0) {
				if(current.left != null)
					current = current.left;
				else
					return null;
			}else {
				if(current.right != null)
					current = current.right;
				else
					return null; 
			}
		}
	}
	
	/**
	 * 删除容器中的某个元素
	 */
	public void remove(T i) {
		TreeNode position = findPosition(i);
		remove0(position);
	}
	
	
	
	private void remove0(TreeNode node) {
		
		TreeNode parent = node.parent;
		TreeNode next,child = null;
		//当其没有子节点时
		if(node.left == null && node.right == null) {
			if(node == root) {
				root = null;
				return;
			}
			
			if(parent.left == node) {
				parent.left = null;
			}
			
			if(parent.right == node) {
				parent.right = null;
			}
			
		}
		//当其有两个子节点时
		//把后继节点的值给拷贝过来，删除后继节点
		else if(node.right != null && node.left != null) {
			next = successor0(node);
			node.data = next.data;
			remove0(next);
			
		}
		//当其有一个子节点时
		else {
			if(node.left != null)
				child = node.left;
			else if(node.right != null)
				child = node.right;
			
			if(node == root) {
				root = child;
				child.parent = null;
			}
			
			if(parent.left == node) {
				parent.left = child;
			}
			else {
				parent.right = child;
			}
				
		}
	}
}
