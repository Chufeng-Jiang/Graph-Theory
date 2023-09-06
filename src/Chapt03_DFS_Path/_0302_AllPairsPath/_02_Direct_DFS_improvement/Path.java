package Chapt03_DFS_Path._0302_AllPairsPath._02_Direct_DFS_improvement;

import java.util.ArrayList;
import java.util.Collections;

public class Path {

    private Graph G;
    private int s, t;

    private int[] pre;
    private boolean[] visited;

    public Path(Graph G, int s, int t){

        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        pre = new int[G.V()];

        for(int i = 0; i < pre.length; i ++)
            pre[i] = -1;

        dfs(s, s);

        //打印visited数组，查看访问过的顶点
        for(boolean e: visited)
            System.out.print(e + " ");
        System.out.println();
    }

    // 私有方法，返回值是boolean. 这种设计是有语义的，可以接收一个返回值，提前结束递归
    // 判断可达：求的是v到t是否可达，t在外层Path方法中已经传入
    // 判断环的逻辑与其类似
    private boolean dfs(int v, int parent){

        visited[v] = true;
        pre[v] = parent;

        if(v == t) return true; //程序出口，当到达t顶点时，返回true提前结束递归，而不仅仅是返回return

        for(int w: G.adj(v)) //遍历与v相邻的顶点
            if(!visited[w]) //如果相邻的顶点没有被访问过
                if(dfs(w, v)) //递归遍历相邻的顶点，如果到达 v==t，则值为true
                    return true; //提前返回true

        return false; // 转一圈没法达到t，就可以返回false
    }

    public boolean isConnected(){ //不用传参了
        return visited[t];
    }

    public Iterable<Integer> path(){

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnected()) return res;

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

        Graph g = new Graph("g3.txt");

        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }
}
