package datastructure.graph;

import java.util.Scanner;

public class GraphDef {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int v = sc.nextInt();
        int e = sc.nextInt();
        Graph g = new Graph(v,e);
        
        for(int i= 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.addEdge(a, b);
        }
        
       /* 
        7 9
        1 2
        1 3
        1 5
        2 5
        2 3
        3 4
        4 6
        4 7
        6 7
        */
        
        //广度优先遍历
        //g.breadthFirstSearch();
        //深度优先遍历
        g.depthFirstSearch(g.vertex[1]);
    }
}
