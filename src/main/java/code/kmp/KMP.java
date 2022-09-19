package code.kmp;

/**
 * @author: wxm
 * @created: 2022/09/19
 */
public class KMP {

    /**
     * KMP算法的作用是在一个已知字符串中查找子串的位置,也叫做串的模式匹配
     */

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || str.length() == 0 || str.length() < match.length()) {
            return -1;
        }
        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();
        int[] next = getNextArray(matchArr);
        int x = 0, y = 0;
        while (x < strArr.length && y < matchArr.length) {
            if (strArr[x] == matchArr[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == matchArr.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < match.length) {
            if (match[index - 1] == match[cn]) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
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
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
