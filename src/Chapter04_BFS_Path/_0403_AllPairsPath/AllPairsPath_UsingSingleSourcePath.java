package Chapter04_BFS_Path._0403_AllPairsPath;

public class AllPairsPath_UsingSingleSourcePath {

    private Graph G;
    private SingleSourcePath[] paths;

    public AllPairsPath_UsingSingleSourcePath(Graph G){

        this.G = G;

        paths = new SingleSourcePath[G.V()];
        for(int v = 0; v < G.V(); v ++)
            paths[v] = new SingleSourcePath(G, v);
    }

    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t){
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
