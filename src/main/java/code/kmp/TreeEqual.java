package code.kmp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2022/09/21
 */
public class TreeEqual {

    /**
     * 给定两棵二叉树的头节点head1和head2，返回head1中是否有某个子树的结构和head2完全一样
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 将二叉树先序序列化，转换为head2是否为head1的子串问题
    public static boolean containsTree(Node head1, Node head2) {
        if (head2 == null) {
            return true;
        }
        if (head1 == null) {
            return false;
        }
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        preSerial(head1, list1);
        preSerial(head2, list2);
        String[] strArr = new String[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            strArr[i] = list1.get(i);
        }
        String[] matchArr = new String[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            matchArr[i] = list2.get(i);
        }
        return getIndexOf(strArr, matchArr) != -1;
    }

    // 先序遍历序列化
    public static void preSerial(Node node, List<String> list) {
        if (node == null) {
            list.add(null);
        } else {
            list.add(String.valueOf(node.value));
            preSerial(node.left, list);
            preSerial(node.right, list);
        }
    }

    // KMP
    public static int getIndexOf(String[] str, String[] match) {
        if (str == null || match == null || str.length < match.length) {
            return -1;
        }
        int[] next = getNextArray(match);
        int x = 0, y = 0;
        while (x < str.length && y < match.length) {
            if (isEqual(str[x], match[y])) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    public static int[] getNextArray(String[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < match.length) {
            if (isEqual(match[index - 1], match[cn])) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }
        }
    }

    // 暴力解
    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree(big, small);
            boolean ans2 = containsTree1(big, small);
            if (ans1 != ans2) {
                System.out.println("fuck.....");
            }
        }
        System.out.println("test finish!");

    }
}
