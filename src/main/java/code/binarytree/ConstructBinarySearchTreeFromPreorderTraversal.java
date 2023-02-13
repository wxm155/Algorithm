package code.binarytree;

/**
 * @author: wxm
 * @created: 2023/02/13
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

    /**
     * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
     * 保证 对于给定的测试用例，总是有可能找到具有给定需求的二叉搜索树。
     * 二叉搜索树 是一棵二叉树，其中每个节点， Node.left 的任何后代的值 严格小于 Node.val , Node.right 的任何后代的值 严格大于 Node.val。
     * 二叉树的 前序遍历 首先显示节点的值，然后遍历Node.left，最后遍历Node.right。
     *
     * 示例 1：
     * 输入：preorder = [8,5,1,7,10,12]
     * 输出：[8,5,10,1,7,null,12]
     *
     * 示例 2:
     * 输入: preorder = [1,3]
     * 输出: [1,null,3]
     *
     * 提示：
     * 1 <= preorder.length <= 100
     * 1 <= preorder[i] <= 10^8
     * preorder 中的值 互不相同
     *
     * 力扣：https://leetcode.cn/problems/construct-binary-search-tree-from-preorder-traversal/
     */

    /**
     * 递归解法
     * @param preorder 先序遍历数组
     * @return 根节点
     */
    public static TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return process1(preorder, 0, preorder.length - 1);
    }

    public static TreeNode process1(int[] arr, int l, int r) {
        if (l > r) {
            return null;
        }
        // 先序遍历，第一个值一定为头节点，小于头节点的为左子树，大于头节点的为右子树
        int firstBig = l + 1;
        for (; firstBig <= r; firstBig++) {
            if (arr[firstBig] > arr[l]) {
                break;
            }
        }
        TreeNode root = new TreeNode(arr[l]);
        root.left = process1(arr, l + 1, firstBig - 1);
        root.right = process1(arr, firstBig, r);
        return root;
    }

    /**
     * 单调栈优化处理预处理数组(大于它且离它最近的位置)
     * @param preorder
     * @return
     */
    public static TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int len = preorder.length;
        // nearBig[i] => preorder[i] 离它最近且大于它的数的位置
        int[] nearBig = new int[len];
        for (int i = 0; i < len; i++) {
            nearBig[i] = -1;
        }
        int[] stack = new int[len];
        int size = 0;
        for (int i = 0; i < len; i++) {
            while (size != 0 && preorder[stack[size - 1]] < preorder[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return process2(preorder, 0, preorder.length - 1, nearBig);
    }

    public static TreeNode process2(int[] arr, int l, int r, int[] nearBig) {
        if (l > r) {
            return null;
        }
        int firstBig = (nearBig[l] == -1 || nearBig[l] > r) ? r + 1 : nearBig[l];
        TreeNode root = new TreeNode(arr[l]);
        root.left = process2(arr, l + 1, firstBig - 1, nearBig);
        root.right = process2(arr, firstBig, r, nearBig);
        return root;
    }



    private static class TreeNode {
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
