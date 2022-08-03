package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/01
 */
public class CardsInLine {

    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
     * 玩家A和玩家B依次拿走每张纸牌 规定玩家A先拿，玩家B后拿
     * 但是每个玩家每次只能拿走最左或最右的纸牌
     * 玩家A和玩家B都绝顶聪明 请返回最后获胜者的分数
     */

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0){
            return -1;
        }
        int first = first1(arr, 0, arr.length - 1);
        int second = second1(arr,0,arr.length - 1);
        return Math.max(first,second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int first1(int[] arr, int L, int R) {
        // 先手拿牌，获得最后值
        if (L == R) {
            return arr[L];
        }

        int left = arr[L] + second1(arr, L + 1, R);
        int right = arr[R] + second1(arr, L, R - 1);
        return Math.max(left, right);
    }

    // arr[L..R]，后手获得的最好分数返回
    public static int second1(int[] arr, int L, int R) {
        // 后手拿牌，只有一张时为0
        if (L == R) {
            return 0;
        }
        // 对手拿了L位置的数
        int left = first1(arr, L + 1, R);
        // 对手拿了R位置的数
        int right = first1(arr, L, R - 1);
        // 后手拿牌由先手决定，只能获取最小值
        return Math.min(left, right);
    }

    /**
     * 1,2,3,4,5
     *          f(0,4)
     *         /     \
     *    f(1,4)      f(0,3)
     *    /    \       /   \
     * f(2,4) f(1,3) f(1,3) f(0,2)
     *
     * 上述过程中出现了f(1,3)重复计算的过程
     *
     */
    public static int win2(int[] arr) {

        int len = arr.length;
        int[][] fMap = new int[len][len];
        int[][] sMap = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                fMap[i][j] = -1;
                sMap[i][j] = -1;
            }
        }
        int first2 = first2(arr, 0, arr.length - 1, fMap, sMap);
        int second2 = second2(arr, 0, arr.length - 1, fMap, sMap);
        return Math.max(first2, second2);
    }

    public static int first2(int[] arr, int L, int R, int[][] fMap, int[][] sMap) {
        if (fMap[L][R] != -1) {
            return fMap[L][R];
        }
        if (L == R) {
            return arr[L];
        }
        int left = arr[L] + second2(arr, L + 1, R, fMap, sMap);
        int right = arr[R] + second2(arr, L, R - 1, fMap, sMap);
        int max = Math.max(left, right);
        fMap[L][R] = max;
        return max;
    }

    public static int second2(int[] arr, int L, int R, int[][] fMap, int[][] sMap) {
        if (sMap[L][R] != -1) {
            return sMap[L][R];
        }
        if (L == R) {
            return 0;
        }
        int left = first2(arr, L + 1, R, fMap, sMap);
        int right = first2(arr, L, R - 1, fMap, sMap);
        int min = Math.min(left, right);
        sMap[L][R] = min;
        return min;
    }

    /**
     * 动态规划最终解法
     * @param arr
     * @return
     */
    public static int win3(int[] arr) {
        int len = arr.length;
        int[][] fMap = new int[len][len];
        int[][] sMap = new int[len][len];
        // 相当于first()1的
        // if (L == R) {
        //    return arr[L];
        // }
        for (int i = 0; i < len; i++) {
            fMap[i][i] = arr[i];
        }
        for (int i = 1; i < len; i++) {
            int R = i;
            int L = 0;
            while (R < len) {
                // 相当于first1()的
                // int left = arr[L] + second1(arr, L + 1, R);
                // int right = arr[R] + second1(arr, L, R - 1);
                // return Math.max(left, right);
                fMap[L][R] = Math.max(arr[L] + sMap[L + 1][R], arr[R] + sMap[L][R - 1]);
                // 相当于second1()的
                // int left = first1(arr, L + 1, R);
                // int right = first1(arr, L, R - 1);
                // return Math.min(left, right);
                sMap[L][R] = Math.min(fMap[L + 1][R], fMap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fMap[0][len - 1], sMap[0][len - 1]);
    }


    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
