package datastructure.binarysearchtree;

import org.junit.Before;
import org.junit.Test;

import datastructure.binarysearchtree.BinarySearchTree.TreeNode;

public class BinarySearchTreeTest {
	private static BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
	@Before
	public void setUp() throws Exception {
		binarySearchTree.insert(4);
		binarySearchTree.insert(2);
		binarySearchTree.insert(3);
		binarySearchTree.insert(9);
		binarySearchTree.insert(6);
		binarySearchTree.insert(8);
		binarySearchTree.insert(1);
		binarySearchTree.insert(7);
	}

	@Test
	public void testMidPrint() {
		binarySearchTree.Midprint();
	}
	
	@Test
	public void testPrePrint() {
		binarySearchTree.Preprint();
	}
	@Test
	public void testContains() {
		boolean result = binarySearchTree.contains(5);
		System.out.println(result);
	}
	
	@Test
	public void testmidOrderWithoutRecur() {
		binarySearchTree.midOrderWithoutRecur();
	}
	
	@Test
	public void testIterator() {
		@SuppressWarnings("rawtypes")
		TreeNode node = binarySearchTree.successor(4);
		System.out.println(node.data);
	}
	
	@Test
	public void testRemove() {
		binarySearchTree.remove(4);
		binarySearchTree.midOrderWithoutRecur();
	}
}
