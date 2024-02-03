package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/2
 */
public class ReverseList {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * <p>
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：[2,1]
     * <p>
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     * <p>
     * 提示：
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     * <p>
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     * <p>
     * 力扣：https://leetcode.cn/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode next = head.next;
        pre.next = null;
        while (next != null) {
            ListNode last = next.next;
            next.next = pre;
            pre = next;
            next = last;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public class ListNode {
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
}
