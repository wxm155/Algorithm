package code.linkedlist;

/**
 * @author wxm
 * @created 2022/6/26
 */
public class Partition {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
     */
    public ListNode partition(ListNode head, int x) {
        ListNode sH = null,sT = null;
        ListNode eH = null, eT = null;
        ListNode mH = null,mT = null;
        ListNode next = null;
        while (head != null){
            next = head.next;
            // next 指针置空
            head.next = null;
            if (head.val < x){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.val == x){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else{
                if (mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        // 可能出现小于、等于、大于区域为空的情况
        if (sH != null){
            sT.next = eH;
            sT = eT == null ? sT : eT;
        }
        if (sT != null){
            sT.next = mH;
        }
        return sH == null ? (eH == null ? mH : eH) : sH;
    }

    /**
     * 给你一个链表的头节点 head 和一个特定值 x ，
     * 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     *
     * 力扣：https://leetcode.cn/problems/partition-list/
     */
    public ListNode partition2(ListNode head, int x) {
        ListNode sH = null,sT = null;
        ListNode mH = null,mT = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = null;
            if (head.val < x){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }

        if (sH != null){
            sT.next = mH;
        }
        return sH == null ? mH : sH;
    }


}
