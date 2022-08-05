package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/04
 */
public class ConvertToLetterString {

    /**
     * 规定1和A对应、2和B对应、3和C对应...26和Z对应
     * 那么一个数字字符串比如"111”就可以转化为: "AAA"、"KA"和"AK"
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(),0);
    }

    public static int process(char[] arr,int index){
        if (index == arr.length){
            return 1;
        }
        // 出现说明前面的决定是错误的
        if (arr[index] == '0'){
            return 0;
        }
        // 单字符转换
        int res = process(arr,index + 1);
        // 双字符转换
        if (index + 1 < arr.length && ((arr[index] - '0') * 10 + arr[index + 1]  - '0') < 27){
            res += process(arr,index + 2);
        }
        return res;
    }

    // 动态规划最终解法
    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int len = arr.length;
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int index = len - 1; index >= 0; index--) {
            if (arr[index] != '0') {
                int res = dp[index + 1];
                if (index + 1 < len && ((arr[index] - '0') * 10 + arr[index + 1] - '0') < 27) {
                    res += dp[index + 2];
                }
                dp[index] = res;
            }
        }
        return dp[0];
    }

    // for test
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);
            if (ans0 != ans1 ) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println("fuck ....");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
