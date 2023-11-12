package Chapter04_BFS_Path._0405_CycleDetection;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        for(int i = 0; i < G.V(); i ++)
            pre[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(bfs(v)){
                    hasCycle = true;
                    break;
                }
    }

    // 从顶点 v 开始，判断图中是否有环
    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){ //如果w没有访问过
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
                else if(pre[v] != w) //从s出发，如果w被访问过，并且顶点v的上一个不是w
                    return true;
        }
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}