package Chapt02_DFS._0205_Graph_DFS_ConnectedComponentsCount;

import java.util.ArrayList;

public class CC {

    private Graph G;
    private int[] visited;
    private int cccount = 0;

    public CC(Graph G){

        this.G = G;
        visited = new int[G.V()];
        for(int i = 0; i < visited.length; i ++)
            visited[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(visited[v] == -1){
                dfs(v, cccount);
                cccount ++;
            }
    }

    private void dfs(int v, int ccid){

        visited[v] = ccid;
        for(int w: G.adj(v))
            if(visited[w] == -1)
                dfs(w, ccid);
    }

    public int count(){
        return cccount;
    }

// 判断两个顶点是否属于同一连通分量
    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }
    /************************************************************
    * ArrayList<Integer>[] arraylist1 = new ArrayList[3];
    * List<Integer>[] arraylist1 = new ArrayList[3];
    * 输出：[[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    ***********************************************************/
    public ArrayList<Integer>[] components(){ // 每个联通分量设置成一个ArrayList

        ArrayList<Integer>[] res = new ArrayList[cccount]; 
		
        for(int i = 0; i < cccount; i ++) // 如果有3个连通分量，就设置3个ArrayList
            res[i] = new ArrayList<Integer>();

        for(int v = 0; v < G.V(); v ++) // 填充每个连通分量的ArrayList
            res[visited[v]].add(v); // visited[v]的取值只有0、1.2.3等，是组名，表示是哪个连通分量
			
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g3.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
		
        for(int ccid = 0; ccid < comp.length; ccid ++){
            System.out.print(ccid + " : ");
            for(int w: comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
