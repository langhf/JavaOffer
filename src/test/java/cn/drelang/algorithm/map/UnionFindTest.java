package cn.drelang.algorithm.map;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Drelang on 2019/8/31 10:44
 */
public class UnionFindTest {
    @Test
    public void testUnion() {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(board);
        // expected: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        System.out.println(Arrays.deepToString(board));
    }

    int m, n;

    void solve(char[][] board) {
        // 用并查集的思想
        if (board == null || (m = board.length) < 1 || (n = board.length) < 1) {
            return ;
        }
        UnionFind union = new UnionFind(m*n+1);
        // 边界的 O 指向虚拟根节点
        int dummyRoot = m*n;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 'O') {
                    boolean isEdge = (i == 0 || i == m-1 || j == 0 || j == n-1);
                    if (isEdge && board[i][j] == 'O') {
                        union.union(dummyRoot, node(i, j));
                    }
                    // 连通四周的 O
                    if (i-1>=0 && board[i-1][j] == 'O') union.union(node(i,j) , node(i-1,j));
                    if (i+1<m && board[i+1][j] == 'O') union.union(node(i,j), node(i+1,j));
                    if (j-1>=0 && board[i][j-1] == 'O') union.union(node(i,j), node(i, j-1));
                    if (j+1<n && board[i][j+1] == 'O') union.union(node(i, j), node(i, j+1));
                }
            }
        }

        // 根节点指向 dummyRoot 的，不需要动
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 'O' && !union.isConnected(node(i, j), dummyRoot)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int node(int i, int j) {
        return i*n + j;
    }
}