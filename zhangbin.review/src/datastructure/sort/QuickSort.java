package datastructure.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author zhang
 * 以尾元素作为每次的分分治点
 * 时间复杂度：
 * 平均情况下： nLogn
 * 最坏情况下：n2
 * 
 * 空间复杂度：
 * o(1)
 * 
 * 稳定性分析：
 * 不稳定
 *
 */
public class QuickSort {
    
    public int[] sort(int[] array) {
        quickSort(array,0,array.length-1);
        return array;
    }

    private void quickSort(int[] array, int begin, int end) {
        //递归的出口，必须得写
        if(begin >= end)
            return;
        
        int position = partition(array,begin,end);
        quickSort(array, begin, position-1);
        quickSort(array, position+1, end);
    }

    private int partition(int[] array, int begin, int end) {
        int x = array[end];
        int i = begin-1;
        for(int j = begin; j < end; j++) {
            if(array[j] <= x) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        int temp = array[i+1];
        array[i+1] = array[end];
        array[end] = temp;
        return i+1;
    }
    
    public static void main(String[] args) {
        int[] arr = {52,21,32,61,95,77,43,11,35,66};
        QuickSort quickSort = new QuickSort();
        arr = quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

   
}
