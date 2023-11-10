package Chapter07_HamiltonLoop_And_StateCompression_And_MemoizationSearch.Hamilton_Loop;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop_Optimization {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop_Optimization(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;

        //dfs的过程记录剩余未访问的节点的数量
        dfs(0, 0, G.V());
    }

    private boolean dfs(int v, int parent, int left){
        visited[v] = true;
        pre[v] = parent;
        left --;

        //出口：如果未访问的节点是0，并且当前节点和初始节点有边
        if(left == 0 && G.hasEdge(v, 0)){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v, left)) return true;
            }
//            else if(w == 0 && left == 0){
//                end = v;
//                return true;
//            }

        visited[v] = false;
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

        Graph g = new Graph("g9.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("g10.txt");
        HamiltonLoop hl2 = new HamiltonLoop(g2);
        System.out.println(hl2.result());
    }
}
