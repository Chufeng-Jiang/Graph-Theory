package Chapter01_Adjacency;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class AdjSet {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    public AdjSet(String filename){
        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet[V]; //构造邻接表, V行，V个LinkedList
            for (int i = 0; i < V; i++) {
               adj[i] = new TreeSet<Integer>();
            }

            E = scanner.nextInt(); //读取第一行第二个数
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            // E是边的数量，在g.txt中表示为第二行开始的E行
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt(); //读取边的一个顶点
                validateVertex(a);
                int b = scanner.nextInt(); //读取边的另一个顶点
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected"); //判断是够存在自环边
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected"); //判断是够存在平行l边

                adj[a].add(b);
                adj[b].add(a);
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
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v){ // 可以是TreeSet，但是数组、链表、红黑树都是实现了Iterable的接口，因此可以统一写成这样
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size(); // Iterable没有size()方法
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));

        for (int i = 0; i < V; i++) {
            sb.append(String.format("%d:",i));
            for (int w: adj[i]) {
                sb.append(String.format("%d ",w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        AdjSet adjSet = new AdjSet("g1.txt"); //新建邻接矩阵，并从文件内容初始化
        System.out.println(adjSet);
    }
}
