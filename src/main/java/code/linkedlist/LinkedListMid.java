package code.linkedlist;

/**
 * @author wxm
 * @created 2022/7/4
 */
public class LinkedListMid {

    /**
     * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 3、输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 4、输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */

    private static class Node {
        public int value;
        public Node next;
        public Node(int v) {
            value = v;
        }
    }

    /**
     * 奇数长度返回中点，偶数长度返回上中点
     * @param head
     * @return
     */
    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数长度返回中点，偶数长度返回下中点
     * @param head
     * @return
     */
    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param head
     * @return
     */
    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数长度返回中点前一个，偶数长度返回下中点前一个
     * @param head
     * @return
     */
    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null){
            return null;
        }
        if (head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
