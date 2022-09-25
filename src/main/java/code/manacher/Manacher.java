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
        // R 向右扩，扩到最远的位置
        // C 扩到R位置时的中心位置
        int C = -1, R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < strArr.length; i++) {
            // a b c d c k s t s k c d c b a
            // L     i’      c       i     R
            // 存在两种情况：
            // 1、i没有被R罩住，继续暴力往外扩
            // 2、i被R罩住：
            //    1、i‘在L...R中，i的回文半径一定等于i’回文半径，在大回文中关于C对称，不用验。
            //    2、i‘不在L...R中：
            //    a b c d e d c b a t s t a b c d e d c f
            //        L   i’          c           i   R
            //    即i‘的回文区域超过了L...R范围，回文半径为R - i
            // 3、i’的回文左边界和L压线：
            //    x a b c d a s t s a b c b a y
            //      L   i‘      c       i   R
            //    i对应的i‘的回文区域不需要验，继续验已知回文区域左边和右边是否相等
            // 时间复杂度为O(N)
            // 2 * C - i 为i’，下标变换
            // Math.min(pArr[2 * C - i], R - i) => 不需要验的瓶颈
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 跳过不需要验的区域，继续验需要验的区域
            while (i + pArr[i] < strArr.length && i - pArr[i] > -1) {
                if (strArr[i + pArr[i]] == strArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // R扩的更远
            int temp = i + pArr[i];
            if (temp > R) {
                R = temp;
                C = i;
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
