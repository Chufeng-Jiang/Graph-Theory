package Chapter02_DFS._0203_Graph_DFS_UsingInterface_Combiation;

import java.util.ArrayList;

public class GraphDFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){
        // 左父右子
        Graph g1 = new AdjSet("g3.txt");
        GraphDFS graphDFS1 = new GraphDFS(g1);
        System.out.println("AdjSet DFS preOrder : " + graphDFS1.pre());
        System.out.println("AdjSet DFS postOrder : " + graphDFS1.post());
        System.out.println();

        Graph g2 = new AdjList("g3.txt");
        GraphDFS graphDFS2 = new GraphDFS(g2);
        System.out.println("AdjList DFS preOrder : " + graphDFS2.pre());
        System.out.println("AdjList DFS postOrder : " + graphDFS2.post());
        System.out.println();

        Graph g3 = new AdjMatrix("g3.txt");
        GraphDFS graphDFS3 = new GraphDFS(g3);
        System.out.println("AdjMatrix DFS preOrder : " + graphDFS3.pre());
        System.out.println("AdjMatrix DFS postOrder : " + graphDFS3.post());
        System.out.println();
    }
}
