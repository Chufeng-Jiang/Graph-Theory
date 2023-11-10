package Chapter07_HamiltonLoop_And_StateCompression_And_MemoizationSearch.State_Compression;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop_StateCompression {

    private Graph G;
    private int[] pre;
    private int end;

    public HamiltonLoop_StateCompression(Graph G){

        this.G = G;
        pre = new int[G.V()];
        end = -1;

        int visited = 0; //用一个数的比特位来表示是否被访问过
        dfs(visited, 0, 0, G.V());
    }

    private boolean dfs(int visited, int v, int parent, int left){

        visited += (1 << v); //第v位置设置为1
        pre[v] = parent;
        left --;
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if((visited & (1 << w)) == 0){ //看数字的第w个位置是否为 0
                if(dfs(visited, w, v, left)) return true;
            }

        visited -= (1 << v); //第v位置恢复为0
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g8.txt");
        HamiltonLoop_StateCompression hl = new HamiltonLoop_StateCompression(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("g3.txt");
        HamiltonLoop_StateCompression hl2 = new HamiltonLoop_StateCompression(g2);
        System.out.println(hl2.result());
    }
}
