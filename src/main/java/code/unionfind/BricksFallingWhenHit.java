package code.unionfind;

/**
 * @author: wxm
 * @created: 2023/08/18
 */
public class BricksFallingWhenHit {

    /**
     * 有一个 m x n 的二元网格 grid ，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
     * 一块砖直接连接到网格的顶部，或者至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
     * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，
     * 对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落 。
     * 一旦砖块掉落，它会立即从网格grid中消失（即，它不会落在其他稳定的砖块上）。
     * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
     * <p>
     * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
     * <p>
     * 示例 1：
     * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
     * 输出：[2]
     * 解释：网格开始为：
     * [[1,0,0,0]，
     * [1,1,1,0]]
     * 消除 (1,0) 处加粗的砖块，得到网格：
     * [[1,0,0,0]
     * [0,1,1,0]]
     * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
     * [[1,0,0,0],
     * [0,0,0,0]]
     * 因此，结果为 [2] 。
     * <p>
     * 示例 2：
     * 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
     * 输出：[0,0]
     * 解释：网格开始为：
     * [[1,0,0,0],
     * [1,1,0,0]]
     * 消除 (1,1) 处加粗的砖块，得到网格：
     * [[1,0,0,0],
     * [1,0,0,0]]
     * 剩下的砖都很稳定，所以不会掉落。网格保持不变：
     * [[1,0,0,0],
     * [1,0,0,0]]
     * 接下来消除 (1,0) 处加粗的砖块，得到网格：
     * [[1,0,0,0],
     * [0,0,0,0]]
     * 剩下的砖块仍然是稳定的，所以不会有砖块掉落。
     * 因此，结果为 [0,0] 。
     * <p>
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * grid[i][j] 为 0 或 1
     * 1 <= hits.length <= 4 * 104
     * hits[i].length == 2
     * 0 <= xi <= m - 1
     * 0 <= yi <= n - 1
     * 所有 (xi, yi) 互不相同
     * <p>
     * 力扣：https://leetcode.cn/problems/bricks-falling-when-hit/
     */

    // 依次将炮弹打到的位置变成2，炮弹逆序将网格还原，还原前后的天花板上的砖块数量差即掉落的砖块数
    public int[] hitBricks(int[][] grid, int[][] hits) {
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                grid[hits[i][0]][hits[i][1]] = 2;
            }
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] ans = new int[hits.length];
        // 逆序遍历，后面的炮弹已经在前面的炮弹变化基础上
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }


    private static class UnionFind {
        // 矩阵行的长度
        private int N;
        // 矩阵列的高度
        private int M;
        // 连到天花板砖块数量
        private int cellingAll;
        // cellingSet[i] = true; => i是头节点，所在的集合是天花板集合
        private boolean[] cellingSet;
        // 原始矩阵，炮弹的影响1 -> 2
        private int[][] grid;
        // 父节点map
        private int[] fatherMap;
        // 集合大小map
        private int[] sizeMap;
        // 栈，用于路径压缩
        private int[] stack;

        public UnionFind(int[][] grid) {
            this.initSpace(grid);
            this.initConnect();
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (valid(r1, c1) && valid(r2, c2)) {
                int f1 = findFather(r1, c1);
                int f2 = findFather(r2, c2);
                if (f1 != f2) {
                    int size1 = sizeMap[f1];
                    int size2 = sizeMap[f2];
                    boolean status1 = cellingSet[f1];
                    boolean status2 = cellingSet[f2];
                    // 小集合合并到大集合中，整体高度保持平均
                    if (size1 <= size2) {
                        fatherMap[f1] = f2;
                        sizeMap[f2] = size1 + size2;
                        // 其中一个为天花板集合，合并
                        if (status1 ^ status2) {
                            cellingSet[f2] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    } else {
                        fatherMap[f2] = f1;
                        sizeMap[f1] = size1 + size2;
                        if (status1 ^ status2) {
                            cellingSet[f1] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    }
                }
            }
        }

        public int findFather(int r1, int c1) {
            // 将二维矩阵压缩成一维数组
            int index = r1 * M + c1;
            int temp = 0;
            while (index != fatherMap[index]) {
                stack[temp++] = index;
                index = fatherMap[index];
            }
            // 路径压缩
            while (temp != 0) {
                fatherMap[stack[--temp]] = index;
            }
            return index;
        }

        public int finger(int r1, int c1) {
            // 将2还原成1
            grid[r1][c1] = 1;
            int index = r1 * M + c1;
            if (r1 == 0) {
                cellingSet[index] = true;
                cellingAll++;
            }
            fatherMap[index] = index;
            sizeMap[index] = 1;
            int pre = cellingAll;
            union(r1, c1, r1 + 1, c1);
            union(r1, c1, r1 - 1, c1);
            union(r1, c1, r1, c1 + 1);
            union(r1, c1, r1, c1 - 1);
            int now = cellingAll;
            // 在合并前，天花板的砖块加1，此时不用再减1
            if (r1 == 0) {
                return now - pre;
            } else {
                return now == pre ? 0 : now - pre - 1;
            }
        }

        private void initSpace(int[][] grid) {
            this.grid = grid;
            this.N = grid.length;
            this.M = grid[0].length;
            this.cellingAll = 0;
            int all = N * M;
            this.cellingSet = new boolean[all];
            this.fatherMap = new int[all];
            this.sizeMap = new int[all];
            this.stack = new int[all];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1) {
                        int index = i * M + j;
                        fatherMap[index] = index;
                        sizeMap[index] = 1;
                        if (i == 0) {
                            cellingSet[index] = true;
                            cellingAll++;
                        }
                    }
                }
            }
        }

        private void initConnect() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    union(i, j, i - 1, j);
                    union(i, j, i + 1, j);
                    union(i, j, i, j - 1);
                    union(i, j, i, j + 1);
                }
            }
        }

        // 边界校验，砖块有效性校验
        private boolean valid(int r1, int c1) {
            return r1 >= 0 && r1 < N && c1 >= 0 && c1 < M && grid[r1][c1] == 1;
        }
    }
}
