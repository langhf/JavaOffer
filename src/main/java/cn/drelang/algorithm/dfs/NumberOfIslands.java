package cn.drelang.algorithm.dfs;

/**
 * 岛屿的数量 leetcode-200
 *
 * Created by Drelang on 2019/8/31 15:56
 */

class NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0'},
                {'1', '1', '0', '0'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '1'}
        };
        NumberOfIslands islands = new NumberOfIslands();
        System.out.println(islands.numIslands(grid));
    }

    int m, n, Island;
    UnionFind un;

    public int numIslands(char[][] grid) {
        if (grid == null || (m = grid.length) < 1 || (n = grid[0].length) < 1) {
            return 0;
        }

        un = new UnionFind(m*n+1);
        Island = m * n;

        int res = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == '1' && !un.isConnected(node(i, j), Island)) { // 当前节点没有连到岛屿区域中
                    res++;
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        un.union(node(i, j), Island);
        if (i-1 >= 0 && grid[i-1][j] == '1' && !un.isConnected(node(i-1, j), Island)) dfs(grid, i-1, j);
        if (i+1 < m && grid[i+1][j] == '1' && !un.isConnected(node(i+1, j), Island)) dfs(grid, i+1, j);
        if (j-1 >= 0 && grid[i][j-1] == '1' && !un.isConnected(node(i, j-1), Island)) dfs(grid, i, j-1);
        if (j+1 < n && grid[i][j+1] == '1' && !un.isConnected(node(i, j+1), Island)) dfs(grid, i, j+1);
    }

    private int node(int i, int j) {
        return i * n + j;
    }
}

class UnionFind {
    int[] parents;

    UnionFind(int cap) {
        parents = new int[cap];
        for (int i=0; i<cap; i++) {
            parents[i] = i;
        }
    }

    int find(int node) {
        while (parents[node] != node) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }

    void union(int m, int n) {
        int p1 = find(m);
        int p2 = find(n);
        if (p1 != p2) {
            parents[p1] = p2;
        }
    }

    boolean isConnected(int m, int n) {
        return find(m) == find(n);
    }
}
