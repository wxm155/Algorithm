package code.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2022/07/12
 */
public class LevelOrderBottom {

    /**
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 力扣：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
     */

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

    /**
     * 从下往上按层遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            // 按层全部遍历
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 添加在头部
            res.add(0, list);
        }
        return res;
    }

    /**
     * 解法二
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        process(root, 0, res);
        return res;
    }

    public void process(TreeNode root, int depth, List<List<Integer>> list) {
        if (root == null) {
            return;
        }
        // 每层添加一个list
        if (depth == list.size()) {
            list.add(0, new ArrayList<>());
        }
        // 添加到对应层的list
        list.get(list.size() - depth - 1).add(root.val);
        process(root.left, depth + 1, list);
        process(root.right, depth + 1, list);
    }

}
