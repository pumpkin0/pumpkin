package datastructure.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode current = result;
        int next = 0;
        while(l1 != null || l2 != null) {
            int val1 = (l1 == null)?0:l1.val;
            int val2 = (l2 == null)?0:l2.val;
            int sum = next + val1 + val2;
            next = sum / 10;
            current.next = new ListNode(sum % 10);
            
            l1 = l1.next;
            l2 = l2.next;
            
            current = current.next;
        }
        
        
        return result.next;
    }
    
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        
        Solution solution = new Solution();
        ListNode addTwoNumbers = solution.addTwoNumbers(l1, l2);
        System.out.println(addTwoNumbers);
        
    }
}
