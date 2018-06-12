package datastructure.leetcode;
/**
 * 根据num1  和  num2 找中值，如果奇数，返回中间值，如果偶数，返回中间两个值的平均值
 * 要求时间复杂度，O(long(m+n))
 * @author zhang
 *
 */
public class FindMidVal {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int[] result = new int[len];
        //先把要排数组拷进目的数组中
        int index = 0;
        for (int i : nums1) {
            result[index++] = i;
        }
        for(int i : nums2) {
            result[index++] = i;
        }
        //这儿通过计数排序进行排序
        
        int[] b = new int[1000000];
        int[] c = new int[len];
        
        for (int i=0 ; i < len; i++) {
            b[result[i]]++;
        }
        //进行计数
        for(int i = 1; i < b.length; i++) {
            b[i] += b[i-1];
        }
        
        //倒序读书
        for(int i = len -1; i >=0; i--) {
            c[b[result[i]]-1] = result[i];
            b[result[i]]--;
        }
        
        //拷贝
        for(int i = 0; i < len; i++) {
            result[i] = c[i];
        }
        
        if(len % 2 == 0) {
            return (result[len / 2] + result[len / 2 - 1]) * 1.0 / 2;
        }
        return result[len / 2];
        
    }
    
    public static void main(String[] args) {
        int[] num1 = {3,5,6,7,8,9};
        int[] num2 = {1,2,4};
        FindMidVal val = new FindMidVal();
        double d = val.findMedianSortedArrays(num1,num2);
        System.out.println(d);
    }
}
