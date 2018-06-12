package datastructure.sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author zhang
 * 题目信息：有n个数，取值范围为0-n2，写出一个时间复杂度与空间复杂度均为 O(n)的实现方法
 * 
 * 空间复杂度：
 * 算法计算，需要的额外的辅助空间为3n，所以，本算法的空间复杂度为 O(n)
 * 时间复杂度：需要的时间为n的常数倍，基本为10，所以时间复杂度为 O(n)
 */
public class RadixSorter {
    private int n;
    public RadixSorter(int n) {
        this.n = n;
    }
    
    public int[] sort(int[] array) {
        //数组b用来存放各个位置的基数，接着对数组b进行排序
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];
        for(int i = 0; i < 2; i++) {
            //先利用个位数字进行排序，然后再用十位数字进行排序
            for(int j = 0; j < n; j++) {
                b[j] = acquireRadix(i,n,array[j]);
                c[b[j]]++;
            }
            //进行计数的统计
            for(int j = 1; j < n; j++) {
                c[j] += c[j-1];
            }
            //倒序，对数组array进行排序
            for(int j = n-1; j>=0; j--) {
                d[c[b[j]]-1] = array[j];
                c[b[j]]--;
            }
            
            //将数组d中的值复制到数组array中
            for(int j = 0; j < n; j++) {
                array[j] = d[j];
            }
            
            //清空数组c，方便下次排序使用
            for(int j = 0; j < n; j++) {
                c[j] = 0;
            }
        }
        return array;
    }

    private int acquireRadix(int i, int len, int ele) {
        int divisor = (int) Math.pow(len, i);
        return (ele / divisor)%len;
    }
    
    public static void main(String[] args) {
        RadixSorter radixSorter = new RadixSorter(10);
        int[] arr = {52,21,32,61,95,77,43,11,35,66};
        arr = radixSorter.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
}
