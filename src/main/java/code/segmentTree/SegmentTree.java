package code.segmentTree;

/**
 * 线段树是一种二叉搜索树，它将一段区间划分为若干单位区间，每一个节点都储存着一个区间。
 * 功能强大，支持区间求和，区间最大值，区间修改，单点修改等操作。
 * 线段树的思想和分治思想很相像
 * <p>
 * 若一个阶段的下标为i，它的父节点的下标为 i >> 1 ,左子节点为 i << 1,右子节点为 i << 1 | 1
 *
 * @author: wxm
 * @created: 2022/11/15
 */
public class SegmentTree {

    // 线段树的最大数量
    private int max;

    // arr[]为原序列的信息从0开始，arr里从1开始
    private int[] arr;

    // sum[]线段树维护区间和
    private int[] sum;

    // lazy[]为累加和懒惰标记
    private int[] lazy;

    // change[]为更新的值
    private int[] change;

    // update[]为更新慵懒标记
    private boolean[] update;

    public SegmentTree(int[] origin) {
        this.max = origin.length + 1;
        // arr[0]不用,从1开始使用
        this.arr = new int[max];
        for (int i = 1; i < max; i++) {
            arr[i] = origin[i - 1];
        }
        // 最完美的情况，节点数量为2^n
        // 最差情况，节点数量为2^n + 1
        // 所以准备4n的长度一定足够
        int len = max << 2;
        this.sum = new int[len];
        this.lazy = new int[len];
        this.change = new int[len];
        this.update = new boolean[len];
    }

    /**
     * 初始化，填充sum[]
     * @param l 范围左边界
     * @param r 范围有边界
     * @param root 根节点
     */
    public void build(int l, int r, int root) {
        if (l == r) {
            sum[root] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        // 构建左子节点
        build(l, mid, root << 1);
        // 构建右子节点
        build(mid + 1, r, root << 1 | 1);
        // 合并当前节点
        pushUp(root);
    }

    /**
     * 更新
     * @param start 更新范围左边界
     * @param end   更新范围右边界
     * @param value 更新值
     * @param l     线段树的范围左边界
     * @param r     线段树的范围右边界
     * @param root  根节点
     */
    public void update(int start, int end, int value, int l, int r, int root) {
        // 任务全包含，懒住
        if (start <= l && r <= end) {
            update[root] = true;
            change[root] = value;
            sum[root] = value * (r - l + 1);
            lazy[root] = 0;
            return;
        }
        int mid = (l + r) >> 1;
        // 无法懒住，往下发
        pushDown(root, mid - l + 1, r - mid);
        // 左边小于等于中点位置，继续更新
        if (start <= mid) {
            update(start, end, value, l, mid, root << 1);
        }
        // 左边大于重点位置，继续更新
        if (end > mid) {
            update(start, end, value, mid + 1, r, root << 1 | 1);
        }
        // 合并当前节点
        pushUp(root);
    }

    /**
     * 添加
     * @param start 更新范围左边界
     * @param end   更新范围右边界
     * @param value 更新值
     * @param l     线段树的范围左边界
     * @param r     线段树的范围有边界
     * @param root  根节点
     */
    public void add(int start, int end, int value, int l, int r, int root) {
        // 任务把此时的范围全包含
        if (start <= l && r <= end) {
            sum[root] += value * (r - l + 1);
            lazy[root] += value;
            return;
        }
        int mid = (l + r) >> 1;
        // 没有全包含，往下发
        pushDown(root, mid - l + 1, r - mid);
        if (start <= mid) {
            add(start, end, value, l, mid, root << 1);
        }
        if (end > mid) {
            add(start, end, value, mid + 1, r, root << 1 | 1);
        }
        // 合并当前节点
        pushUp(root);
    }

    /**
     * 查询
     * @param start 查询范围左边界
     * @param end   查询范围右边界
     * @param l     线段树的范围左边界
     * @param r     线段树的范围右边界
     * @param root  根节点
     * @return      结果
     */
    public int query(int start, int end, int l, int r, int root) {
        if (start <= l && r <= end) {
            return sum[root];
        }
        int mid = (l + r) >> 1;
        pushDown(root, mid - l + 1, r - mid);
        int ans = 0;
        if (start <= mid) {
            ans += query(start, end, l, mid, root << 1);
        }
        if (end > mid) {
            ans += query(start, end, mid + 1, r, root << 1 | 1);
        }
        return ans;
    }

    // 求sum[root]
    private void pushUp(int root) {
        sum[root] = sum[root << 1] + sum[root << 1 | 1];
    }

    /**
     * 所有懒增加，和懒更新，从父范围，发给左右两个子范围
     * @param root 根节点
     * @param ln   左子树节点个数
     * @param rn   右子树节点个数
     */
    private void pushDown(int root, int ln, int rn) {
        if (update[root]) {
            update[root << 1] = true;
            update[root << 1 | 1] = true;
            change[root << 1] = change[root];
            change[root << 1 | 1] = change[root];
            lazy[root << 1] = 0;
            lazy[root << 1 | 1] = 0;
            sum[root << 1] = change[root] * ln;
            sum[root << 1 | 1] = change[root] * rn;
            update[root] = false;
        }
        if (lazy[root] != 0) {
            lazy[root << 1] += lazy[root];
            sum[root << 1] += lazy[root] * ln;
            lazy[root << 1 | 1] += lazy[root];
            sum[root << 1 | 1] += lazy[root] * rn;
            lazy[root] = 0;
        }
    }

    // for test
    private static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    // for test
    private static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    // for test
    private static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
