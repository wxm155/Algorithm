package code.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wxm
 * @created: 2022/06/30
 */
public class MergeKLists {

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * 力扣：https://leetcode.cn/problems/merge-k-sorted-lists/
     */


    private static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }
    }

    /**
     * 每次合并两个队列
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists1(ListNode[] lists) {

        if (lists == null && lists.length < 1) {
            return null;
        }
        ListNode head = new ListNode(-1);
        ListNode next = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode list = lists[i];
            while (next != null && list != null) {
                if (next.val < list.val) {
                    head.next = next;
                    next = next.next;
                } else {
                    head.next = list;
                    list = list.next;
                }
                head = head.next;
            }
            next.next = list == null ? next : list;
        }
        return head.next;
    }

    /**
     * 利用优先级队列，每次弹出最小值，将剩余节点放入队列
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null && lists.length < 1) {
            return null;
        }
        // PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(e ->e.val));
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new NodeComparator());
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }
        return head.next;
    }

    /**
     * ListNode比较器
     */
    private static class NodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    /**
     * 归并思想解决多链表合并
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null && lists.length < 1) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);
        return mergeTwoList(leftNode, rightNode);
    }

    public static ListNode mergeTwoList(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        tail.next = head1 == null ? head2 : head1;
        return head.next;
    }
}
