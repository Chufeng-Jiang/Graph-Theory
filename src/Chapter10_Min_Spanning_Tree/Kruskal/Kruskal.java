package Chapter10_Min_Spanning_Tree.Kruskal;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    private WeightedGraph G;
    private ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G){

        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if(cc.count() > 1) return;

        // 获得图中所有的边
        ArrayList<WeightedEdge> edges = new ArrayList<>();

        for(int v = 0; v < G.V(); v ++)
            for(int w: G.adj(v))
                if(v < w) // 剪枝：0-2,2-0，只判断0-2，避免重复
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));

        Collections.sort(edges);

        UF uf = new UF(G.V());
        for(WeightedEdge edge: edges){
            int v = edge.getV();
            int w = edge.getW();

            // 判断选择的边的两个顶点是否属相连
            if(!uf.isConnected(v, w)){
                mst.add(edge);
                uf.unionElements(v, w); // 合并两个顶点和边
            }
        }
    }

    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args){

        WeightedGraph g = new WeightedGraph("gw1.txt");
        Kruskal kruskal = new Kruskal(g);
        System.out.println(kruskal.result());
    }
}
