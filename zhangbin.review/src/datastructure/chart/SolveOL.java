package datastructure.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 欧拉回路
 * @author zhang
 *
 */
public class SolveOL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            String str = scanner.next();
            list.add(str);
        }
        
        boolean result = judgeResult(list);
        System.out.println(result);
    }

    private static boolean judgeResult(ArrayList<String> list) {
        //定义两个集合，暂时命名为首集合 、尾集合，分别统计两个首位字符的个数
        HashMap<Character,Integer> firstCharMap = new HashMap<>();
        HashMap<Character,Integer> lastCharMap = new HashMap<>();
        //查找首字符在尾集合中出现的次数
        //查找尾字符在首集合中出现的次数
        //需要考虑同一个字符串，首位字符相同的情况
        for (String str : list) {
            char firstChar = str.charAt(0);
            char lastChar = str.charAt(str.length()-1);
            Integer firstCharCount = firstCharMap.get(firstChar);
            if(firstCharCount != null) {
                firstCharMap.put(firstChar, firstCharCount + 1);
            }else{
                firstCharMap.put(firstChar, 1);
            }
            
            Integer lastCharCount = lastCharMap.get(lastChar);
            if(lastCharCount != null) {
                lastCharMap.put(lastChar, lastCharCount + 1);
            }else {
                lastCharMap.put(lastChar, 1);
            }
        }
        //统计奇点出现的个数
        int count = 0;
        for (String str : list) {
            char firstNode = str.charAt(0);
            char lastNode = str.charAt(str.length()-1);
            Integer a = firstCharMap.get(lastNode);
            Integer b = lastCharMap.get(firstNode);
            if(a == null) {
                a = 0;
            }
            if(b == null) {
                b = 0;
            }
            if(firstNode != lastNode) {
                a += 1;
                b += 1;
            }
            //统计度数为奇数的节点个数
            if(a % 2 == 1) {
                count++;
            }
            if(b % 2 == 1) {
                count++;
            }
        }
        //根据欧拉回路的结论，只有两个奇点的连通图可以一笔画成。全是偶点，那么一定可以一笔画成，所以只考虑奇点的情况
        if(count == 2) {
            return true;
        }
        return false;
    }
}
