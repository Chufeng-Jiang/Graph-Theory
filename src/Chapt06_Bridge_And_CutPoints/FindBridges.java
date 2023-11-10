package Chapt06_Bridge_And_CutPoints;

import java.util.ArrayList;

public class FindBridges {

    private Graph G;
    private boolean[] visited;

    //ord数组记录访问的顺序
    private int ord[];

    //low数组记录该顶点可以访问到的ord[值]最小的[顶点]
    private int low[];

    //cnt用来记录步数，给order赋值
    private int cnt;

    // Edge类型的动态数组,记录图中的桥
    private ArrayList<Edge> res;

    public FindBridges(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        res = new ArrayList<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        ord[v] = cnt;

        // 初始的时候，low的值就是访问的顺序值，在递归return的时候才进行更新
        low[v] = ord[v];

        cnt ++;

        // 通过邻接表，挨个查找相邻的节点
        for(int w: G.adj(v))
            //如果有相邻的节点还没有被访问过，就dfs
            if(!visited[w]){
                dfs(w, v);

                // 对上一个节点的low值进行更新
                low[v] = Math.min(low[v], low[w]);

                // 如果子节点的low值比父节点的ord大，说明两点之间是一座桥。要区分于割点的判断。
                // 因为如果都在同一个环内，low值一定是父节点之前的节点，数字会更小，那么就不是桥，桥是不可回头的。
                if(low[w] > ord[v])
                    res.add(new Edge(v, w));
            }
            else if(w != parent) //如果该点访问过，不继续dfs，只更新low值
                low[v] = Math.min(low[v], low[w]);
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g2.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println("Bridges in g2 : " + fb.result());

        Graph g2 = new Graph("g8.txt");
        FindBridges fb2 = new FindBridges(g2);
        System.out.println("Bridges in g8 : " + fb2.result());

        Graph g3 = new Graph("g3.txt");
        FindBridges fb3 = new FindBridges(g3);
        System.out.println("Bridges in g3 : " + fb3.result());

        Graph tree = new Graph("tree.txt");
        FindBridges fb_tree = new FindBridges(tree);
        System.out.println("Bridges in tree : " + fb_tree.result());
    }
}
