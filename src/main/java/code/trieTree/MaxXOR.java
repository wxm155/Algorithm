package code.trieTree;

/**
 * @author: wxm
 * @created: 2023/06/28
 */
public class MaxXOR {

    /**
     * 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，返回arr的最大子数组异或和
     */

    /**
     * 暴力解 O(n^2)
     *
     * @param arr
     * @return
     */
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 生成前缀异或和数组
        int[] xor = new int[arr.length];
        xor[0] = arr[0];
        // xor[i] = arr[0...i]的异或和
        for (int i = 1; i < arr.length; i++) {
            xor[i] = xor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        // 从arr[0...i]、arr[1...i]、arr[2...i].....arr[i...i]依次尝试
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                // xor[i] ^ xor[j - 1] => arr[j-1...i]的异或和
                max = Math.max(max, j == 0 ? xor[i] : xor[i] ^ xor[j - 1]);
            }
        }
        return max;
    }

    /**
     * 前缀树解法，O(N)
     *
     * @param arr
     * @return
     */
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        TrieTreeNum treeNum = new TrieTreeNum();
        treeNum.insert(0);
        // 0..i 整体异或和
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            max = Math.max(max, treeNum.maxXor(xor));
            treeNum.insert(xor);
        }
        return max;
    }

    /**
     * 前缀树
     */
    public static class TrieTreeNum {
        public Node root;

        public TrieTreeNum() {
            this.root = new Node();
        }

        /**
         * 添加
         *
         * @param num
         */
        public void insert(int num) {
            Node node = root;
            for (int i = 31; i >= 0; i--) {
                // 取出num的第i位是0还是1
                int path = ((num >> i) & 1);
                node.next[path] = node.next[path] == null ? new Node() : node.next[path];
                node = node.next[path];
            }
        }

        /**
         * 给定一个数字，在构建好的前缀树中num和谁异或和最大，返回异或最大结果
         *
         * @param num
         * @return
         */
        public int maxXor(int num) {
            Node node = root;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                // path 非0即1
                int path = ((num >> i) & 1);
                // 期望遇到的结果，符号位为符号位期望遇到与自己相同的，其它位期望遇到与自己相反的   path ^ 1 => 取反
                // 如满足不了符号位为0，其它位还是紧着变1，负数值求的是补码
                int best = i == 31 ? path : (path ^ 1);
                // 实际遇到的结果，可能期望的结果不存在
                best = node.next[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位异或完的结果,或到结果中去
                ans |= ((path ^ best) << i);
                node = node.next[best];
            }
            return ans;
        }

    }

    public static class Node {
        public Node[] next;

        public Node() {
            this.next = new Node[2];
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubarray1(arr);
            int res = maxXorSubarray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "fuck....");
    }
}
