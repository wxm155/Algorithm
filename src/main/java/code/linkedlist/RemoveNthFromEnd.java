package code.linkedlist;

import java.util.Stack;

/**
 * @author: wxm
 * @created: 2022/06/29
 */
public class RemoveNthFromEnd {

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     *
     * 力扣：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     */


    private static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }
    }

    /**
     * 利用容器实现
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        // 利用傀儡节点防止删除头节点
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode cur = pre;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode next = null;
        for (int i = 0; i < n; i++) {
            next = stack.pop();
        }
        stack.peek().next = next.next;
        return pre.next;
    }

    /**
     * 利用双指针，让快指针先走删除节点数 n-1 个位置，快指针走到尾，慢指针为删除节点的前一个节点
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n < 1) {
            return head;
        }
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode slow = pre;
        ListNode fast = pre;
        // +1 让slow节点停在删除节点的前一个节点
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }
}
