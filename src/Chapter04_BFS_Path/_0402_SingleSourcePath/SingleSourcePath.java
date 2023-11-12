package Chapter04_BFS_Path._0402_SingleSourcePath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SingleSourcePath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];

        for(int i = 0; i < pre.length; i ++)
            pre[i] = -1;

        bfs(s);
    }

    private void bfs(int s){ // 区分一下DFS两个参数，DFS实际上是递归，把参数传进去就开始递归了。而BFS实际上是使用队列进行模拟，只需要传入源就可以，两个参数也可以但是没必要
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s; //赋初值，源的源是源

        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v; //w的上一个顶点是v
                }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
    }
}
