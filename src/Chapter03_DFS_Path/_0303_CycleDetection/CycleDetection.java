package Chapter03_DFS_Path._0303_CycleDetection;

public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];

        //要对所有的连通分量进行环检测
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])  //如果没有访问过
                if(dfs(v, v)){ //则进行深度搜索，如果深度搜索出来的是true，说明有环，则进入循环break
                    hasCycle = true;
                    break;
                }
    }

    //从顶点v开始，判断图中是否有环
    private boolean dfs(int v, int parent){

        visited[v] = true;

        for(int w: G.adj(v))
            if(!visited[w]){ //case1：如果w没有被访问过
                if(dfs(w, v)) //如果dfs返回true，则说明有环。因为dfs有环才会返回true，那么进入if选择语句return true提前结束
                    return true;
            }
            else if(w != parent) // case2：从v出发，找到了w，w还被访问过，并且w不是v的前一个节点
                return true; // 此时找到了环

        //其他的情况，找一圈没有找到环，返回false
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){

        Graph g = new Graph("g3.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("g4.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}