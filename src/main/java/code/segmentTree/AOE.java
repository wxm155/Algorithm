package code.segmentTree;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/11/14
 */
public class AOE {

    /**
     * 给定两个非负数组x和hp，长度都是N，再给定一个正数range x有序，
     * x[i]表示i号怪兽在x轴上的位置 hp[i]表示i号怪兽的血量 再给定一个正数range，
     * 表示如果法师释放技能的范围长度 被打到的每只怪兽损失1点血量。
     * 返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
     */

    // 贪心
    // 1) 总是用技能的最左边缘刮死当前最左侧的没死的怪物
    // 2) 然后向右找下一个没死的怪物，重复步骤1)
    public static int minAoe2(int[] x, int[] hp, int range) {
        // 举个例子：
        // 如果怪兽情况如下，
        // 怪兽所在，x数组  : 2 3 5 6 7 9
        // 怪兽血量，hp数组 : 2 4 1 2 3 1
        // 怪兽编号        : 0 1 2 3 4 5
        // 技能直径，range = 2
        int len = x.length;
        int[] cover = new int[len];
        // 首先求cover数组，
        // 如果技能左边界就在0号怪兽，那么技能到2号怪兽就覆盖不到了
        // 所以cover[0] = 2;
        // 如果技能左边界就在1号怪兽，那么技能到3号怪兽就覆盖不到了
        // 所以cover[1] = 3;
        // 如果技能左边界就在2号怪兽，那么技能到5号怪兽就覆盖不到了
        // 所以cover[2] = 5;
        // 如果技能左边界就在3号怪兽，那么技能到5号怪兽就覆盖不到了
        // 所以cover[3] = 5;
        // 如果技能左边界就在4号怪兽，那么技能到6号怪兽（越界位置）就覆盖不到了
        // 所以cover[4] = 6(越界位置);
        // 如果技能左边界就在5号怪兽，那么技能到6号怪兽（越界位置）就覆盖不到了
        // 所以cover[5] = 6(越界位置);
        // 综上：
        // 如果怪兽情况如下，
        // 怪兽所在，x数组  : 2 3 5 6 7 9
        // 怪兽血量，hp数组 : 2 4 1 2 3 1
        // 怪兽编号        : 0 1 2 3 4 5
        // cover数组情况   : 2 3 5 5 6 6
        // 技能直径，range = 2
        // cover[i] = j，表示如果技能左边界在i怪兽，那么技能会影响i...j-1号所有的怪兽
        // 就是如下的for循环，在求cover数组
        int r = 0;
        for (int i = 0; i < len; i++) {
            if (r < len && x[r] - x[i] <= range) {
                r++;
            }
            cover[i] = r;
        }
        // 我们要让技能的左边界，刮死当前的i号怪兽
        // 这样能够让技能尽可能的往右释放，去尽可能的打掉右侧的怪兽
        // 此时cover[i]，正好的告诉我们，技能影响多大范围。
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (hp[i] > 0) {
                int minHp = hp[i];
                for (int j = i; j < cover[i]; j++) {
                    hp[j] -= minHp;
                }
                res += minHp;
            }
        }
        return res;
    }

    // 线段树解法
    public static int minAoe(int[] x, int[] hp, int range) {
        int len = x.length;
        int[] cover = new int[len];
        int r = 0;
        for (int i = 0; i < len; i++) {
            while (r < len && x[r] - x[i] <= range) {
                r++;
            }
            cover[i] = r;
        }
        SegmentTree st = new SegmentTree(hp);
        st.build(1, len, 1);
        int ans = 0;
        for (int i = 1; i <= len; i++) {
            int leftHp = st.query(1, len, i, i, 1);
            if (leftHp > 0) {
                ans += leftHp;
                st.add(1, len, -leftHp, i, cover[i - 1], 1);
            }
        }
        return ans;
    }


    // for test
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int N = 50;
        int X = 500;
        int H = 60;
        int R = 10;
        int testTime = 500000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x2 = randomArray(len, X);
            Arrays.sort(x2);
            int[] hp2 = randomArray(len, H);
            int[] x3 = copyArray(x2);
            int[] hp3 = copyArray(hp2);
            int range = (int) (Math.random() * R) + 1;
            int ans2 = minAoe2(x2, hp2, range);
            int ans3 = minAoe(x3, hp3, range);
            if (ans2 != ans3) {
                System.out.println("fuck....");
            }
        }
        System.out.println("测试结束");
    }
}
