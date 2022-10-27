package code.highFrequency;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wxm
 * @created 2022/10/27
 */
public class CountFiles {

    /**
     * 给定一个文件目录的路径，
     * 写一个函数统计这个目录下所有的文件数量并返回，
     * 隐藏文件也算，但是文件夹不算
     */

    // 二叉树的深度优先遍历，文件夹为非叶子节点，文件为叶子节点
    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int count = 0;
        Stack<File> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    count++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return count;
    }

    // 宽度优先遍历
    public static int getFileNumber2(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        int count = 0;
        Queue<File> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            File folder = queue.poll();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    count++;
                }
                if (next.isDirectory()) {
                    queue.offer(next);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\wxm\\Desktop\\test";
        System.out.println(getFileNumber(path));
        System.out.println(getFileNumber2(path));
    }
}
