package code.binary;

/**
 * @author: wxm
 * @created: 2022/02/08
 */
public class SunOfFactorial {

    /**
     *  给定一个参数N，求 1! + 2！+ 3！+...+ N! 的值
     */

    public static void main(String[] args){

        int n = 4;
        System.out.println(sumOfFactorial(n));

    }

    public static int sumOfFactorial(int n){
        int temp = 1;
        int result = 0;
        for(int i = 1; i <= n; i++){
            temp = temp * i;
            result += temp;
        }
        return result;
    }
}
