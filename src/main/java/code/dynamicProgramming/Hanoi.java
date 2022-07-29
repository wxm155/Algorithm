package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/07/29
 */
public class Hanoi {

    /**
     * 汉罗塔问题，不改变顺序，将左边移动到右边
     */

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "left", "right", "mid");
        }
    }

    public static void process(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
        } else {
            process(n - 1, from, other, to);
            System.out.println("move " + n + " from " + from + " to " + to);
            process(n - 1, other, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
