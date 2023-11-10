package Chapt06_Bridge_And_CutPoints;

import java.util.HashSet;

public class FindCutPoints {

    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph G){

        this.G = G;
        visited = new boolean[G.V()];

        res = new HashSet<>();
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
        low[v] = ord[v];
        cnt ++;

        // 记录子节点的数量
        int child = 0;

        for(int w: G.adj(v))
            if(!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                //割点的判断
                if(v != parent && low[w] >= ord[v])
                    res.add(v);

                child ++;
                if(v == parent && child > 1)
                    res.add(v);

               // if(v == parent && child = 1) -- 单环肯定不是割点
            }
            else if(w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public HashSet<Integer> result(){
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g8.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println("Cut Points in g8 : " + fc.result());

        Graph g2 = new Graph("g2.txt");
        FindCutPoints fc2 = new FindCutPoints(g2);
        System.out.println("Cut Points in g2 : " + fc2.result());

        Graph tree = new Graph("tree.txt");
        FindCutPoints fc3 = new FindCutPoints(tree);
        System.out.println("Cut Points in tree : " + fc3.result());
    }
}
