package Chapter02_DFS._0202_Graph_DFS_PreOrderAndPostOrder_AdjMatrix;

import java.util.ArrayList;

public class AdjMatrixDFS {

    private AdjMatrix G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();   // 先序遍历：从前往后遍历顶点
    private ArrayList<Integer> post = new ArrayList<>();  // 后续遍历：从后往前遍历顶点

    public AdjMatrixDFS(AdjMatrix G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);          // 先序遍历：先加入容器，再进行递归
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);         // 后序遍历：先进行递归，再加入容器
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){

        AdjMatrix g = new AdjMatrix("g3.txt");
        AdjMatrixDFS graphDFS = new AdjMatrixDFS(g);
        System.out.println("DFS preOrder : " + graphDFS.pre());
        System.out.println("DFS postOrder : " + graphDFS.post());
    }
}
