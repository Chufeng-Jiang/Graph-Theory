package Chapt01_Adjacency;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix {
    private int V;
    private int E;
    private int[][] adj;

    // 构造函数，从文件内容初始化邻接矩阵
    public AdjMatrix(String filename){
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt(); //读取第一行第一个数,代表图中的顶点数
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");

            adj = new int[V][V]; //构造邻接矩阵

            E = scanner.nextInt(); //读取第一行第二个数，代表图中边的数量
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            // E是边的数量，在g.txt中表示为第二行开始的E行
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt(); //读取边的一个顶点
                validateVertex(a);
                int b = scanner.nextInt(); //读取边的另一个顶点
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected"); //判断是够存在自环边
                if(adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected"); //判断是否存在平行l边

                adj[a][b] = 1; //存在边则设置为1
                adj[b][a] = 1;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex" + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
           if(adj[v][i] == 1) res.add(i);
        }
        return res;
    }

    public int degree(int v){
        return adj(v).size(); //adj(v)是上方的adj方法，size()是ArrayList的接口
    }

    // 用于在控制台打印该临接矩阵
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E)); //打印顶点数和边的数量

        for (int i = 0; i < V; i++) { //行
            for (int j = 0; j < V; j++) { //列
                sb.append(String.format("%d",adj[i][j])); //读取矩阵的值
            }
            sb.append('\n'); //行尾换行
        }
        return sb.toString(); //返回该邻接矩阵
    }

    public static void main(String[] args){
        AdjMatrix adjMatrix = new AdjMatrix("g1.txt"); //新建邻接矩阵，并从文件内容初始化
        System.out.println(adjMatrix);

    }
}
