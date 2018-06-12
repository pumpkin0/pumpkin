package datastructure.avltree;

public class Test {
	public static void main(String[] args) {
		
		AVLTree<Integer> at = new AVLTree<>();
		for(int i = 1; i < 10; i++) {
			at.insert(i);
		}
		at.Preprint();
		at.Midprint();
		at.midOrderWithoutRecur();
	}
}
