package code.leetCodeTop100;

import java.util.LinkedList;

/**
 * @author wxm
 * @created 2024/2/2
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int depth = 0;
        while (!list.isEmpty()) {
            LinkedList<TreeNode> temp = new LinkedList<>();
            for (TreeNode node : list) {
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }
            list = temp;
            depth++;
        }
        return depth;
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left,right) + 1;
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
