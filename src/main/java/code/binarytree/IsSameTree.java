package code.binarytree;

/**
 * @author: wxm
 * @created: 2022/07/08
 */
public class IsSameTree {

    /**
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * 力扣：https://leetcode.cn/problems/same-tree/
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
     * 终止条件与返回值：
     * 当两棵树的当前节点都为 null 时返回 true
     * 当其中一个为 null 另一个不为 null 时返回 false
     * 当两个都不为空但是值不相等时，返回 false
     * 执行过程：当满足终止条件时进行返回，不满足时分别判断左子树和右子树是否相同
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null){
            return false;
        }
        if (p.val != q.val){
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
