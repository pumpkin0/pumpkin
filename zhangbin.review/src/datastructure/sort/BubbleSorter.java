package datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度为O(n2),不管数组有序、无序，它的时间复杂度应该不变
 * 最优情况下：时间复杂度 O(n*(n-1)/2)
 * 最坏情况下：时间复杂度O(n*3(n-1)/2)
 * sort2方法：
 * 空间复杂度(临时变量所占用的空间)：
 * 最优情况为0,即已经排序好的数组，不需要进行交换
 * 平均情况下为O(1)
 * 
 * 稳定性：
 * 稳定的算法
 * @author zhang
 *
 */
public class BubbleSorter {
    public static int[] sort(int[] array) {
        int pivot=0,min=Integer.MAX_VALUE,tmp,temp;
        for(int i = 0; i < array.length; i++) {
            for(int j = i; j < array.length-1;j++) {
                if(array[j] < array[j+1]) {
                    //交换，用于下一次的比较
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] =tmp;
                    //记录，用于输出
                    min = array[j+1];
                    pivot = j+1;
                    
                }
            }
            //从首位开始，逐步放置元素
            temp = array[i];
            array[i] = min;
            array[pivot] = temp;
        }
        return array;
    }
    /**
     * 空间复杂度：计算整个算法的辅助空间单元的个数
     * 在这儿，辅助空间单元的个数为1，所以为O(1);
     * 最优情况下，为O(0);
     * 
     */
    public static int[] sort2(int[] arr) {
        int temp;
        for(int i = 0; i < arr.length; i++) {
            for(int j = arr.length - 2; j >= i; j--) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
    
    
    static int[] bubbleSort(int array[], int length)  
    {  
        int i, j, tmp;  
        int flag = 1;  
          
        if (1 >= length) return null;  
      
        for (i = length-1; i > 0; i--, flag = 1){   
            for (j = 0; j < i; j++){  
                if (array[j] > array[j+1]){  
                    tmp = array[j];  
                    array[j] = array[j+1];  
                    array[j+1] = tmp;  
                    flag = 0;  
                }     
            } 
            //如果是一个有序的的数组，那么在里层循环 循环一次之后，flag的值仍为1,说明数组时有序的，直接跳出循环，那么他的时间复杂度
            //最优情况下为O(n)
            //最坏情况下为O(n2)
            if (flag==1) break;  
        }
        return array;
    } 
    
    
    
    public static void main(String[] args) {
        int[] arr = {5,2,3,6,9,7,4};
        arr = sort2(arr);
        System.out.println(Arrays.toString(arr));
        
        int[] sort = bubbleSort(arr,arr.length);
        System.out.println(Arrays.toString(sort));
        
    }
}
