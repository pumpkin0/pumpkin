package datastructure.graph;
/**
 * 回溯法，其实是属于 图的深度优先 遍历。用于解决全排列的问题(按照某一确定的字典表 确定全排列的所有情况)
 * @author zhang
 *
 */
public class DFSPermutationGenerator {
    public int N;
    private boolean[] used;
    private int[] result;
    public DFSPermutationGenerator(int n) {
        N = n;
        used = new boolean[n+1];
        result = new int[n];
    }
    
    public static void main(String[] args) {
        DFSPermutationGenerator generator = new DFSPermutationGenerator(4);
        generator.make(0);
        
    }

    private void make(int level) {
        for(int i = 1; i <= N; i++) {
            if(!used[i]) {
                used[i] = true;
                result[level] = i;
                make(level + 1);
                used[i] = false;
            }
        }
        
        if(level == N -1) {
            for(int i = 0; i < N; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
    }
    
    
}
