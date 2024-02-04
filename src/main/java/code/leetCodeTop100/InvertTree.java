package code.leetCodeTop100;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class InvertTree {

    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * <p>
     * 示例 1：
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * <p>
     * 示例 2：
     * 输入：root = [2,1,3]
     * 输出：[2,3,1]
     * <p>
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     * <p>
     * 提示：
     * 树中节点数目范围在 [0, 100] 内
     * -100 <= Node.val <= 100
     * <p>
     * 力扣：https://leetcode.cn/problems/invert-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
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
