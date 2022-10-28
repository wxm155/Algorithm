package code.highFrequency;

/**
 * @author: wxm
 * @created: 2022/10/28
 */
public class MinSwapStep {

    /**
     * 一个数组中只有两种字符'G'和'B'，
     * 可以让所有的G都放在左侧，所有的B都放在右侧
     * 或者可以让所有的G都放在右侧，所有的B都放在左侧，
     * 但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
     */

    public static int minSteps1(String s) {
        if (s == null || s == "") {
            return 0;
        }
        char[] arr = s.toCharArray();
        int gi = 0, step1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                // G换到左边所需要交换的次数
                step1 += i - (gi++);
            }
        }
        int bi = 0, step2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'B') {
                // B换到左边所需要交换的次数
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    public static int minSteps2(String s) {
        if (s == null || s == "") {
            return 0;
        }
        char[] arr = s.toCharArray();
        int gi = 0, step1 = 0, bi = 0, step2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'G') {
                // G换到左边所需要交换的次数
                step1 += i - (gi++);
            } else {
                // B换到左边所需要交换的次数
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    // for test
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str = randomString(maxLen);
            int ans1 = minSteps1(str);
            int ans2 = minSteps2(str);
            if (ans1 != ans2) {
                System.out.println("fuck.....");
            }
        }
        System.out.println("测试结束");
    }
}
