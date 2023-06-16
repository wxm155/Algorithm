package code.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/06/16
 */
public class SortedListToBST {

    /**
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     *
     * 示例 1:
     * 输入: head = [-10,-3,0,5,9]
     * 输出: [0,-3,9,-10,null,5]
     * 解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
     *
     * 示例 2:
     * 输入: head = []
     * 输出: []
     *
     * 提示:
     * head 中的节点数在[0, 2 * 104] 范围内
     * -105 <= Node.val <= 105
     *
     * 力扣：https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
     */

    /**
     * 解法一：以中间位置为树的头节点，构建出来的二叉树为高度平衡二叉树
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        // right = list.size() - 1 >> list从0开始计算
        return process(list, 0, list.size() - 1);
    }

    public TreeNode process(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        // int mid = left + ((right - left + 1) >> 1); 偶数取中间右边的树
        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(list.get(mid));
        root.left = process(list, left, mid - 1);
        root.right = process(list, mid + 1, right);
        return root;
    }

    /**
     * 解法二：
     * 寻找链表的中间点有个小技巧：
     * 快慢指针起初都指向头结点，分别一次走两步和一步，当快指针走到尾节点时，慢指针正好走到链表的中间。断成两个链表，分而治之。
     * @param head
     * @return
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        // slow指针每次走一步，fast指针每次走两步，fast走到尾节点的时候，slow指针刚好在中间节点
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        // 断开链表
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST2(head);
        root.right = sortedListToBST2(right);
        return root;
    }

    ListNode node;

    /**
     * 解法三：有序链表的值正好是BST中序遍历的结果，可以先构建左子树，再构建根节点，再构建右子树。——遵循中序遍历。
     * @param head
     * @return
     */
    public TreeNode sortedListToBST3(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        node = head;
        return process3(0, len - 1);
    }

    public TreeNode process3(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode();
        // 先构建左子树
        root.left = process3(left, mid - 1);
        // 构建根节点，左子树构建完，node节点正好是中间根节点的值
        root.val = node.val;
        node = node.next;
        // 构建右子树
        root.right = process3(mid + 1, right);
        return root;
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
