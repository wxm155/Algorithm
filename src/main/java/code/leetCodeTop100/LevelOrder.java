package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class LevelOrder {

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     * <p>
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     * <p>
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [0, 2000] 内
     * -1000 <= Node.val <= 1000
     * <p>
     * 力扣：https://leetcode.cn/problems/binary-tree-level-order-traversal/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            List<Integer> values = new ArrayList<>();
            LinkedList<TreeNode> temp = new LinkedList<>();
            for (TreeNode node : stack) {
                values.add(node.val);
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            res.add(values);
            stack = temp;
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.pop();
                values.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            res.add(values);
        }
        return res;
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
