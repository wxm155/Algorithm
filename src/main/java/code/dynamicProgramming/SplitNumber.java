package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/16
 */
public class SplitNumber {

    /**
     * 给定一个正数n，求n的裂开方法数，
     * 规定：后面的数不能比前面的数小
     * 比如4的裂开方法有： 1+1+1+1、1+1+2、1+3、2+2、4 5种，
     * 所以返回5
     */

    public static int splitNumber(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        return process1(n,1);
    }

    public static int process1(int rest, int pre) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int res = 0;
        for (int first = pre; first <= rest; first++) {
            res += process1(rest - first, first);
        }
        return res;
    }



    public static void main(String[] args) {
        System.out.println(splitNumber(4));
    }
}
