package dynamicPlan;

/**
 * 求最大子序列的和
 * @author zhang
 *
 */
public class MaxSubSum {
   /* public static void main(String[] args) {
        int[] arr = {-5,1,2,3,-1,2,-2};
        int maxSum = getMaxSumViolent(arr);
        System.out.println(maxSum);
    }*/
    /*
     * 暴力求解，遍历，时间复杂O(n2)
     */
    /*public static int getMaxSumViolent(int[] arr) {
        int temp,j;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++) {
            temp = 0;
            j = 0;
            for(j = 0; j < i; j++) {
               temp += arr[j];
            }
            if(temp > max) {
                max = temp;
            }
        }
        return max ;
    }*/
    
   /* public static int getMaxSumDynamicPlan(int[] arr) {
        
    }*/
}
