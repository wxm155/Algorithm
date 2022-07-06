package code.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2022/07/05
 */
public class levelOrder {

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * 示例 1：
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[3],[9,20],[15,7]]
     *
     * 示例 2：
     * 输入：root = [1]
     * 输出：[[1]]
     *
     * 示例 3：
     * 输入：root = []
     * 输出：[]
     *
     * 力扣：https://leetcode.cn/problems/binary-tree-level-order-traversal/
     */

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

    /**
     * 根据队列的size获取每层节点的数量
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> element = new ArrayList<>();
            int currentSize =  queue.size();
            for (int i = 0; i < currentSize; i++) {
                TreeNode poll = queue.poll();
                element.add(poll.val);
                if (poll.left != null){
                    queue.offer(poll.left);
                }
                if (poll.right != null){
                    queue.offer(poll.right);
                }
            }
            res.add(element);
        }
        return res;
    }

}
