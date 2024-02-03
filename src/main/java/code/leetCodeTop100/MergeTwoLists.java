package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/3
 */
public class MergeTwoLists {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 示例 1：
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * <p>
     * 示例 2：
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     * <p>
     * 提示：
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * <p>
     * 力扣：https://leetcode.cn/problems/merge-two-sorted-lists/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null){
            return null;
        }
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        if (list1.val <= list2.val){
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        }else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
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
