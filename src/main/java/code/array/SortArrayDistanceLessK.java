package code.array;

import java.util.PriorityQueue;

/**
 * @author: wxm
 * @created: 2022/03/31
 */
public class SortArrayDistanceLessK {

    /**
     * 给定一个无序数组，和一个k，数组每个位置的数在排完序的位置偏差不会超过k，排序该数组
     */

    public static void sortArrayDistanceLessK(int[] arr,int k){
        if (arr == null || arr.length < 2){
            return;
        }
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 将区间内数全部加入小根堆
        for (;index <= Math.min(arr.length - 1,k - 1);index++){
            heap.add(arr[index]);
        }
        int i = 0;
        // 向右移动，加一个弹一个
        for (;index < arr.length;index++,i++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        // 剩余的数
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }

    }

}
