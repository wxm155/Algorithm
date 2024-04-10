package code.leetCodeTop100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/04/10
 */
public class Subsets {
    
    /**
     * 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。 解集不能包含重复的子集。你可以按任意顺序返回解集。
     * <p>
     * 示例 1： 输入：nums = [1,2,3] 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 示例 2： 输入：nums = [0] 输出：[[],[0]]
     * <p>
     * 提示： 1 <= nums.length <= 10， -10 <= nums[i] <= 10 nums 中的所有元素 互不相同
     * <p>
     * 力扣：https://leetcode.cn/problems/subsets/description/?envType=study-plan-v2&envId=top-100-liked
     */
    
    
    public List<List<Integer>> subsets(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        process(nums, 0, nums.length, res, temp);
        return res;
    }
    
    public void process(int[] nums, int index, int len, List<List<Integer>> res, List<Integer> temp) {
        if (index == len) {
            res.add(temp);
            return;
        }
        // 不要当前数字
        List<Integer> no = new ArrayList<>(temp);
        process(nums, index + 1, len, res, no);
        
        // 要当前数字
        List<Integer> yes = new ArrayList<>(temp);
        yes.add(nums[index]);
        process(nums, index + 1, len, res, yes);
        
    }
    
    // 回溯
    public List<List<Integer>> subsets2(int[] nums) {
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        process2(nums, 0, nums.length, res, temp);
        return res;
    }
    
    public void process2(int[] nums, int index, int len, List<List<Integer>> res, List<Integer> temp) {
        if (index == len) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 要当前数字
        temp.add(nums[index]);
        process2(nums, index + 1, len, res, temp);
        
        // 不要当前数字
        temp.remove(temp.size() - 1);
        process2(nums, index + 1, len, res, temp);
    }
}
