package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/3
 */
public class SwapPairs {

    /**
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * <p>
     * 示例 2：
     * 输入：head = []
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：head = [1]
     * 输出：[1]
     * <p>
     * 提示：
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     * <p>
     * 力扣：https://leetcode.cn/problems/swap-nodes-in-pairs/description/?envType=study-plan-v2&envId=top-100-liked
     */

    // 递归解法
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    // 非递归解法
    public ListNode swapPairs2(ListNode head) {
        ListNode pre = new ListNode(0,head);
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null){
            ListNode start = temp.next;
            ListNode end= temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
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
