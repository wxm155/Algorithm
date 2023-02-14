package code.binarytree;

/**
 * @author: wxm
 * @created: 2023/02/14
 */
public class LeftRightSameTreeNumber {

    /**
     * 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的树是相等树，
     * 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
     */

    public static int sameTreeNum(Node head) {
        if (head == null) {
            return 0;
        }
        int leftNum = sameTreeNum(head.left);
        int rightNum = sameTreeNum(head.right);
        return leftNum + rightNum + (same(head.left, head.right) ? 1 : 0);
    }

    public static boolean same(Node n1, Node n2) {
        // true ^ true = false
        // false ^ false = false
        // true ^ false = true;
        if (n1 == null ^ n2 == null) {
            return false;
        }
        if (n1 == null && n2 == null) {
            return true;
        }
        return n1.value == n2.value && same(n1.left, n2.left) && same(n1.right, n2.right);
    }

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }
}
