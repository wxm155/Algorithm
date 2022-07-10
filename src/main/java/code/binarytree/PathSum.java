package code.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxm
 * @created 2022/7/10
 */
public class PathSum {

    /**
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     *
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     * 示例 2：
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：[]
     *
     * 示例 3：
     * 输入：root = [1,2], targetSum = 0
     * 输出：[]
     *
     * 力扣：https://leetcode.cn/problems/path-sum-ii/
     *
     * 相似{@link HasPathSum}
     */

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

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        process(root,0,targetSum);
        return res;
    }

    public void process(TreeNode root,int sum,int targetSum){
        if (root == null){
            return;
        }
        sum += root.val;
        list.add(root.val);
        if (sum == targetSum && root.left == null && root.right == null){
            res.add(new ArrayList<>(list));
        }
        process(root.left,sum,targetSum);
        process(root.right,sum,targetSum);
        // 切换分支删除另一分支的值
        list.remove(list.size() - 1);
        // 没必要
        //sum -= root.val;
    }
}
