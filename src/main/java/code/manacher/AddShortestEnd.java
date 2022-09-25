package code.manacher;

/**
 * @author wxm
 * @created 2022/9/25
 */
public class AddShortestEnd {

    /**
     * 给定一个字符串str，只能在str的后面添加字符，想让str整体变成回文串，返回至少要添加几个字符,
     * 扩展返回添加字符串的结果。
     */

    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] strArr = manacherStr(str);
        int[] pArr = new int[strArr.length];
        int R = -1, C = -1, end = -1;
        for (int i = 0; i < strArr.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < strArr.length && i - pArr[i] > -1) {
                if (strArr[i + pArr[i]] == strArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            int temp = i + pArr[i];
            if (temp > R) {
                R = temp;
                C = i;
            }
            // 以最后一个字符串为结尾的回文子串
            if (R == strArr.length) {
                end = pArr[i];
                break;
            }
        }
        // 生成以最后一个字符串为结尾的回文子串的前面字符
        char[] res = new char[str.length() - end + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = strArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static char[] manacherStr(String str) {
        char[] strArr = str.toCharArray();
        char[] res = new char[strArr.length * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : strArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
