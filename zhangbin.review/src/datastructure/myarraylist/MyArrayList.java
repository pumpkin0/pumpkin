package datastructure.myarraylist;
/**
 * 简单的实现了MyArrayList的增删查改
 * 提供了两种构造方法
 * @author zhang
 *
 */
public class MyArrayList {
	private int count = 3;
	private static int position;
	private int[] array;
	public MyArrayList(int count) {
		this.count = count;
		array = new int[count];
	}
		
	public MyArrayList() {
		array = new int[count];
	}
	
	public void put(int ele) {
		position++;
		if(position >= count) {
			count *= 2;
			int[] newArray = new int[count];
			System.arraycopy(array, 0, newArray, 0, array.length);
			array = newArray;
		}
		array[position - 1] = ele;
	}
	
	public Integer get(int index) {
		return array[index];
	}
	/**
	 * 返回修改前的结果
	 * @param index
	 * @param ele
	 */
	public Integer update(int index, int ele) {
		int old = array[index];
		array[index] = ele;
		return old;
	}
	/**
	 * note:需要有大量的复制工作
	 * @param index
	 */
	public void delete(int index) {
		for(int i = index; i <= position; i++) {
			array[i] = array[i + 1];
		}
		position--;
	}
	
	public void print() {
		for(int i = 0; i < position; i++) {
			System.out.print(array[i] + " ");
		}
	}
	
}
