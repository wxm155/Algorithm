package code.sort;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/03/24
 */
public class ThreeSumClosest {

    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     *
     *示例 1：
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     *
     * 示例 2：
     * 输入：nums = [0,0,0], target = 1
     * 输出：0
     *
     * 力扣：https://leetcode-cn.com/problems/3sum-closest/
     *
     * 思路：先排序，双指针 相似 {@link ThreeSum}
     */

    public static int threeSumClosest(int[] nums, int target) {

        if (nums == null || nums.length < 3){
            return -1;
        }
        Arrays.sort(nums);
        // 标志值
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int temp = curr + nums[left] + nums[right];
                if (Math.abs(target -temp) < Math.abs(target -result)){
                    result = temp;
                }
                if (temp == target){
                    return temp;
                }else if (temp > target){
                    right--;
                }else {
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,0};
        int result = threeSumClosest(arr, -100);
        System.out.println(result);
    }
}
