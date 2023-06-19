package code.binarytree;

import java.security.MessageDigest;

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

    /**
     * 利用二叉树的序列化生成字符串，再利用hash算法生成指定长度的字符串（防止树结构过大生成字符串过多，趋近于N），
     * 将两次递归优化为一次递归
     *
     * @param head
     * @return
     */
    public static int sameTreeNum2(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head).num;
    }

    public static NodeInfo process2(Node head) {
        if (head == null) {
            return new NodeInfo(0, sha256Code("#,"));
        }
        NodeInfo left = process2(head.left);
        NodeInfo right = process2(head.right);
        int num = (left.str.equals(right.str) ? 1 : 0) + left.num + right.num;
        return new NodeInfo(num, sha256Code(left.str + head.value + "," + right.str + ","));
    }

    private static String sha256Code(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class NodeInfo {
        public int num;
        public String str;

        public NodeInfo(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // for test
    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameTreeNum(head);
            int ans2 = sameTreeNum2(head);
            if (ans1 != ans2) {
                System.out.println("fuck....");
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");

    }
}
