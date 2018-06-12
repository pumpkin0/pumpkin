package datastructure.graph;

import java.util.LinkedList;

public class Graph {
    int v;
    int e;
    ListHead[] vertex;
    
    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        vertex = new ListHead[v+1];  //vertex:顶点
        
        for(int i = 1; i <= v; i++) {
            //这步操作，使得listHead中的data值为 顶点的编号
            vertex[i] = new ListHead(i);
        }
    }
    
    
    public void addEdge(int a, int b) {
        //使得a b 两个顶点互相 连接
        vertex[a].linkTo(b);
        vertex[b].linkTo(a);
    }
    
    
    public void breadthFirstSearch() {
        LinkedList<ListHead> q = new LinkedList<>();
        q.add(vertex[1]);
        vertex[1].visited = true;
        
        while(!q.isEmpty()) {
            ListHead tmp = q.remove(0);
            System.out.println(tmp.data);
            
            //根据某个顶点，开始遍历其相连的顶点
            AdjacentListNode node = tmp.firstArc;
            
            while(node != null) {
                tmp = vertex[node.nodeIndex];
                if(!tmp.visited) {
                    q.add(tmp);
                    tmp.visited = true;
                }
                
                node = node.nextArc;
            }
        }
    }
    
    
    public void depthFirstSearch(ListHead v) {
        v.visited = true;
        System.out.println(v.data);
        AdjacentListNode node = v.firstArc;
        
        while(node != null) {
            //如果该顶点已经被访问过，跳过该顶点
            if(vertex[node.nodeIndex].visited) {
                node = node.nextArc;
                //跳过本次循环 的目的：防止nextArc也已经被访问过
                continue;
            }
            
            depthFirstSearch(vertex[node.nodeIndex]);
            node = node.nextArc;
        }
    }
    
}
/**
 * 表示相邻的节点
 * @author zhang
 *
 */
class AdjacentListNode{
    public int nodeIndex; //相邻节点的索引
    public int info; //权重
    
    public AdjacentListNode nextArc; //两节点之间的边,用着条边指向 相邻的节点

    public AdjacentListNode(int nodeIndex) {
        this.nodeIndex = nodeIndex;
        nextArc = null;
    }
}

class ListHead{
    int data;  //data为顶点的 数据域
    AdjacentListNode firstArc;  //表示该顶点的第一个相邻的顶点
    public boolean visited;  //表示该顶点是否被 遍历过
    public ListHead(int data) {
        this.data = data;
    }
    
    //连接两个顶点。所有与该顶点相邻的顶点 都放在了 AdjacentListNode这个单链表的数据结构中
    public void linkTo(int end) {
        if(firstArc == null) {
            firstArc = new AdjacentListNode(end);
        }
        
        AdjacentListNode n = firstArc;
        
        while(n.nextArc != null) {
            n = n.nextArc;
        }
        
        n.nextArc = new AdjacentListNode(end);
    }
    
   
    
}




