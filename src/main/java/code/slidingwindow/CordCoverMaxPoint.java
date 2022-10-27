package code.slidingwindow;

/**
 * @author wxm
 * @created 2022/10/27
 */
public class CordCoverMaxPoint {

    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点，
     * 给定一个正数K，代表绳子的长度，
     * 返回绳子最多压中几个点？
     * 即使绳子边缘处盖住点也算盖住
     */

    public static int maxPoint(int[] arr, int L) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int l = 0, r = 0, res = 0;
        while (l < N) {
            while (r < N && (arr[r] - arr[l]) <= L) {
                r++;
            }
            res = Math.max(res, r - (l++));
        }
        return res;
    }
}
