package datastructure.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度：
 * 如果数组已经有序，那么插入排序的时间复杂度为O(n)
 * 极端情况下，插入排序的时间复杂度为O(n*n)
 * 冒泡排序的时间复杂度为O(n*n/2)
 * 空间复杂度：
 * O(1)
 * 稳定性：插入排序，从右往左匹配，只有比左边的元素小，其才会往前挪。即使相等，也是位于该元素的后面，稳定性不会被破坏
 * @author zhang
 *
 */
public class InsertSorter {
    public static int[] sort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            int pivot = array[i];
            int j = i -1;
            for(;j >= 0; j--) {
                if(pivot < array[j]) {
                    //向右移一位，空出一个位置
                    array[j+1] = array[j];
                }
                else {
                    break;
                }
            }
            array[j+1] = pivot;
        }
        return array;
    }
    public static void main(String[] args) {
        int[] arr = {5,2,3,6,9,7,4};
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
