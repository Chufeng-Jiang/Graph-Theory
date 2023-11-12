package Chapter05_Path_Union_Find;

/// Leetcode 695
/// Using Union-Find
public class Union_Find {

    class UF {
        private int[] parent; //parent数组中保存着每个节点所指向的父节点的索引
        private int[] sz;

        public UF(int n) {
            parent = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; //初始化的时候当前节点的父节点都是自己
                sz[i] = 1; //当前所属集合的大小
            }
        }
        // 不断去查询自己的父亲节点, 直到到达根节点
        // 根节点的特点: parent[p] == p
        public int find(int p) {
            if (p != parent[p]) //还没找到根节点
                parent[p] = find(parent[p]); //递归实现
            return parent[p]; //终于找到根节点
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void unionElements(int p, int q) {

            int pRoot = find(p); //找到p的父节点
            int qRoot = find(q); //找到q的父节点

            if (pRoot == qRoot)//如果pq的父节点相同，说明在同一个集合内
                return;

            parent[pRoot] = qRoot; //如果不相同，将p的父节点挂到q的父节点下，进行合并
            sz[qRoot] += sz[pRoot]; //q的集合大小合并
        }

        public int size(int p) {
            return sz[find(p)];
        }
    }

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null) return 0;

        R = grid.length;
        if (R == 0) return 0;

        C = grid[0].length;
        if (C == 0) return 0;

        UF uf = new UF(R * C);
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1)
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        uf.unionElements(v, next);
                    }
                }
        }

        int res = 0;
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1)
                res = Math.max(res, uf.size(v)); //遍历找到最大的size
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
