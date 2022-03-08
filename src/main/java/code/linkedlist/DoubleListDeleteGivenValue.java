package code.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个双向链表和一个值，删除链表中的值
 *
 * @author: wxm
 * @created: 2022/03/08
 */
public class DoubleListDeleteGivenValue {

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 删除双向链表中指定值
     *
     * @param head
     * @param num
     * @return
     */
    public static DoubleNode deleteValue(DoubleNode head, int num) {
        // 找到第一个不删除的节点
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
            if (head != null){
                head.last = null;
            }
        }

        DoubleNode pre = head;
        DoubleNode next = head;
        while (next != null) {
            if (next.value == num) {
                DoubleNode current = next.next;
                pre.next = current;
                // 删除最后一个节点
                // 30 22 5 -1 15 ,15
                if (current != null){
                    current.last = pre;
                }
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
    public static DoubleNode generateDoubleList(int maxSize, int maxValue) {
        int size = 0;
        int randomMaxSize = (int) (Math.random() * (maxSize + 1));
        DoubleNode head = new DoubleNode((int) (Math.random() * (maxValue + 1)));
        size++;
        DoubleNode current = head;
        while (size <= randomMaxSize) {
            DoubleNode temp = new DoubleNode((int) ((Math.random() - Math.random()) * (maxValue + 1)));
            current.next = temp;
            temp.last = current;
            current = temp;
            size++;
        }
        return head;
    }

    public static void main(String[] args) {

        // test generateDoubleList
//        int maxSize = 20;
//        int maxValue = 50;
//        DoubleNode doubleNode = generateDoubleList(maxSize, maxValue);
//        List<Integer> ascList = new ArrayList<>();
//        DoubleNode ascNode = doubleNode;
//        while (ascNode != null) {
//            ascList.add(ascNode.value);
//            ascNode = ascNode.next;
//        }
//
//        // 反转链表
//        List<Integer> descList = new ArrayList<>();
//
//        DoubleNode descNode = doubleNode;
//        DoubleNode pre = null;
//        DoubleNode next = null;
//        while (descNode != null) {
//            next = descNode.next;
//            descNode.next = pre;
//            descNode.last = next;
//            pre = descNode;
//            descNode = next;
//        }
//
//        DoubleNode temp = pre;
//        while (temp != null) {
//            descList.add(temp.value);
//            temp = temp.next;
//        }
//
//        for (int i = 0; i < ascList.size(); i++) {
//            if (ascList.get(i) != descList.get(descList.size() - 1 - i)) {
//                System.out.println("fuck....");
//            }
//        }

        for (int i = 0; i < 500000; i++) {
            int maxSize = 20;
            int maxValue = 20;
            DoubleNode doubleNode = generateDoubleList(maxSize, maxValue);
            int deleteValue = (int)(Math.random() * (maxValue + 1));
            DoubleNode resultNode = deleteValue(doubleNode, deleteValue);
            List<Integer> ascList = new ArrayList<>();
            DoubleNode ascNode = resultNode;
            while (ascNode != null) {
                ascList.add(ascNode.value);
                ascNode = ascNode.next;
            }
            if (ascList.contains(deleteValue)){
                System.out.println("fuck....");
            }
        }
    }
}
