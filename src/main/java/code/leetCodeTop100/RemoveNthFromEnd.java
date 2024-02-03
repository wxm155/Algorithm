package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/3
 */
public class RemoveNthFromEnd {

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * <p>
     * 示例 2：
     * 输入：head = [1], n = 1
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     * <p>
     * 提示：
     * 链表中结点的数目为 sz
     * 1 <= sz <= 30
     * 0 <= Node.val <= 100
     * 1 <= n <= sz
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * <p>
     * 力扣：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 添加虚拟头结点，不用判空，防止删除的是第一个节点
        ListNode temp = new ListNode(0, head);
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        ListNode cur = temp;
        for (int i = 1; i <= len - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return temp.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 添加虚拟头结点，不用判空，防止删除的是第一个节点
        ListNode temp = new ListNode(0, head);
        ListNode first = head, second = temp;
        // 先使first先走n个节点
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        // 然后first和second一起走，
        // 当first到达末尾时，second正好处在要删除的倒数第n个节点上
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return temp.next;
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
