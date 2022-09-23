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
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] strArr = manacherStr(str);
        // TODO....
        return 0;
    }


    // 123  => #1#2#3#
    // 将回文子串奇数和偶数统一计算
    public static char[] manacherStr(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[index++];
        }
        return res;
    }
}
