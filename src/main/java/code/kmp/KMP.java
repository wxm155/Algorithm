package code.kmp;

/**
 * @author: wxm
 * @created: 2022/09/19
 */
public class KMP {

    /**
     * KMP算法：作用在一个已知字符串中查找子串的位置,也叫做串的模式匹配
     * KMP的精髓在于匹配到不符合的字符串时存在两个加速过程，
     * 1、通过next数组右移跨过的字符串一定不匹配，不用匹配
     * 2、右移后匹配串的开始位置到已知串刚匹配到的位置一定相等，不用匹配。
     */

    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || str.length() == 0 || str.length() < match.length()) {
            return -1;
        }
        char[] strArr = str.toCharArray();
        char[] matchArr = match.toCharArray();
        int[] next = getNextArray(matchArr);
        int x = 0, y = 0;
        // a a b a a b a a k
        // a a b a a b a a t
        // 依次匹配到k != t时,继续k和b比较
        //       a a b a a b a a t
        // kmp的精髓：
        // 1、移动的三个位置的字符串不用匹配，一定匹配不出匹配串
        // 2、strArr[a..a]和matchArr[a..a]之间一定相等，不用匹配，直接匹配k == b
        // 不得不佩服先贤的智慧
        //
        // 已知串长度为N，匹配串长度为M
        // 时间复杂度 O(N)，暴力解的时间复杂度为O(N * M)
        // while中三个分支只会进一个
        //              x       x - y
        // 第一个分支：   ↑         ↑
        // 第二个分支：   ↑        不变
        // 第三个分支：  不变     y变小整体↑
        // 时间复杂度随着x的变化呈线性变化，即 O(N)
        while (x < strArr.length && y < matchArr.length) {
            // 相等，继续匹配
            if (strArr[x] == matchArr[y]) {
                x++;
                y++;
                // 匹配到最开始位置没匹配上
            } else if (next[y] == -1) {
                x++;
                // y位置继续往前跳
            } else {
                y = next[y];
            }
        }
        return y == matchArr.length ? x - y : -1;
    }

    // next数组：记录匹配串每个位置最长相等前缀串和后缀串的位置,不包含当前位置且不包含整体
    // a a b a a b a a k       a a b a a b a a t
    //                   next -1 0 1 0 1 2 3 4 5
    public static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        // 0,1位置规定为-1，0
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < match.length) {
            // match[index - 1]和match[cn]相等，最长前缀串只需cn + 1
            if (match[index - 1] == match[cn]) {
                // ++cn 即满足最长前缀串长度，也调整了cn的位置
                next[index++] = ++cn;
                // cn > 0 继续往前跳
            } else if (cn > 0) {
                cn = next[cn];
                // 都不匹配
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
