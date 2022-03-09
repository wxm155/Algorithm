package code.linkedlist;

/**
 * @author: wxm
 * @created: 2022/03/09
 */
public class AddTwoNumbers {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 力扣：https://leetcode-cn.com/problems/add-two-numbers/
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     */


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = null, tail = null;
        // 进位标志变量
        int temp = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int total = num1 + num2 + temp;
            if (head == null) {
                head = tail = new ListNode(total % 10);
                temp = total / 10;
            } else {
                tail.next = new ListNode(total % 10);
                tail = tail.next;
                temp = total / 10;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        // 最后进位
        if (temp != 0) {
            tail.next = new ListNode(temp);
        }
        return head;
    }

    /**
     * 优化版
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode head = null, tail = null;
        int temp = 0;
        while (l1 != null || l2 != null || temp > 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int total = num1 + num2 + temp;
            if (head == null) {
                head = tail = new ListNode(total % 10);
                temp = total / 10;
            } else {
                tail.next = new ListNode(total % 10);
                temp = total / 10;
                tail = tail.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return head;
    }

}
