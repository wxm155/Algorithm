package code.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wxm
 * @created: 2022/03/24
 */
public class FourSum {

    /**
     * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
     * （若两个四元组元素一一对应，则认为两个四元组重复）：
     * 0 <= a, b, c, d < n
     * a、b、c 和 d 互不相同
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * 你可以按 任意顺序 返回答案 。
     *
     * 示例 1：
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     *
     * 示例 2：
     * 输入：nums = [2,2,2,2,2], target = 8
     * 输出：[[2,2,2,2]]
     *
     * 力扣：https://leetcode-cn.com/problems/4sum/
     *
     * 思路：参考三数之和{@link ThreeSum} 有一些意想不到的问题
     * [2,2,2,2],8
     * [0,0,0,1000000000,1000000000,1000000000,1000000000,1000000000],1000000000
     *
     */

    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4){
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            if ((long)nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target){
                break;
            }
            if ((long)nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target){
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                if ((long)nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target){
                    break;
                }
                if ((long)nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target){
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right){
                    int temp = nums[i] + nums[j] + nums[left] + nums[right];
                    if (target == temp){
                        List<Integer> tempArr = new ArrayList<>();
                        tempArr.add(nums[i]);
                        tempArr.add(nums[j]);
                        tempArr.add(nums[left]);
                        tempArr.add(nums[right]);
                        result.add(tempArr);
                        while (left < right && nums[left] == nums[left + 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right -1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }else if (target > temp){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,1000000000,1000000000,1000000000,1000000000,1000000000};
        System.out.println(fourSum(arr, 1000000000));
    }
}
