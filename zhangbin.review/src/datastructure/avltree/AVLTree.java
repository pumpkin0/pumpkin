package datastructure.avltree;

import java.util.LinkedList;

public class AVLTree<T extends Comparable<T>> {
	/*
	 * 定义节点
	 */
	class TreeNode{
		public Object data;
		public int height ; //与高度相对的
		public int balance;
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
	
	public void insert(T i) {
		if(root == null) {
			root = new TreeNode(i);
			return;
		}
		insert0(root,i);
	}
	
	private void insert0(TreeNode root, T i) {
		if(i.compareTo((T)root.data) < 0) {
			if(root.left != null)
				insert0(root.left,i);
			else {
				root.left = new TreeNode(i);
				root.left.parent = root;
			}
				
		}
		else {
			if(root.right != null)
				insert0(root.right,i);
			else {
				root.right = new TreeNode(i);
				root.right.parent = root;
			}
		}
		root.balance = calcBalance(root);
		if(root.balance >= 2) {
			if(root.left.balance == -1) {
				left_rotate(root.left);
			}
			right_rotate(root);
		}
		
		if(root.balance <= -2) {
			if(root.right.balance == 1) {
				right_rotate(root.right);
			}
			left_rotate(root);
		}
		root.balance = calcBalance(root);
		root.height = calcHeight(root);
     
	}
	
	
	private void right_rotate(TreeNode node) {
		TreeNode nParent = node.parent;
		TreeNode nRightSon = node.left;
		TreeNode nLeftGrandSon = nRightSon.right;
		
		nRightSon.parent = nParent;
		if(nParent != null) {
			if(node == nParent.left)
				nParent.left = nRightSon;
			else
				nParent.right = nRightSon;
		}
		
		nRightSon.right = node;
		node.parent = nRightSon;
		
		if(nLeftGrandSon != null)
			node.left = nLeftGrandSon;
		
		nRightSon.height = calcHeight(nRightSon);
		nRightSon.balance = calcBalance(nRightSon);
	}

	private void left_rotate(TreeNode node) {
		TreeNode nParent = node.parent;
		TreeNode nLeftSon = node.right;
		TreeNode nRightGrandSon = nLeftSon.right;
		
		nLeftSon.parent = nParent;
		if(nParent != null) {
			if(node == nParent.left)
				nParent.left = nLeftSon;
			else
				nParent.right = nLeftSon;
		}
		
		nLeftSon.left = node;
		node.parent = nLeftSon;
		
		if(nRightGrandSon != null)
			node.left = nRightGrandSon;
		
		nLeftSon.height = calcHeight(nLeftSon);
		nLeftSon.balance = calcBalance(nLeftSon);
		
	}

	private Integer calcBalance(TreeNode node) {
		int left_height;
		int right_height;
		
		if(node.left != null)
			left_height = node.left.height;
		else
			left_height = 0;
		
		if(node.right != null)
			right_height = node.right.height;
		else
			right_height = 0;
		
		return left_height - right_height;
		
	}
	
	private Integer calcHeight(TreeNode node) {
		int height = 0;
		if(node.left != null)
			height = node.left.height;
		if(node.right != null && height < node.right.height)
			height = node .right.height;
		height++;	
		return height;
		
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
	private void printSelf2(TreeNode node) {
		System.out.println(node.data);
		if(node.left != null) {
			printSelf(node.left);
		}
		if(node.right != null) {
			printSelf(node.right);
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
