package code.array;

/**
 * @author: wxm
 * @created: 2022/03/16
 */
public class NetherlandsFlag {

    /**
     * 荷兰国旗问题，给定一个数组，以数组最后一个数作为划分值，
     * 将数组划分为 小于arr[arr.length -1]、等于arr[arr.length -1]、大于arr[arr.length -1],可以无序
     */

    public static void netherlandsFlag(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int left = -1;
        int right = arr.length -1;
        int index = 0;
        int num = arr[arr.length -1];
        // 左边指针和右边指针分别向中间移动
        while (index < right){
            if (arr[index] < num){
                swap(arr,++left,index++);
            }else if (arr[index] == num){
                index++;
            }else {
                swap(arr,index,--right);
            }
        }
        // 交换划分值的位置
        swap(arr,right,arr.length -1);

    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(10, 50);
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
        netherlandsFlag(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

}
