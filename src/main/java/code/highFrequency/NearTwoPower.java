package code.highFrequency;

/**
 * @author: wxm
 * @created: 2022/10/28
 */
public class NearTwoPower {

    /**
     * 给定一个非负整数num，如何不用循环语句，返回>=num，
     * 并且离num最近的，2的某次方
     */

    // HashMap的源码实现
    public static final int tableSizeFor(int n) {
        // --防止n本身为2^n扩大一倍
        n--;
        // 最高位的1后面所有的位置全部变成1
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        int test = 50;
        System.out.println(tableSizeFor(test));
    }
}
