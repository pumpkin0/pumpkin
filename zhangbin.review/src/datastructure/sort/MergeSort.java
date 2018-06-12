package datastructure.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author zhang
 * 时间复杂度(最坏情况下不发生变化)：
 * O(nlogn)
 * 
 * 空间复杂度：
 * O(n)
 */
public class MergeSort {
    private int[] array2;
    public int[] sort(int[] array) {
        array2 = new int[array.length];
        mergeSort(array,0,array.length-1);
        for(int i = 0; i < array.length; i++) {
            array[i] = array2[i];
        }
        return array;
    }
    private void mergeSort(int[] array, int begin, int end) {
        if(begin < end) {
            int mid = (begin + end)/2;
            //递归的拆解成更小的子问题
            mergeSort(array,begin,mid);
            mergeSort(array,mid+1,end);
            //合并子问题(这儿，当只有一个元素的时候，认为子问题得到解决)
            merge(array,begin,mid,end);
        }
    }
    private void merge(int[] array, int begin, int mid, int end) {
        int i = begin;
        int k = begin;
        int j = mid+1;
        while(i <= mid && j <= end) {
            if(array[i] <= array[j]) {
                array2[k++] = array[i++];
            }else {
                array2[k++] = array[j++];
            }
        }
        
        while(i <= mid) {
            array2[k++] = array[i++];
        }
        while(j <= end) {
            array2[k++] = array[j++];
        }
        
        for(int t = begin; t <= end; t++) {
            array[t] = array2[t];
        }
    }
    
    
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {52,21,32,61,95,77,43,11,35,66};
        arr = mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
