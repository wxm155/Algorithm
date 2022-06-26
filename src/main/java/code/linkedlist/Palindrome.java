package code.linkedlist;

import java.util.Stack;

/**
 * @author wxm
 * @created 2022/6/26
 */
public class Palindrome {

    /**
     * 给定一个单链表的头节点head，请判断该链表是否为回文结构
     *
     * 力扣：https://leetcode.cn/problems/palindrome-linked-list/
     *
     */

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
     * 方案一：利用栈
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head){
        if (head == null){
            return true;
        }
        // 利用栈将链表逆序
        Stack<Integer> stack = new Stack<>();
        ListNode current1 = head;
        while(current1 != null){
            stack.push(current1.val);
            current1 = current1.next;
        }
        // 判断是否回文
        ListNode current2 = head;
        while (current2 != null){
            if (stack.pop() != current2.val){
                return false;
            }
            current2 = current2.next;
        }
        return true;
    }

    /**
     * 解法二，额外空间复杂度 O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head){
        if (head == null || head.next == null){
            return true;
        }
        // 利用快慢指针找出中点
        ListNode n1 = head;
        ListNode n2 = head;
        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 以中点为界，将后半部分链表反转
        n2 = n1.next;
        n1.next = null;
        ListNode n3 = null;
        while(n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        // 判断两个链表是否相等
        n3 = n1;
        n2 = head;
        boolean result = true;
        while (n1 != null && n2 != null){
            if (n1.val != n2.val){
                result =  false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 将链表还原
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return result;
    }
}
