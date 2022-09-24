package code.manacher;

/**
 * @author: wxm
 * @created: 2022/09/23
 */
public class Manacher {

    /**
     * Manacher算法：查找一个字符串的最长回文子串的线性算法
     */

    public static int manacher(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] strArr = manacherStr(str);
        int[] pArr = new int[strArr.length];
        int c = -1, r = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < strArr.length; i++) {
            // 2 * c - i 为i对称的位置
            pArr[i] = r > i ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i + pArr[i] < strArr.length && i - pArr[i] > -1) {
                if (strArr[i + pArr[i]] == strArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // r扩的更远
            int temp = i + pArr[i];
            if (temp > r) {
                r = temp;
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // 121 => #1#2#1#  回文半径为4
        // 1221 => #1#2#2#1#  回文半径为5
        return max - 1;
    }


    // 123  => #1#2#3#
    // 将回文子串奇数和偶数统一计算
    public static char[] manacherStr(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherStr(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("fuck.....");
            }
        }
        System.out.println("test finish");
    }
}
