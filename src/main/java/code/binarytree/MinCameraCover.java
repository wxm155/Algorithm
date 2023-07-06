package code.binarytree;

/**
 * @author: wxm
 * @created: 2023/07/05
 */
public class MinCameraCover {

    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * 计算监控树的所有节点所需的最小摄像头数量。
     * <p>
     * 示例 1：
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     * <p>
     * 示例 2：
     * 输入：[0,0,null,0,null,0,null,null,0]
     * 输出：2
     * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     * <p>
     * 提示：
     * 给定树的节点数的范围是 [1, 1000]。
     * 每个节点的值都是 0。
     * <p>
     * 力扣：https://leetcode.cn/problems/binary-tree-cameras/
     */

    /**
     * 解法一：穷举所有可能性
     *
     * @param root
     * @return
     */
    public int minCameraCover1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        NodeInfo info = process1(root);
        return (int) Math.min(info.uncovered + 1, Math.min(info.coveredNoCamera, info.coveredHasCamera));
    }

    /**
     * 潜台词：root节点的子节点都被覆盖
     *
     * @param root 当前节点
     * @return
     */
    public NodeInfo process1(TreeNode root) {
        if (root == null) {
            return new NodeInfo(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        NodeInfo left = process1(root.left);
        NodeInfo right = process1(root.right);
        // 当前节点被覆盖并且有相机
        // 左孩子和有孩子三种情况下的最小值，当前节点有相机，孩子节点什么情况都可以
        long leftMin = Math.min(left.uncovered, Math.min(left.coveredNoCamera, left.coveredHasCamera));
        long rightMin = Math.min(right.uncovered, Math.min(right.coveredNoCamera, right.coveredHasCamera));
        long coveredHasCamera = leftMin + rightMin + 1;

        // 当前节点被覆盖无相机
        // 取决于孩子节点上最少有一个节点有相机
        long leftHasAndRightNo = left.coveredHasCamera + right.coveredNoCamera;
        long leftNoAndRightHas = left.coveredNoCamera + right.coveredHasCamera;
        long allHas = left.coveredHasCamera + right.coveredHasCamera;
        long coveredNoCamera = Math.min(allHas, Math.min(leftHasAndRightNo, leftNoAndRightHas));

        // 当前节点未被覆盖
        // 左右孩子节点上必须为被覆盖没有相机的情况
        long uncovered = left.coveredNoCamera + right.coveredNoCamera;
        return new NodeInfo(uncovered, coveredNoCamera, coveredHasCamera);
    }

    // 潜台词：x是头节点，x下方的点都被覆盖的情况下
    public static class NodeInfo {
        // x没有被覆盖，x为头的树至少需要几个相机
        public long uncovered;
        // x被相机覆盖，但是x没相机，x为头的树至少需要几个相机
        public long coveredNoCamera;
        // x被相机覆盖了，并且x上放了相机，x为头的树至少需要几个相机
        public long coveredHasCamera;

        public NodeInfo(long uncovered, long coveredNoCamera, long coveredHasCamera) {
            this.uncovered = uncovered;
            this.coveredNoCamera = coveredNoCamera;
            this.coveredHasCamera = coveredHasCamera;
        }
    }

    /**
     * 解法二：贪心优化节点状态
     * @param root
     * @return
     */
    public int minCameraCover2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        NodeStatus status = process2(root);
        return status.status == Status.UNCOVERED ? status.cameras + 1 : status.cameras;
    }

    public NodeStatus process2(TreeNode root) {
        if (root == null) {
            return new NodeStatus(Status.COVERED_NO_CAMERA, 0);
        }
        NodeStatus left = process2(root.left);
        NodeStatus right = process2(root.right);
        int cameras = left.cameras + right.cameras;
        // 当前节点被覆盖并且有相机
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new NodeStatus(Status.COVERED_HAS_CAMERA, cameras + 1);
        }
        // 当前节点没有被覆盖
        if (left.status == Status.COVERED_NO_CAMERA && right.status == Status.COVERED_NO_CAMERA) {
            return new NodeStatus(Status.UNCOVERED, cameras);
        }
        // 当前节点被覆盖无相机
        return new NodeStatus(Status.COVERED_NO_CAMERA, cameras);
    }

    // 以x为头，x下方的节点都是被覆盖，得到的最优解中：
    // x是什么状态，在这种状态下，需要至少几个相机
    public static class NodeStatus {
        public Status status;
        public int cameras;

        public NodeStatus(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public static enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
