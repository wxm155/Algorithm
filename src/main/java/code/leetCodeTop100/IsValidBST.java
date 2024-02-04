package code.leetCodeTop100;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class IsValidBST {

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * <p>
     * 示例 1：
     * 输入：root = [2,1,3]
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     * <p>
     * 提示：
     * 树中节点数目范围在[1, 104] 内
     * -2^31 <= Node.val <= 2^31 - 1
     * <p>
     * 力扣：https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode root, long leftValue, long rightValue) {
        if (root == null) {
            return true;
        }
        if (root.val <= leftValue || root.val >= rightValue) {
            return false;
        }
        boolean left = valid(root.left, leftValue, root.val);
        boolean right = valid(root.right, root.val, rightValue);
        return left && right;
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
