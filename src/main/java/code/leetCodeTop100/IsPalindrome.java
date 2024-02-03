package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxm
 * @created 2024/2/2
 */
public class IsPalindrome {

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：head = [1,2,2,1]
     * 输出：true
     * <p>
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：false
     * <p>
     * 提示：
     * 链表中节点数目在范围[1, 105] 内
     * 0 <= Node.val <= 9
     * <p>
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * <p>
     * 力扣：https://leetcode.cn/problems/palindrome-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
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
