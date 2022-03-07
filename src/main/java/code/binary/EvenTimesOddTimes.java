package code.binary;

/**
 * @author: wxm
 * @created: 2022/03/04
 */
public class EvenTimesOddTimes {

    /**
     * 给定一个数组，只有一种数出现了奇数次，打印这个数
     */
    public static void getOneOddTimes(int[] arr){
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        System.out.println(result);
    }

    /**
     * 给定一个数组，只有两种数出现了奇数次，打印这两种数
     */

    public static void getTwoOddTimes(int[] arr){
        // temp为两种奇数的异或值
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp ^= arr[i];
        }
        // 最右侧的1提出出来
        // temp :     00110010110111000
        // onlyOne :  00000000000001000
        int onlyOne = temp & (-temp);
        int resultOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & onlyOne) != 0){
                resultOne ^= arr[i];
            }
        }
        System.out.println(resultOne +"  " + (resultOne ^ temp));
    }
}
