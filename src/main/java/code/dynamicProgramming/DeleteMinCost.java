package code.dynamicProgramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/06/21
 */
public class DeleteMinCost {

    /**
     * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
     * 比如 s1 = "abcde"，s2 = "axbc"，s2删掉'x'即可，返回1
     */

    /**
     * 如果s2的字符串很小，解法不会有什么问题，如果s2的字符串很长，子串的数量会很多，代价会很大
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost1(String s1, String s2) {
        List<String> list = new ArrayList<>();
        process(s2.toCharArray(), 0, "", list);
        // 长度长的在前面
        list.sort(new LenComparator());
        for (String str : list) {
            // indexOf底层与KMP算法代价一样，可替换成KMP
            if (s1.indexOf(str) != -1) {
                return s2.length() - str.length();
            }
        }
        return s2.length();
    }

    /**
     * 生成所有的子串
     *
     * @param arr
     * @param index
     * @param path
     * @param list
     */
    public static void process(char[] arr, int index, String path, List<String> list) {
        if (index == arr.length) {
            list.add(path);
            return;
        }
        // 不要当前字符
        process(arr, index + 1, path, list);
        // 要当前字符
        process(arr, index + 1, path + arr[index], list);
    }


    public static int minCost2(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;
        // dp为前缀长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len1; i++) {
            // 右上半部分无效
            for (int j = 1; j <= Math.min(i, len2); j++) {
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (arr1[i - 1] == arr2[j - 1] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static class LenComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    /**
     * for test
     * @param l
     * @param v
     * @return
     */
    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 100;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
            if (ans1 != ans2) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }

}
