package code.unionfind;

/**
 * @author: wxm
 * @created: 2022/07/13
 */
public class FindCircleNum {

    /**
     * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
     * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
     * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
     * 返回矩阵中 省份 的数量。
     *
     * 示例 1：
     * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
     * 输出：2
     *
     * 示例 2：
     * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
     * 输出：3
     *
     * 力扣：https://leetcode.cn/problems/number-of-provinces/
     */

    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        UnionFind unionFind = new UnionFind(length);
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (isConnected[i][j] == 1){
                    unionFind.union(i,j);
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

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int num) {
            int temp = 0;
            while (num != parent[num]) {
                help[temp++] = num;
                num = parent[num];
            }
            // 路径压缩
            for (int i = 0; i < temp; i++) {
                parent[help[i]] = num;
            }
            return num;
        }

        public void union(int a, int b) {
            int aFather = find(a);
            int bFather = find(b);
            if (aFather != bFather) {
                if (size[aFather] > size[bFather]) {
                    size[aFather] += size[bFather];
                    parent[bFather] = aFather;
                } else {
                    size[bFather] += size[aFather];
                    parent[aFather] = bFather;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }
}
