package Chapter02_DFS._0201_Graph_DFS_ArrayList;

import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];

        /***********************************************************
         * 如果图中存在多个联通分量，使用循环进行DFS
         * 每一轮visited的点属于同一个联通分量，后面会有优化区分各个联通分量
         ***********************************************************/
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        order.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args){

        Graph g = new Graph("g3.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.order());
    }
}
