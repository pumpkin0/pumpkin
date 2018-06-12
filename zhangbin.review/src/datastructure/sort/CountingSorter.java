package datastructure.sort;

import java.util.Arrays;
/**
 * 时间复杂度：时间复杂度为 O(n) 
 * 但有一个弊端，排序元素的的差值不能太大，如果相差太大，辅助数组的长度也太大，空间占用太大，效率会降低
 * 
 * 空间复杂度：空间复杂度为O(n)
 * @author zhang
 *
 */
public class CountingSorter {
    
    public static int[] sort(int[] array) {
        int len = array.length;
        //这儿找出array数组的最大值
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        
        int[] b = new int[max+1];
        int[] c = new int[max+1];
        
        for (int i = 0; i < len; i++) {
           //以array数组的值作为b数组的索引进行存放
           b[array[i]]++;
        }
        
        //进行计数
        for(int i = 1; i <= max; i++) {
            b[i] += b[i-1];
        }
        
        //在以倒序的方式读取b数组中的元素
        for(int i = len; i >=1; i--) {
            c[b[array[i-1]]-1] = array[i-1];
        }
        //把c数组中的值copy到原数组
        for(int i = 0; i < len; i++) {
            array[i] = c[i];
        }
        return array;
    }
    
    //测试
    public static void main(String[] args) {
        int[] arr = {5,2,3,6,9,7,4};
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
