package code.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单向链表和一个值，删除给定链表中的值
 *
 * @author: wxm
 * @created: 2022/03/08
 */
public class LinkedListDeleteGivenValue {

    public static class Node {
        public int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 删除单向链表中指定值
     *
     * @param head
     * @param num
     * @return
     */
    public static Node deleteValue(Node head, int num) {
        // 找到不被删除的头节点，可能存在前几个节点都是需要删除的节点
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node next = head;
        while (next != null) {
            if (next.value == num) {
                pre.next = next.next;
            } else {
                pre = next;
            }
            next = next.next;
        }
        return head;
    }

    /**
     * for test
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static Node generateLinkedList(int maxSize, int maxValue) {
        int size = 0;
        size++;
        int randomMaxSize = (int)(Math.random() * (maxSize + 1));
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        Node current = head;
        while (size <= randomMaxSize) {
            int value = (int) ((Math.random() - Math.random())* (maxValue + 1));
            current.next = new Node(value);
            current = current.next;
            size++;
        }
        return head;
    }

    public static void main(String[] args) {

        int maxSize = 20;
        int maxValue = 50;
        for (int i = 0; i < 50000; i++) {
            Node node = generateLinkedList(maxSize, maxValue);
            List<Integer> list = new ArrayList<>();

            int deleteValue = (int) ((Math.random() - Math.random())* (maxValue + 1));
            Node result = deleteValue(node, deleteValue);
            while (result != null) {
                list.add(result.value);
                result = result.next;
            }
            if (list.contains(deleteValue)) {
                System.out.println("fuck....");
            }
        }
//        Node node = generateLinkedList(maxSize, maxValue);
//        Node current = node;
//        while(current != null){
//            System.out.print(current.value + " ");
//            current = current.next;
//        }
//        System.out.println();
//        int deleteValue = (int) (Math.random() * (maxValue + 1));
//        System.out.println("删除值："+deleteValue);
//        Node result = deleteValue(node,deleteValue);
//        while (result != null){
//            System.out.print(result.value + " ");
//            result = result.next;
//        }
//        System.out.println();

    }
}
