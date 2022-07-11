package code.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2022/07/08
 */
public class WidthOfBinaryTree {

    /**
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     *
     * 示例 1:
     * 输入:
     *            1
     *          /   \
     *         3     2
     *        / \     \
     *       5   3     9
     * 输出: 4
     * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
     *
     * 示例 2:
     * 输入:
     *           1
     *          /
     *         3
     *        / \
     *       5   3
     * 输出: 2
     * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
     *
     * 示例 3:
     * 输入:
     *           1
     *          / \
     *         3   2
     *        /
     *       5
     *
     * 输出: 2
     * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
     *
     * 示例 4:
     * 输入:
     *           1
     *          / \
     *         3   2
     *        /     \
     *       5       9
     *      /         \
     *     6           7
     * 输出: 8
     * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
     *
     * 力扣：https://leetcode.cn/problems/maximum-width-of-binary-tree/
     */

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() { }
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取二叉树的最大宽度
     * @param root
     * @return
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));
        int curDept = 0, ans = 0, left = 0;
        while (!queue.isEmpty()) {
            NodeInfo info = queue.poll();
            // 如果存在node节点，将node节点的子节点放入队列
            if (info.node != null) {
                // 左边是最左节点时，position恒等于0
                queue.offer(new NodeInfo(info.node.left, info.dept + 1, info.position * 2));
                queue.offer(new NodeInfo(info.node.right, info.dept + 1, info.position * 2 + 1));
                // 向下移动一层时最左节点时，将dept和left赋值
                if (curDept != info.dept) {
                    curDept = info.dept;
                    left = info.position;
                }
                // 左右间距 right - left + 1
                ans = Math.max(ans, info.position - left + 1);
            }
        }
        return ans;
    }

    public static class NodeInfo {
        TreeNode node;
        int dept;
        int position;

        public NodeInfo(TreeNode node, int dept, int position) {
            this.node = node;
            this.dept = dept;
            this.position = position;
        }
    }


    /**
     * 解法二：利用map记住每层最左位置，李荣递归求出最大值
     */
    private static Map<Integer,Integer> leftMap = new HashMap<>();
    private static int ans;

    public static int widthOfBinaryTree2(TreeNode root) {
        if (root == null){
            return 0;
        }
        process(root,0,0);
        return ans;
    }

    public static void process(TreeNode root,int dept,int position){
        if (root == null){
            return;
        }
        leftMap.computeIfAbsent(dept, k ->position);
        ans = Math.max(ans, position - leftMap.get(dept) + 1);
        process(root.left,dept + 1,position * 2);
        process(root.right,dept + 1,position * 2 + 1);
    }

}
