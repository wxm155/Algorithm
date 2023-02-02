package code.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wxm
 * @created: 2022/03/23
 */
public class ThreeSum {

    /**
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * 示例 1：
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 示例 2：
     * 输入：nums = []
     * 输出：[]
     *
     * 示例 3：
     * 输入：nums = [0]
     * 输出：[]
     *
     * 力扣：https://leetcode-cn.com/problems/3sum/
     */

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if (nums[i] > 0){
                return result;
            }
            int curr = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int temp = curr + nums[left] + nums[right];
                if (temp == 0){
                    List<Integer> arrList = new ArrayList<>();
                    arrList.add(curr);
                    arrList.add(nums[left]);
                    arrList.add(nums[right]);
                    result.add(arrList);
                    // 去重
                    while (left < right && nums[left + 1] == nums[left]){
                        left++;
                    }
                    while(left <right && nums[right - 1] == nums[right]){
                        right--;
                    }
                    left++;
                    right--;
                }else if (temp < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return result;
    }
}
