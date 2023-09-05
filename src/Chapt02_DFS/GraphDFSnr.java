package Chapt02_DFS;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDFSnr {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();


    public GraphDFSnr(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        while(!stack.empty()){
            int cur = stack.pop();
            pre.add(cur);
            for(int w: G.adj(v))
                if(!visited[w]){
                    stack.push(w);
                    visited[w] = true;
                }
        }
    }


    public Iterable<Integer> pre(){
        return pre;
    }



    public static void main(String[] args){

        Graph g1 = new AdjSet("g3.txt");
        GraphDFSnr graphDFS1 = new GraphDFSnr(g1);
        System.out.println("DFS preOrder : " + graphDFS1.pre());

        System.out.println();

        Graph g2 = new AdjList("g3.txt");
        GraphDFSnr graphDFS2 = new GraphDFSnr(g2);
        System.out.println("DFS preOrder : " + graphDFS2.pre());

        System.out.println();

        Graph g3 = new AdjMatrix("g3.txt");
        GraphDFSnr graphDFS3 = new GraphDFSnr(g3);
        System.out.println("DFS preOrder : " + graphDFS3.pre());

        System.out.println();
    }
}
