package code.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2023/06/13
 */
public class IsValidBST {

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * 示例 1：
     * 输入：root = [2,1,3]
     * 输出：true
     *
     * 示例 2：
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     *
     * 提示：
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     *
     * 力扣：https://leetcode.cn/problems/validate-binary-search-tree/
     */

    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return process(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean process(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        boolean left = process(root.left, lower, root.val);
        boolean right = process(root.right, root.val, upper);
        return left && right;
    }

    private static class TreeNode {
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
