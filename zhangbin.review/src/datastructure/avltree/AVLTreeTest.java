package datastructure.avltree;

import org.junit.Before;
import org.junit.Test;

import datastructure.avltree.AVLTree.TreeNode;
import datastructure.binarysearchtree.BinarySearchTree;

public class AVLTreeTest {

	private static AVLTree<Integer> avlTree = new AVLTree<>();
	@Before
	public void setUp() throws Exception {
	    //avlTree.insert(7);
		avlTree.insert(4);
		avlTree.insert(2);
		avlTree.insert(3);
		avlTree.insert(9);
		avlTree.insert(6);
		avlTree.insert(8);
		avlTree.insert(1);
		avlTree.insert(5);
		avlTree.insert(12);
		avlTree.insert(10);
		avlTree.insert(-5);
	}

	@Test
	public void testMidPrint() {
		avlTree.Midprint();
	}
	
	@Test
	public void testPrePrint() {
		avlTree.Preprint();
	}
	@Test
	public void testContains() {
		boolean result = avlTree.contains(5);
		System.out.println(result);
	}
	
	@Test
	public void testmidOrderWithoutRecur() {
		avlTree.midOrderWithoutRecur();
	}
	
	@Test
	public void testIterator() {
		@SuppressWarnings("rawtypes")
		TreeNode node = avlTree.successor(4);
		System.out.println(node.data);
	}
	
	@Test
	public void testRemove() {
		avlTree.remove(4);
		avlTree.midOrderWithoutRecur();
	}
}
