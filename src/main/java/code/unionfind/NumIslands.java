package code.unionfind;

/**
 * @author: wxm
 * @created: 2022/07/14
 */
public class NumIslands {

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * 示例 1：
     * 输入：grid = [
     *   ["1","1","1","1","0"],
     *   ["1","1","0","1","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","0","0","0"]
     * ]
     * 输出：1
     *
     * 示例 2：
     * 输入：grid = [
     *   ["1","1","0","0","0"],
     *   ["1","1","0","0","0"],
     *   ["0","0","1","0","0"],
     *   ["0","0","0","1","1"]
     * ]
     * 输出：3
     *
     * 力扣：https://leetcode.cn/problems/number-of-islands/
     */

    public int numIslands(char[][] grid) {
        int res = 0;
        int length = grid.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    infect(grid, i, j);
                }
            }
        }
        return res;
    }

    // 存在一个1，将1的上下左右全部改成0
    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 0;
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }


    /**
     * 并查集解法：只须判断节点的左边和上边是否需要合并
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {

        int len = grid.length;
        int col = grid[0].length;
        UnionFind unionFind = new UnionFind(grid);
        // 左边界处理
        for (int i = 1; i < len; i++) {
            if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
                unionFind.union(i - 1, 0, i, 0);
            }
        }
        // 上边界处理
        for (int i = 1; i < col; i++) {
            if (grid[0][i - 1] == '1' && grid[0][i] == '1') {
                unionFind.union(0, i - 1, 0, i);
            }
        }
        // 剩余位置
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < col; j++) {
                if (grid[i - 1][j] == '1' && grid[i][j] == '1') {
                    unionFind.union(i - 1, j, i, j);
                }
                if (grid[i][j - 1] == '1' && grid[i][j] == '1') {
                    unionFind.union(i, j - 1, i, j);
                }
            }
        }
        return unionFind.sets;

    }

    /**
     * 并查集
     */
    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        private int col;

        public UnionFind(char[][] grid) {
            int len = grid.length;
            col = grid[0].length;
            int num = len * col;
            parent = new int[num];
            size = new int[num];
            help = new int[num];
            sets = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = index(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }
        // 计算二维数组中坐标对应一维数组位置
        public int index(int i, int j) {
            return col * i + j;
        }

        public int find(int i) {
            int temp = 0;
            while (i != parent[i]) {
                help[temp++] = i;
                i = parent[i];
            }
            // 路径压缩
            for (int j = 0; j < temp; j++) {
                parent[help[j]] = i;
            }
            return i;
        }

        public void union(int i1, int j1, int i2, int j2) {
            int r1 = index(i1, j1);
            int r2 = index(i2, j2);
            int f1 = find(r1);
            int f2 = find(r2);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }
    }
}
