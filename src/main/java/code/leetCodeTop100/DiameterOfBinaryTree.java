package code.leetCodeTop100;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class DiameterOfBinaryTree {

    /**
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     * <p>
     * 示例 1：
     * 输入：root = [1,2,3,4,5]
     * 输出：3
     * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
     * <p>
     * 示例 2：
     * 输入：root = [1,2]
     * 输出：1
     * <p>
     * 提示：
     * 树中节点数目在范围 [1, 10^4] 内
     * -100 <= Node.val <= 100
     * <p>
     * 力扣：https://leetcode.cn/problems/diameter-of-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(ans, left + right);
        return Math.max(left, right) + 1;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
