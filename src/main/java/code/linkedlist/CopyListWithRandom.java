package code.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2022/06/28
 */
public class CopyListWithRandom {

    /**
     * 一种特殊的单链表节点类描述如下
     * class Node {
     *    int value;
     *    Node next;
     *    Node rand;
     *    Node(int val) { value = val; }
     * }
     *    rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，
     *    也可能指向null 给定一个由Node节点类型组成的无环单链表的头节点head，
     *    请实现一个函数完成这个链表的复制 返回复制的新链表的头节点，要求时间复杂度O(N)，额外空间复杂度O(1)
     *
     * 力扣：https://leetcode.cn/problems/copy-list-with-random-pointer/
     */

    private static class Node {
        int value;
        Node next;
        Node rand;
        Node(int val) {
            value = val;
        }
    }

    /**
     * 利用容器实现
     * @param head
     * @return
     */
    public Node copyList1(Node head){
        if (head == null){
            return null;
        }
        Map<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /**
     * 非容器实现，额外空间复杂度 O(1)
     * @param head
     * @return
     */
    public Node copyList2(Node head){
        if (head == null){
            return null;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        // 1 1' 2 2' 3 3'
        // 依次设置 1' 2' 3' random指针
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            copy.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

        Node res = head.next;
        cur = head;
        // 新老链表分离
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

}
