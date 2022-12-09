package code.highFrequency;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/12/02
 */
public class BoatsToSavePeople {

    /**
     * 给定一个正数数组arr，代表若干人的体重，再给定一个正数limit，表示所有船共同拥有的载重量，
     * 每艘船最多坐两人，且不能超过载重 想让所有的人同时过河，
     * 并且用最好的分配方法让船尽量少，返回最少的船数
     */

    /**
     * 首尾双指针
     * @param people 人体重数组
     * @param limit 船限制重量
     * @return  最少的船数
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int ans = 0;
        int l = 0, r = people.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            // 大于limit就自己一个人坐一船，小于就两个人坐一船
            if (sum > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans++;
        }
        return ans;
    }
}
