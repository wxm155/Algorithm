package code.dichotomy;

/**
 * @author: wxm
 * @created: 2022/03/04
 */
public class LessIndex {

    /**
     * 给定一个无序数组，任意两个相邻的数不相等，查找局部最小值
     *
     * 头部判断，如果为上升趋势，必然头部区间存在最小值，
     * 尾部判断，如果为下降趋势，必然尾部存在最小值，
     * 然后判断中间趋势，左边下降，右边上升必然中间存在最小值，
     * 可以借鉴思想，此算法不够严谨，无法进行批量校验
     */

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        // 头部
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        // 尾部
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int start = 1;
        int end = arr.length -2;
        int mid = 0;
        while(start < end){
             mid = start + ((end -start) >> 1);
            if (arr[mid] > arr[mid -1]){
                end = mid -1;
            }else if (arr[mid] < arr[mid +1]){
                start = mid +1;
            }else{
                return mid;
            }
        }
        return start;
    }

    /**
     * 生成随机数组
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] ints = generateRandomArray(20, 50);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        System.out.println();
        System.out.println(ints[getLessIndex(ints)]);
//
//        int[] arr = {32,7,-13,-2,-17,6,5,10,-7,-17,3};
//        getLessIndex(arr);
    }
}
