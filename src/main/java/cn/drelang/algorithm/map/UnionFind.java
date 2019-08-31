package cn.drelang.algorithm.map;

/**
 * 并查集，解决问题
 *
 * Created by Drelang on 2019/8/31 10:42
 */

class UnionFind {
    int[] parents;

    UnionFind(int cap) {
        parents = new int[cap];
        for (int i=0; i<cap; i++) {
            parents[i] = i;
        }
    }

    // 查找元素的根节点
    int find(int node) {
        while (parents[node] != node) {
            // 查找的过程中压缩路径
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }

    void union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);
        if (p1 != p2) {
            // 两个根节点指向同一个根节点
            parents[p2] = p1;
        }
    }

    boolean isConnected(int node1, int node2) {
        return find(node1) == find(node2);
    }
}
