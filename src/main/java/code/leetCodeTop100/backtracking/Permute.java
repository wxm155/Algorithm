package code.leetCodeTop100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/04/10
 */
public class Permute {
    
    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <p>
     * 示例 1： 输入：nums = [1,2,3] 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * <p>
     * 示例 2： 输入：nums = [0,1] 输出：[[0,1],[1,0]]
     * <p>
     * 示例 3： 输入：nums = [1] 输出：[[1]]
     * <p>
     * 提示： 1 <= nums.length <= 6, -10 <= nums[i] <= 10 nums 中的所有整数 互不相同
     * <p>
     * 力扣：https://leetcode.cn/problems/permutations/description/?envType=study-plan-v2&envId=top-100-liked
     */
    
    public List<List<Integer>> permute(int[] nums) {
        
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[len];
        
        process(nums, 0, len, res, temp, used);
        return res;
    }
    
    public void process(int[] nums, int index, int len, List<List<Integer>> res, List<Integer> temp, boolean[] used) {
        
        if (index == len) {
            res.add(new ArrayList<>(temp));
            return;
        }
        
        for (int i = 0; i < len; i++) {
            
            if (!used[i]) {
                
                temp.add(nums[i]);
                used[i] = true;
                
                process(nums, index + 1, len, res, temp, used);
                
                // 恢复现场
                used[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }
    
}
