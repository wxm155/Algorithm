package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class RightSideView {

    /**
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * <p>
     * 示例 1:
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1,3,4]
     * <p>
     * 示例 2:
     * 输入: [1,null,3]
     * 输出: [1,3]
     * <p>
     * 示例 3:
     * 输入: []
     * 输出: []
     * <p>
     * 提示:
     * 二叉树的节点个数的范围是 [0,100]
     * -100 <= Node.val <= 100
     * <p>
     * 力扣：https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode last = queue.peekLast();
            res.add(last.val);
            LinkedList<TreeNode> temp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null){
                    temp.add(node.left);
                }
                if (node.right != null){
                    temp.add(node.right);
                }
            }
            queue = temp;
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
