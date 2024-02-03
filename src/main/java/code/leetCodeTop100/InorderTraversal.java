package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxm
 * @created 2024/2/2
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    public void process(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        process(node.left, list);
        list.add(node.val);
        process(node.right, list);
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
