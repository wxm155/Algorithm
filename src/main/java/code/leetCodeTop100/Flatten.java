package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class Flatten {

    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * 示例 1：
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     * <p>
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * 提示：
     * 树中结点数在范围 [0, 2000] 内
     * -100 <= Node.val <= 100
     * <p>
     * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
     * <p>
     * 力扣：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> cache = new ArrayList<>();
        preOrder(root, cache);
        TreeNode cur = cache.get(0);
        for (int i = 1; i < cache.size(); i++) {
            TreeNode node = cache.get(i);
            cur.right = node;
            cur = node;
        }
    }

    public void preOrder(TreeNode root, List<TreeNode> cache) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = null;
        cache.add(root);
        preOrder(left, cache);
        preOrder(right, cache);
    }

    public void flatten2(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                // 左子树的最右节点
                TreeNode leftRight = root.left;
                while (leftRight.right != null) {
                    leftRight = leftRight.right;
                }
                // 将右子树挂到左子树的最右节点上
                leftRight.right = root.right;
                // 将左子树迁移到右子树上
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
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
