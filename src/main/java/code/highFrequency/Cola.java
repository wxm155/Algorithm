package code.highFrequency;

/**
 * @author: wxm
 * @created: 2022/11/22
 */
public class Cola {

    /**
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */


    /**
     * @param m 要买可乐的数量
     * @param a 100元的张数
     * @param b 50元的张数
     * @param c 10元的张数
     * @param x 可乐的单价
     * @return 投币的次数
     */
    public static int putTimes(int m, int a, int b, int c, int x) {
        // 面值
        int[] coin = { 100, 50, 10 };
        // 张数
        int[] num = { a, b, c };
        // 投币次数,之前还剩余的钱数,之前还剩余的张数
        int puts = 0, preRestMoney = 0, preRestNums = 0;
        for (int i = 0; i < 3 && m != 0; i++) {
            // 当前的面值付出一些钱+之前剩下的钱，此时有可能凑出一瓶可乐来
            // 当前钱能买的第一瓶可乐的张数
            // (a + (b - 1)) / b  => a / b 向上取整
            int curCoinBuyFirstNums = (x - preRestMoney + coin[i] - 1) / coin[i];
            // 如果当前钱的张数够买第一瓶可乐
            if (num[i] >= curCoinBuyFirstNums) {
                giveRest(coin, num, i + 1, (preRestMoney + curCoinBuyFirstNums * coin[i]) - x, 1);
                puts += curCoinBuyFirstNums + preRestNums;
                num[i] -= curCoinBuyFirstNums;
                m--;
            } else { // 如果当前钱的张数不够买第一瓶的可乐
                preRestMoney += coin[i] * num[i];
                preRestNums += num[i];
                continue;
            }
            // 凑出第一瓶可乐之后，当前的面值有可能能继续买更多的可乐
            // 当前钱买一瓶可乐需要的张数
            int curCoinBuyOneColeNums = (x + coin[i] - 1) / coin[i];
            // 当前钱可以买可乐的数量
            int curCoinBuyNums = Math.min(num[i] / curCoinBuyOneColeNums, m);
            // 当前钱买一瓶可乐的剩余钱数
            int curCoinBuyOneRest = curCoinBuyOneColeNums * coin[i] - x;
            giveRest(coin, num, i + 1, curCoinBuyOneRest, curCoinBuyNums);
            puts += curCoinBuyOneColeNums * curCoinBuyNums;
            m -= curCoinBuyNums;
            // 剩余的金额
            num[i] -= curCoinBuyOneColeNums * curCoinBuyNums;
            preRestMoney = coin[i] * num[i];
            preRestNums = num[i];
        }
        return m == 0 ? puts : -1;

    }

    /**
     * 找的钱加在后续的张数上面
     *
     * @param coins       面值数组
     * @param nums        张数数组
     * @param i           当前钱的下标
     * @param oneTimeRest 一次剩余的钱
     * @param times       次数
     */
    public static void giveRest(int[] coins, int[] nums, int i, int oneTimeRest, int times) {
        for (; i < 3; i++) {
            nums[i] += (oneTimeRest / coins[i]) * times;
            oneTimeRest %= coins[i];
        }
    }

    // 暴力解
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = { 100, 50, 10 };
        int[] zhang = { a,b,c };
        int puts = 0;
        while (m != 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }
        return puts;
    }

    public static int buy(int[] qian, int[] zhang, int rest) {
        int first = -1;
        for (int i = 0; i < 3; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }
        if (qian[first] >= rest) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - rest, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, rest - qian[first]);
            if (next == -1) {
                return -1;
            }
            return 1 + next;
        }
    }


    public static void main(String[] args) {
        int testTime = 1000;
        int zhangMax = 10;
        int colaMax = 10;
        int priceMax = 20;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * colaMax);
            int a = (int) (Math.random() * zhangMax);
            int b = (int) (Math.random() * zhangMax);
            int c = (int) (Math.random() * zhangMax);
            int x = ((int) (Math.random() * priceMax) + 1) * 10;
            int ans1 = putTimes(m, a, b, c, x);
            int ans2 = right(m, a, b, c, x);
            if (ans1 != ans2) {
                System.out.println("int m = " + m + ";");
                System.out.println("int a = " + a + ";");
                System.out.println("int b = " + b + ";");
                System.out.println("int c = " + c + ";");
                System.out.println("int x = " + x + ";");
                System.out.println("fuck.....");
                break;
            }
        }
        System.out.println("test end");
    }
}
