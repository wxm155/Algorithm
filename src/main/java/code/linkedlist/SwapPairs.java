package code.linkedlist;

/**
 * @author: wxm
 * @created: 2022/07/01
 */
public class SwapPairs {

    /**
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     *
     * 力扣：https://leetcode.cn/problems/swap-nodes-in-pairs/
     */

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null){
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return pre.next;
    }
}
