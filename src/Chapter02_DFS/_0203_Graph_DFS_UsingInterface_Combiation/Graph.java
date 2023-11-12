package Chapter02_DFS._0203_Graph_DFS_UsingInterface_Combiation;
public interface Graph {

    int V();
    int E();
    boolean hasEdge(int v, int w);
    Iterable<Integer> adj(int v);
    int degree(int v);
}
