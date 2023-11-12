package Chapter03_DFS_Path._0302_AllPairsPath._01_UsingSingleSourcePath;

import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre; // 当前索引是点，存储的值是上一个顶点

    //构造函数把visited数组和pre数组填充好，后面求是否连通和路径就直接使用填充好的visited和pre数组
    public SingleSourcePath(Graph G, int s){ //单源路径，要把源s传进来，而且只考虑与s连通的顶点，不连通的不考虑

        G.validateVertex(s);

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        dfs(s, s);
    }

    private void dfs(int v, int parent){ //参数一：当前顶点； 参数二：上一个顶点

        visited[v] = true;
        pre[v] = parent;

        for(int w: G.adj(v)) //跟v相邻的所有顶点，相当于v是源，遍历与当前顶点相邻的所有点
            if(!visited[w])
                dfs(w, v); //（顶点，源）
    }
///////////////////////////////////////////////////////////////////////////////
    public boolean isConnectedTo(int t){ // 从源到t是否可达
        G.validateVertex(t);
        return visited[t]; //如果能visited数组里是true，那么就是连通的。
    }
//////////////////////////////////////////////////////////////////////////////
    public Iterable<Integer> path(int t){ //从源到t的路径

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;

        int cur = t; // 从t往回找
        while(cur != s){
            res.add(cur); //添加当前节点（循环内不包含源）
            cur = pre[cur]; //pre[cur]的值是cur的上一个节点
        }
        res.add(s); //添加源

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g3.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
        System.out.println("0 -> 5 : " + sspath.path(5));
    }
}
