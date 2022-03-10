package code.array;

/**
 * @author: wxm
 * @created: 2022/03/10
 */
public class MaxArea {

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 力扣：https://leetcode-cn.com/problems/container-with-most-water/
     *
     * 解题思路：利用双指针
     */

    /**
     * 双指针(最优解)
     * 每次移动值小的指针
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxValue = 0;
        while (left < right) {
            maxValue = height[right] > height[left] ?
                    Math.max(maxValue, (right - left) * height[left++]) :
                    Math.max(maxValue, (right - left) * height[right--]);
        }
        return maxValue;
    }

    /**
     * 暴力解法
     *
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int maxValue = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxValue = Math.max(maxValue, ((j - i) * Math.min(height[j], height[i])));
            }
        }
        return maxValue;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500000; i++) {
            int[] arr = generateRandomArray(20, 50);
            int result1 = maxArea(arr);
            int result2 = maxArea2(arr);
            if (result1 != result2) {
                System.out.println("fuck....");
            }
        }
    }
}
