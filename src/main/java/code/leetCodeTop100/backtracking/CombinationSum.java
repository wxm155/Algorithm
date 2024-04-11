package code.leetCodeTop100.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/04/11
 */
public class CombinationSum {
    
    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ， 并以列表形式返回。你可以按 任意顺序
     * 返回这些组合。 candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     * <p>
     * 示例 1： 输入：candidates = [2,3,6,7], target = 7 输出：[[2,2,3],[7]] 解释： 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。 7
     * 也是一个候选， 7 = 7 。 仅有这两种组合。
     * <p>
     * 示例 2： 输入: candidates = [2,3,5], target = 8 输出: [[2,2,2,2],[2,3,3],[3,5]]
     * <p>
     * 示例 3： 输入: candidates = [2], target = 1 输出: []
     * <p>
     * 提示： 1 <= candidates.length <= 30， 2 <= candidates[i] <= 40 candidates 的所有元素 互不相同 1 <= target <= 40
     * <p>
     * 力扣：https://leetcode.cn/problems/combination-sum/description/?envType=study-plan-v2&envId=top-100-liked
     */
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        process(candidates, target, 0, res, temp, new HashSet<>());
        return res;
    }
    
    public void process(int[] candidates, int target, int cur, List<List<Integer>> res, List<Integer> temp,
            HashSet<String> set) {
        // 重复性判断
        if (cur == target) {
            List<Integer> newTemp = new ArrayList<>(temp);
            Collections.sort(newTemp);
            String string = Arrays.toString(newTemp.toArray());
            if (!set.contains(string)) {
                res.add(newTemp);
                set.add(string);
            }
            return;
        }
        
        if (cur > target) {
            return;
        }
        
        for (int candidate : candidates) {
            
            temp.add(candidate);
            cur += candidate;
            process(candidates, target, cur, res, temp, set);
            cur -= candidate;
            temp.remove(temp.size() - 1);
        }
    }
    
}
