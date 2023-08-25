package code.binarytree;

/**
 * @author: wxm
 * @created: 2023/08/21
 */
public class CompleteTreeNodeNumber {

    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * <p>
     * 示例 1：
     * 输入：root = [1,2,3,4,5,6]
     * 输出：6
     * <p>
     * 示例 2：
     * 输入：root = []
     * 输出：0
     * <p>
     * 示例 3：
     * 输入：root = [1]
     * 输出：1
     * <p>
     * 提示：
     * 树中节点的数目范围是[0, 5 * 10^4]
     * 0 <= Node.val <= 5 * 10^4
     * 题目数据保证输入的树是 完全二叉树
     * <p>
     * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     * <p>
     * 力扣：https://leetcode.cn/problems/count-complete-tree-nodes/
     */

    // 时间复杂度：O(N)
    public int countNodes(TreeNode root) {
        return process(root);
    }

    public static int process(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = process(node.left);
        int right = process(node.right);
        return left + right + 1;
    }

    // 时间复杂度：O((logN)^2)
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process2(root, 1, mostRightLevel(root, 1));
    }

    public static int process2(TreeNode node, int level, int h) {
        if (level == h) {
            return 1;
        }
        // 每次计算右子树的最左节点的层数
        // 如果右子树的层数等于树的高度，则左子树一定是满完全二叉树，直接计算节点数量
        // 如果右子树的层数不等于树的高度，则右子树一定是满完全二叉树，直接计算节点数量
        if (mostRightLevel(node.right, level + 1) == h) {
            return (1 << (h - level)) + process2(node.right, level + 1, h);
        } else {
            return (1 << (h - level - 1)) + process2(node.left, level + 1, h);
        }

    }

    public static int mostRightLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
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
