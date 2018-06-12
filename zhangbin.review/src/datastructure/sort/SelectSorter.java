package datastructure.sort;

import java.util.Arrays;

/**
 * 选择排序，贪心思想的体现
 * 选择排序每次都是从第一个开始去操作，平均时间复杂度 O(n2)
 * 最优情况下，时间复杂度为 O(n2)
 * 最坏情况下，时间负杂度为O(2n*3n)
 * 空间复杂度：
 * 最优情况：为0
 * 最坏情况：O(1)
 * @author zhang
 *
 */
public class SelectSorter {
    public static int[] sort(int[] array){
        int min,pivot=-1,temp;
        for(int i = 0; i < array.length; i++) {
            min = array[i];
            for(int j = i; j < array.length; j++) {
                if(array[j] <= min) {
                    min = array[j];
                    pivot = j;
                }
            }
            if(pivot != i) {
                temp = array[i];
                array[i] = min;
                array[pivot] = temp;
            }
            
        }
        return array;
    }
    
    public static void main(String[] args) {
        int[] arr = {5,2,3,6,9,7,4};
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
