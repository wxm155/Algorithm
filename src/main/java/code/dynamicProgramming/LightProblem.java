package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/07/14
 */
public class LightProblem {

    /**
     * 给定一个数组arr，长度为N，arr中的值不是0就是1
     * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
     * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
     * 问题一：
     * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
     * 排成一条直线说明：
     * i为中间位置时，i号灯的开关能影响i-1、i和i+1
     * 0号灯的开关只能影响0和1位置的灯
     * N-1号灯的开关只能影响N-2和N-1位置的灯
     * <p>
     * 问题二：
     * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
     * 排成一个圈说明：
     * i为中间位置时，i号灯的开关能影响i-1、i和i+1
     * 0号灯的开关能影响N-1、0和1位置的灯
     * N-1号灯的开关能影响N-2、N-1和0位置的灯
     */

    /**
     * 递归解法
     *
     * @param arr
     * @return
     */
    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        // 不改变arr[0]的状态
        int p1 = process(arr, 2, arr[0], arr[1]);
        // 改变arr[0]的状态
        int p2 = process(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    /**
     * 以当前位置按下开关影响左边和当前节点，从左往右的尝试模型
     * 时间复杂度：O(N),递归只会走一个if分支
     *
     * @param arr
     * @param nextIndex
     * @param preStatus
     * @param curStatus
     * @return
     */
    public static int process(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) {
            return preStatus == curStatus ? curStatus ^ 1 : Integer.MAX_VALUE;
        }
        // 前面的灯是关着的状态，当前必须按下开关
        if (preStatus == 0) {
            curStatus ^= 1;
            int next = process(arr, nextIndex + 1, curStatus, arr[nextIndex] ^ 1);
            return next == Integer.MAX_VALUE ? next : next + 1;
        } else {
            // 当前节点不需要按下开关
            return process(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    /**
     * 非递归解法
     *
     * @param arr
     * @return
     */
    public static int noLoopRight1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] == arr[1] ? arr[0] ^ 1 : Integer.MAX_VALUE;
        }
        int p1 = process1(arr, arr[0], arr[1]);
        int p2 = process1(arr, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    /**
     * 递归版本每次直走一个if的分支，只需有限的变量即可实现动态规划
     *
     * @param arr
     * @param preStatus
     * @param curStatus
     * @return
     */
    public static int process1(int[] arr, int preStatus, int curStatus) {
        int nextIndex = 2;
        int res = 0;
        while (nextIndex != arr.length) {
            if (preStatus == 0) {
                res++;
                preStatus = curStatus ^ 1;
                curStatus = arr[nextIndex] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[nextIndex];
            }
            nextIndex++;
        }
        return preStatus == curStatus ? (res + (preStatus ^ 1)) : Integer.MAX_VALUE;
    }

    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("begin...");
        int testTime = 200000;
        int lenMax = 12;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = noLoopRight(arr);
            int ans2 = noLoopRight1(arr);
            if (ans1 != ans2) {
                System.out.println("fuck....");
                break;
            }
        }
        System.out.println("end...");
    }
}
