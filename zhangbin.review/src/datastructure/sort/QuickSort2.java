package datastructure.sort;

import java.util.Arrays;
/**
 * 快速排序
 * 实现方式：
 * 把首位元素作为分治点，把数组分成两部分，递归完成排序
 * @author zhang
 *
 */
public class QuickSort2 {
    public int[] sort(int[] array) {
        quickSort(array,0,array.length-1);
        return array;
    }

    private void quickSort(int[] array, int begin, int end) {
        if(begin >= end) {
            return;
        }
        int position = partition(array,begin,end);
        quickSort(array, begin, position-1);
        quickSort(array, position+1, end);
    }
    //以首位元素作为分治点
    private int partition(int[] array, int begin, int end) {
        int x = array[begin];
        int i = begin;
        for(int j = begin + 1; j <= end; j++) {
            if(array[j] <= x) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        //把第i个元素和第1个元素调换
        int temp = array[begin];
        array[begin] = array[i];
        array[i]= temp;
        
        return i;
    }
    
    public static void main(String[] args) {
        int[] arr = {52,21,32,61,95,77,43,11,35,66};
        QuickSort quickSort = new QuickSort();
        arr = quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}   
