package code.binarytree;

/**
 * @author: wxm
 * @created: 2023/07/26
 */
public class BSTtoDoubleLinkedList {

    /**
     * 给定一棵搜索二叉树头节点，转化成首尾相接的有序双向链表（节点都有两个方向的指针）
     */

    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }
        Info allInfo = process(head);
        allInfo.end.right = allInfo.start;
        allInfo.start.left = allInfo.end;
        return allInfo.start;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        if (left.end != null) {
            left.end.right = head;
        }
        head.left = left.end;
        head.right = right.start;
        if (right.start != null) {
            right.start.left = head;
        }
        return new Info(left.start != null ? left.start : head, right.end != null ? right.end : head);
    }


    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
}
