package code.trieTree;

/**
 * @author: wxm
 * @created: 2023/06/28
 */
public class MaximumXOR {

    /**
     * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     *
     * 示例 1：
     * 输入：nums = [3,10,5,25,2,8]
     * 输出：28
     * 解释：最大运算结果是 5 XOR 25 = 28.
     *
     * 示例 2：
     * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
     * 输出：127
     *
     * 提示：
     * 1 <= nums.length <= 2 * 10^5
     * 0 <= nums[i] <= 2^31 - 1
     *
     * 力扣：https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array/
     */

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        TrieTreeNum treeNum = new TrieTreeNum();
        treeNum.insert(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, treeNum.maxXor(nums[i]));
            treeNum.insert(nums[i]);
        }
        return max;
    }


    /**
     * 前缀树
     */
    public static class TrieTreeNum {
        public Node root;

        public TrieTreeNum() {
            this.root = new Node();
        }

        /**
         * 添加
         *
         * @param num
         */
        public void insert(int num) {
            Node node = root;
            for (int i = 31; i >= 0; i--) {
                // 取出num的第i位是0还是1
                int path = ((num >> i) & 1);
                node.next[path] = node.next[path] == null ? new Node() : node.next[path];
                node = node.next[path];
            }
        }

        /**
         * 给定一个数字，在构建好的前缀树中num和谁异或和最大，返回最大结果
         *
         * @param num
         * @return
         */
        public int maxXor(int num) {
            Node node = root;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                // 期望遇到的结果，31位为符号位不变   path ^ 1 => 取反
                int best = i == 31 ? path : (path ^ 1);
                // 实际遇到的结果，可能期望的结果不存在
                best = node.next[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位异或完的结果,或到结果中去
                ans |= ((path ^ best) << i);
                node = node.next[best];
            }
            return ans;
        }
    }

    public static class Node {
        public Node[] next;

        public Node() {
            this.next = new Node[2];
        }
    }
}
