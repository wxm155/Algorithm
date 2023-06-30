package code.trieTree;

/**
 * @author: wxm
 * @created: 2023/06/30
 */
public class MaximizeXor {

    /**
     * 给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
     * 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR xi) ，
     * 其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
     * 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个查询的答案。
     * <p>
     * 示例 1：
     * 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
     * 输出：[3,3,7]
     * 解释：
     * 1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
     * 2) 1 XOR 2 = 3.
     * 3) 5 XOR 2 = 7.
     * <p>
     * 示例 2：
     * 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
     * 输出：[15,-1,5]
     * <p>
     * 提示：
     * 1 <= nums.length, queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= nums[j], xi, mi <= 10^9
     * <p>
     * 力扣：https://leetcode.cn/problems/maximum-xor-with-an-element-from-array/
     */


    public int[] maximizeXor(int[] nums, int[][] queries) {
        int[] res = new int[queries.length];
        TrieTreeNum treeNum = new TrieTreeNum();
        // 将所有数添加进前缀树
        for (int i = 0; i < nums.length; i++) {
            treeNum.insert(nums[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            res[i] = treeNum.maxXorWithXBehindM(queries[i][0], queries[i][1]);
        }
        return res;
    }

    /**
     * 前缀树
     */
    public static class TrieTreeNum {

        private Node root = new Node();

        /**
         * 添加数进前缀树
         *
         * @param num 添加的数
         */
        public void insert(int num) {
            Node node = root;
            node.min = Math.min(root.min, num);
            for (int i = 30; i >= 0; i--) {
                int path = ((num >> i) & 1);
                node.next[path] = node.next[path] == null ? new Node() : node.next[path];
                node = node.next[path];
                node.min = Math.min(node.min, num);
            }
        }

        /**
         * 在前缀树结构中，获取小于m并且和x异或值最大的结果
         *
         * @param x 异或的数
         * @param m 小于的数
         * @return 结果
         */
        public int maxXorWithXBehindM(int x, int m) {
            if (root.min > m) {
                return -1;
            }
            Node node = root;
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int path = ((x >> i) & 1);
                // 期望遇到的值
                int best = path ^ 1;
                // 实际遇到的值
                best = (node.next[best] == null || node.next[best].min > m) ? best ^ 1 : best;
                // 将i位的异或结果或进结果中
                ans |= ((path ^ best) << i);
                node = node.next[best];
            }
            return ans;
        }
    }


    public static class Node {
        public int min;
        public Node[] next;

        public Node() {
            this.min = Integer.MAX_VALUE;
            this.next = new Node[2];
        }
    }
}
