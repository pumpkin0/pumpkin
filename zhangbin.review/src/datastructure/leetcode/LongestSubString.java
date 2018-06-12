package datastructure.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        //从第一位开始遍历,记录最大值，直到最后一位，然后返回最大的值
        int maxLength = s.length();
        //定义一个优先队列，用于直接返回最大的值
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //定义一个hashmap用于存放每一个字符
        HashMap<Character,Integer> map = new HashMap<>(); 
        //定义一个计数器
        int count = 0;
        Character temp = null;
        for(int i = 0; i < maxLength; i++) {
            count = 0;
            map.clear();
            for(int j = i; j < maxLength; j++) {
                temp = s.charAt(j);
                Integer temp_value = map.get(temp);
                if(temp_value == null) {
                    count++;
                    map.put(temp, 1);
                }else {
                    break;
                }
            }
            queue.add(count);
        }
        return queue.peek();
        
        
       
       
        
    }
    public static void main(String[] args) {
        String s = "bbbbb";
        LongestSubString longestSubString = new LongestSubString();
        int lengthOfLongestSubstring = longestSubString.lengthOfLongestSubstring(s);
        System.out.println(lengthOfLongestSubstring);
    }
}
