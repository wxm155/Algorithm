package code.highFrequency;

import java.util.HashMap;

/**
 * @author: wxm
 * @created: 2023/07/20
 */
public class StepSum {

    /**
     * 定义何为step sum？比如680，680 + 68 + 6 = 754，680的step sum叫754。
     * 给定一个正数num，判断它是不是某个数的step sum
     */

    /**
     * 利用stepNum的单调性，二分查找
     *
     * @param stepNum
     * @return
     */
    public static boolean stepSum(int stepNum) {
        int l = 0;
        int r = stepNum;
        int m = 0;
        int cur = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            cur = step(m);
            if (cur == stepNum) {
                return true;
            } else if (cur > stepNum) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public static int step(int num) {
        int step = 0;
        while (num != 0) {
            step += num;
            num /= 10;
        }
        return step;
    }

    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(step(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = step(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (stepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
