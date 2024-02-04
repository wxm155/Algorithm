package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/02/04
 */
public class SortList {

    /**
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * <p>
     * 示例 1：
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * <p>
     * 示例 2：
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     * <p>
     * 示例 3：
     * 输入：head = []
     * 输出：[]
     * <p>
     * 提示：
     * 链表中节点的数目在范围 [0, 5 * 10^4] 内
     * -10^5 <= Node.val <= 10^5
     * <p>
     * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     */

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> nodes = new ArrayList<>();
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            nodes.add(head);
            head = next;
        }
        nodes.sort(new NodeComparator());

        ListNode temp = nodes.get(0);
        ListNode cur = temp;
        for (int i = 1; i < nodes.size(); i++) {
            cur.next = nodes.get(i);
            cur = cur.next;
        }
        return temp;
    }

    public static class NodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
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
