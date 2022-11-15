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

    // 初始化，填充sum[]
    public void build(int l, int r, int rt) {
        if (l == r) {
            sum[rt] = arr[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, rt << 1);
        build(mid + 1, r, rt << 1 | 1);
        pushUp(rt);
    }

    // 更新
    public void update(int L, int R, int C, int l, int r, int rt) {
        // 任务全包含，懒住
        if (L <= l && r <= R) {
            update[rt] = true;
            change[rt] = C;
            sum[rt] = C * (r - l + 1);
            lazy[rt] = 0;
            return;
        }
        // 无法懒住，往下发
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            update(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            update(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    // 添加
    public void add(int L, int R, int C, int l, int r, int rt) {
        // 任务把此时的范围全包含
        if (L <= l && r <= R) {
            sum[rt] += C * (r - l + 1);
            lazy[rt] += C;
            return;
        }
        // 没有全包含
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        if (L <= mid) {
            add(L, R, C, l, mid, rt << 1);
        }
        if (R > mid) {
            add(L, R, C, mid + 1, r, rt << 1 | 1);
        }
        pushUp(rt);
    }

    // 查询
    public long query(int L, int R, int l, int r, int rt) {
        if (L <= l && r <= R) {
            return sum[rt];
        }
        int mid = (l + r) >> 1;
        pushDown(rt, mid - l + 1, r - mid);
        long ans = 0;
        if (L <= mid) {
            ans += query(L, R, l, mid, rt << 1);
        }
        if (R > mid) {
            ans += query(L, R, mid + 1, r, rt << 1 | 1);
        }
        return ans;
    }


    // 求sum[rt]
    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    // 所有懒增加，和懒更新，从父范围，发给左右两个子范围
    // ln表示左子树元素结点个数，rn表示右子树结点个数
    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            update[rt << 1] = true;
            update[rt << 1 | 1] = true;
            change[rt << 1] = change[rt];
            change[rt << 1 | 1] = change[rt];
            lazy[rt << 1] = 0;
            lazy[rt << 1 | 1] = 0;
            sum[rt << 1] = change[rt] * ln;
            sum[rt << 1 | 1] = change[rt] * rn;
            update[rt] = false;
        }
        if (lazy[rt] != 0) {
            lazy[rt << 1] += lazy[rt];
            sum[rt << 1] += lazy[rt] * ln;
            lazy[rt << 1 | 1] += lazy[rt];
            sum[rt << 1 | 1] += lazy[rt] * rn;
            lazy[rt] = 0;
        }
    }
}
