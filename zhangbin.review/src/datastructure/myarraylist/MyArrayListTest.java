package datastructure.myarraylist;

public class MyArrayListTest {
	public static void main(String[] args) {
		MyArrayList list = new MyArrayList();
		list.put(1);
		list.put(2);
		list.put(3);
		list.put(4);
		list.put(5);
		list.put(6);
		
		list.print();
		
		/*Integer result = list.get(4);
		System.out.println("result : " + result);
		
		Integer update = list.update(0, 9);
		System.out.println(update);
		list.print();
*/		
		System.out.println("=====================");
		list.delete(3);
		list.print();
	}
}
