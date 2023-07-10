package code.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2023/07/10
 */
public class WorldBreak {

    /**
     * 假设所有字符都是小写字母，大字符串是str，arr是去重的单词表, 每个单词都不是空字符串且可以使用任意次。
     * 使用arr中的单词有多少种拼接str的方式，返回方法数。
     */

    /**
     * 时间复杂度：O(N^3)
     *
     * @param str
     * @param arr
     * @return
     */
    public static int ways1(String str, String[] arr) {
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        return process1(str, 0, set);
    }

    /**
     * 标识从arr[index....]能被set中的字符串分解的话，返回分解的方法数
     *
     * @param str
     * @param index
     * @param set
     * @return
     */
    public static int process1(String str, int index, Set<String> set) {
        if (index == str.length()) {
            return 1;
        }
        int ways = 0;
        for (int end = index; end < str.length(); end++) {
            String pre = str.substring(index, end + 1);
            if (set.contains(pre)) {
                ways += process1(str, end + 1, set);
            }
        }
        return ways;
    }

    /**
     * 动态规划解法
     *
     * @param str
     * @param arr
     * @return
     */
    public static int ways2(String str, String[] arr) {
        if (str == null || str.length() == 0 || arr == null || arr.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        int len = str.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int end = index; end < len; end++) {
                String pre = str.substring(index, end + 1);
                if (set.contains(pre)) {
                    dp[index] += dp[end + 1];
                }
            }
        }
        return dp[0];
    }

    /**
     * fot test
     */
    public static class RandomSample {
        public String str;
        public String[] arr;

        public RandomSample(String s, String[] a) {
            str = s;
            arr = a;
        }
    }

    /**
     * for test
     *
     * @param candidates
     * @param num
     * @param len
     * @param joint
     * @return
     */
    public static RandomSample generateRandomSample(char[] candidates, int num, int len, int joint) {
        String[] seeds = randomSeeds(candidates, num, len);
        HashSet<String> set = new HashSet<>();
        for (String str : seeds) {
            set.add(str);
        }
        String[] arr = new String[set.size()];
        int index = 0;
        for (String str : set) {
            arr[index++] = str;
        }
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < joint; i++) {
            all.append(arr[(int) (Math.random() * arr.length)]);
        }
        return new RandomSample(all.toString(), arr);
    }

    /**
     * for test
     *
     * @param candidates
     * @param num
     * @param len
     * @return
     */
    public static String[] randomSeeds(char[] candidates, int num, int len) {
        String[] arr = new String[(int) (Math.random() * num) + 1];
        for (int i = 0; i < arr.length; i++) {
            char[] str = new char[(int) (Math.random() * len) + 1];
            for (int j = 0; j < str.length; j++) {
                str[j] = candidates[(int) (Math.random() * candidates.length)];
            }
            arr[i] = String.valueOf(str);
        }
        return arr;
    }

    public static void main(String[] args) {
        char[] candidates = {'a', 'b'};
        int num = 20;
        int len = 4;
        int joint = 5;
        int testTimes = 30000;
        boolean testResult = true;
        for (int i = 0; i < testTimes; i++) {
            RandomSample sample = generateRandomSample(candidates, num, len, joint);
            int ans1 = ways1(sample.str, sample.arr);
            int ans2 = ways2(sample.str, sample.arr);
            if (ans1 != ans2) {
                testResult = false;
                break;
            }
        }
        if (testResult) {
            System.out.println("测试通过。");
        } else {
            System.out.println("fuck....");
        }
    }
}
