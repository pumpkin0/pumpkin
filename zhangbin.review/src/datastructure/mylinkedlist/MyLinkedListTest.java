package datastructure.mylinkedlist;

public class MyLinkedListTest {
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.print();
		System.out.println();
		list.delete(4);
		list.print();
		System.out.println();
		list.delete(9);
		list.print();
		System.out.println();
		list.delete(8);
		list.print();
		System.out.println();
		System.out.println(list.get(4));
		
		System.out.println("元素的修改");
		list.update(0, 5);
		list.print();
		System.out.println();
		list.update(1, 8);
		list.print();
		System.out.println();
		System.out.println("在三个元素之后添加元素12");
		list.insert(2, 12);
		System.out.println();
		list.print();
		
		
		
	}
}
