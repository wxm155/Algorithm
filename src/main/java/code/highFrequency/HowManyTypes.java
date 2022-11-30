package code.highFrequency;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2022/11/30
 */
public class HowManyTypes {

    /**
     * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
     * 如果其中某两个字符串所含有的字符种类完全一样 就将两个字符串算作一类，
     * 比如baacbba和bac就算作一类，返回arr中有多少类
     */

    /**
     * @param str String数组
     * @return 类型数
     */
    public static int manyTypes(String[] str) {
        Set<String> set = new HashSet<>();
        for (String string : str) {
            char[] chars = string.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < chars.length; i++) {
                map[chars[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key += String.valueOf((char) i + 'a');
                }
            }
            set.add(key);
        }
        return set.size();
    }

    /**
     * @param str String数组
     * @return 类型数
     */
    public static int manyTypes2(String[] str) {
        Set<Integer> set = new HashSet<>();
        for (String string : str) {
            char[] chars = string.toCharArray();
            Integer key = 0;
            for (int i = 0; i < chars.length; i++) {
                key |= (1 << (chars[i] - 'a'));
            }
            set.add(key);
        }
        return set.size();
    }

    // for test
    public static String[] getRandomStringArray(int possibilities, int strMaxSize, int arrMaxSize) {
        String[] ans = new String[(int) (Math.random() * arrMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = getRandomString(possibilities, strMaxSize);
        }
        return ans;
    }

    // for test
    public static String getRandomString(int possibilities, int strMaxSize) {
        char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strMaxSize = 10;
        int arrMaxSize = 100;
        int testTimes = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr = getRandomStringArray(possibilities, strMaxSize, arrMaxSize);
            int ans1 = manyTypes(arr);
            int ans2 = manyTypes2(arr);
            if (ans1 != ans2) {
                System.out.println("fuck......");
            }
        }
        System.out.println("test finish");

    }
}
