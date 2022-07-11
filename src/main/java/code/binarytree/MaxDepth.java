package code.binarytree;

/**
 * @author: wxm
 * @created: 2022/07/08
 */
public class MaxDepth {

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() { }
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取树得深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return process(root);

    }

    public static int process(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        return Math.max(left,right) + 1;
    }
}
