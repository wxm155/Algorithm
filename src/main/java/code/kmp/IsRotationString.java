package code.kmp;

/**
 * @author: wxm
 * @created: 2022/09/21
 */
public class IsRotationString {

    /**
     * 判断str1和str2是否互为旋转字符串
     * 例：abcdef
     * abcdef,bcdefa,cdefab,defabc,efabcd,fabcde为abcdef的旋转串
     */

    public static boolean isRotation(String str1,String str2){
        if (str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        // 转换为str2是否为str1 + str1的子串问题
        String temp = str1 + str1;
        int index = getIndexOf(temp, str2);
        return index != -1;
    }

    // KMP
    public static int getIndexOf(String str, String match) {
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

    public static void main(String[] args) {
        String str1 = "wangxianming";
        String str2 = "xianmingwang";
        System.out.println(isRotation(str1, str2));

    }

}
