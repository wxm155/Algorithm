package code.linkedlist;

/**
 * @author: wxm
 * @created: 2022/07/04
 */
public class RotateRight {

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     *
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     *
     * 示例 2：
     * 输入：head = [0,1,2], k = 4
     * 输出：[2,0,1]
     *
     * 力扣：https://leetcode.cn/problems/rotate-list/
     */

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val;this.next = next; }
    }


    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0 || head.next == null){
            return head;
        }
        ListNode cur = head;
        int len = 1;
        while (cur.next != null){
            len++;
            cur = cur.next;
        }
        int move = len - k % len;
        if (move == len){
            return head;
        }
        cur.next = head;
        while (move-- > 0){
            cur = cur.next;
        }
        ListNode res = cur.next;
        cur.next = null;
        return res;
    }
}
