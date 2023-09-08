package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/06
 */
public class MergeRecord {

    /**
     * 给定整数power，给定一个数组arr，给定一个数组reverse。含义如下：
     * arr的长度一定是2的power次方，reverse中的每个值一定都在0~power范围。
     * 例如power = 2, arr = {3, 1, 4, 2}，reverse = {0, 1, 0, 2}
     * 任何一个在前的数字可以和任何一个在后的数组，构成一对数。可能是升序关系、相等关系或者降序关系。
     * 比如arr开始时有如下的降序对：(3,1)、(3,2)、(4,2)，一共3个。
     * 接下来根据reverse对arr进行调整：
     * reverse[0] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [3,1,4,2]，此时有3个逆序对。
     * reverse[1] = 1, 表示在arr中，划分每2(2的1次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [1,3,2,4]，此时有1个逆序对
     * reverse[2] = 0, 表示在arr中，划分每1(2的0次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [1,3,2,4]，此时有1个逆序对。
     * reverse[3] = 2, 表示在arr中，划分每4(2的2次方)个数一组，然后每个小组内部逆序，那么arr变成
     * [4,2,3,1]，此时有5个逆序对。
     * 所以返回[3,1,1,5]，表示每次调整之后的逆序对数量。
     * <p>
     * 输入数据状况：
     * power的范围[0,20]
     * arr长度范围[1,10的7次方]
     * reverse长度范围[1,10的6次方]
     */

    // 暴力解
    public static int[] mergeRecord(int[] arr, int[] reverse, int power) {
        int[] res = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            reverseArray(arr, 1 << reverse[i]);
            res[i] = reverseNum(arr);
        }
        return res;
    }

    public static void reverseArray(int[] arr, int teamSize) {
        if (teamSize < 2) {
            return;
        }
        for (int j = 0; j < arr.length; j += teamSize) {
            reverse(j, j + teamSize - 1, arr);
        }
    }

    public static void reverse(int start, int end, int[] arr) {
        while (start < end) {
            int temp = arr[end];
            arr[end--] = arr[start];
            arr[start++] = temp;
        }
    }

    public static int reverseNum(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    res++;
                }
            }
        }
        return res;
    }
}
