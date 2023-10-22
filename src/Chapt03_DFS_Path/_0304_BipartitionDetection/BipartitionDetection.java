package Chapt03_DFS_Path._0304_BipartitionDetection;

public class BipartitionDetection {

    private Graph G;
    private boolean[] visited;

    // colors数组装的值是每个顶点的颜色
    private int[] colors; //二分图可以通过染色过程把顶点区分开，[-1:顶点还没染色] [0:一种颜色][1:另外一种颜色]
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];

        //颜色初始化为-1
        for(int i = 0; i < G.V(); i ++)
            colors[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v]) //如果没有被访问
                if(!dfs(v, 0)){ // 起始的时候把v统一染成0色，如果dfs返回的false，进入下面结构体，否则跳出执行v++
                    isBipartite = false; // 检测出错了，就设置成false
                    break; // 后续的循环就不需要进行了
                }
    }

    private boolean dfs(int v, int color){  //参数一：顶点   参数二：颜色
        visited[v] = true;
        colors[v] = color;

        //依次判断相邻顶点w的颜色
        for(int w: G.adj(v))
            if(!visited[w]){ //如果w没有被访问过，则进入判断
                if(!dfs(w, 1 - color)) //如果v的颜色是0，那么w的颜色应该是1。如果v的颜色是1，那么w的颜色应该是0.
                    return false; //如果相邻的两个顶点颜色一样，那么就不是二分图
            }
            else if(colors[w] == colors[v]) //如果相邻的两个顶点颜色一样，那么就不是二分图
                return false;

            return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){

        Graph g = new Graph("g4.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);
        // true

        Graph g2 = new Graph("g5.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);
        // false

        Graph g3 = new Graph("g6.txt");
        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
        System.out.println(bipartitionDetection3.isBipartite);
        // true
    }
}
