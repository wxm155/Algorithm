package code.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 单链表反转
 * @author: wxm
 * @created: 2022/03/04
 */
public class ReverseLinkedList {

    /**
     * 单向链表
     */
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 单向链表反转
     *
     * @param head
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            // 记住下一个节点
            next = head.next;
            // 将头节点的next指向前一个节点
            head.next = pre;
            // pre和next赋值
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * for test
     * @param head
     * @return
     */
    public static List<Integer> test(Node head) {
        List<Integer> list = new ArrayList<>();
        Node pre = head;
        while (pre != null) {
            list.add(pre.value);
            pre = pre.next;
        }
        return list;
    }

    /**
     * 生成随机单向链表
     *
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static Node generateRandomLinkedList(int maxLen, int maxValue) {
        int size = (int) (Math.random() * (maxLen + 1));
        if (size == 0) {
            return null;
        }
        // 生成头节点
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        Node pre = head;
        size--;
        while (size > 0) {
            pre.next = new Node((int) (Math.random() * (maxValue + 1)));
            pre = pre.next;
            size--;
        }
        return head;
    }

    public static void main(String[] args) {

        for (int j = 0; j < 50000; j++) {
            Node node = generateRandomLinkedList(20, 50);
            List<Integer> list = test(node);
            Node reverseNode = reverseLinkedList(node);
            Node pre = reverseNode;
            for (int i = list.size() - 1; i >= 0; i--) {
                int value1 = list.get(i);
                int value2 = pre.value;
                if (value1 != value2){
                    System.out.println("fuck....");
                }
                pre = pre.next;
            }
        }
//        Node node = generateRandomLinkedList(20, 50);
//        Node pre = node;
//        while (pre != null){
//            System.out.print(pre.value + " ");
//            pre = pre.next;
//        }
//        System.out.println();
//        System.out.println("---------------------");
//        Node reverseNode = reverseLinkedList(node);
//        while (reverseNode != null){
//            System.out.print(reverseNode.value + " ");
//            reverseNode = reverseNode.next;
//        }
    }
}
