package code.linkedlist;

/**
 * @author: wxm
 * @created: 2022/06/28
 */
public class FindFirstIntersectNode {

    /**
     * 给定两个可能有环也可能无环的单链表，头节点head1和head2
     * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
     * 如果不相交返回null 要求如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
     */

    private static class Node {
        int value;
        Node next;
        Node(int val) {
            value = val;
        }
    }

    public static Node getIntersectNode(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        // 两个链表都无环
        if (loopNode1 == null && loopNode2 == null){
            return noLoop(head1, head2);
        }
        // 两个链表都有环
        if (loopNode1 != null && loopNode2 != null){
            return bothLoop(head1,loopNode1,head2,loopNode2);
        }
        // 一个有环，一个无环必不相交
        return null;
    }

    /**
     * 判断链表是否为环形链表，如果有环，返回第一个入环节点，无环返回null。
     * 利用快慢指针，快指针一次走两步，慢指针一次走一步，第一次相交后，快指针返回头节点一次走一步，
     * 再次相交时为入环第一个节点
     * @param head
     * @return
     */
    public static Node getLoopNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast){
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 两个无环链表是否相交,两链表相交结尾必相等
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }
        int n = 0;
        Node cur1 = head1;
        Node cur2 = head2;
        // 判断条件为下一个节点是否为空，不然不相交的if恒等于false
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        // 不相交
        if (cur1 != cur2){
            return null;
        }
        // cur1 为长链表
        cur1 = n > 0 ? head1 : head2;
        // cur2 为短链表
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        // 长链表先移动两链表的差值
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        // 获取相交第一个节点
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个都有环，存在两种相交情况：
     * 1、入环前相交。2、环上相交
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        // 入环前相交
        if (loop1 == loop2){
            int n = 0;
            cur1 = head1;
            cur2 = head2;
            // 只需到入环节点即可
            while (cur1.next != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2.next != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }
        // 入环后相交
        else {
            // 环为一个节点，loop1不等于loop2必不相交
            cur1 = loop1.next;
            while (cur1 != loop1){
                if (cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

//        // 1->2->3->4->5->6->7->4...
//        head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(3);
//        head1.next.next.next = new Node(4);
//        head1.next.next.next.next = new Node(5);
//        head1.next.next.next.next.next = new Node(6);
//        head1.next.next.next.next.next.next = new Node(7);
//        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4
//
//        // 0->9->8->2...
//        head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next; // 8->2
//        System.out.println(getIntersectNode(head1, head2).value);
//
//        // 0->9->8->6->4->5->6..
//        head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next.next.next.next.next; // 8->6
//        System.out.println(getIntersectNode(head1, head2).value);

    }
}
