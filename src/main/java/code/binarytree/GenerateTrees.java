package code.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/06/02
 */
public class GenerateTrees {

    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     *
     * 提示：
     * 1 <= n <= 8
     *
     * 力扣：https://leetcode.cn/problems/unique-binary-search-trees-ii/
     */


    /**
     * 生成由1-n生成的所有搜索二叉树
     *
     * @param n n
     * @return 返回结果
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        return process(1, n);
    }

    /**
     * 递归构建所有搜索二叉树的可能性
     *
     * @param start 开始节点
     * @param end   结束节点
     * @return 返回结果
     */
    public static List<TreeNode> process(int start, int end) {
        List<TreeNode> allTree = new ArrayList<>();
        if (start > end) {
            allTree.add(null);
            return allTree;
        }
        // 遍历以每个节点作为根节点的可能性，小于i的节点一定在i的左子树上，大于i的节点一定在i的右子树上
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = process(start, i - 1);
            List<TreeNode> right = process(i + 1, end);
            // 遍历左右子树，组装所有可能性
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    allTree.add(root);
                }
            }
        }
        return allTree;
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
