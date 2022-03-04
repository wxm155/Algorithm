package code.day01;

/**
 * 打印整数的二进制
 * @author: wxm
 * @created: 2022/02/08
 */
public class Code01_PrintIntegerBinary {

    public static void main(String[] args){
//        int num = 60;
//        print(num);
//        System.out.println(1 << 8);
        int a = 5;
        print(a);
        int b = 2;
        print(b);
//        print( a & b);
//        print( a | b);
        print( a ^ b);
    }

    public static void print(int num){
        for(int i = 31; i >= 0; i--){
            System.out.print((num & (1 << i)) == 0 ? "0":"1");
        }
        System.out.println();
    }

    /**
     *  ~ 取反  00000000 00000000 00000000 10000000 取反为  11111111 11111111 11111111 01111111
     *  & 与  都为1则为1，其余则为0
     *  | 或  有1则为1
     *  ^ 异或  相同为0，不同为1  0^N = N; N^N = 0;
     *  >> 右移 左边用符号位来补
     *  >>> 右移  左边不用符号位来补，用0来补
     *  1 << 8  ==> 00000000 00000000 00000000 10000000   计算为2的8次方
     *
     *  00000000 00000000 00000000 10000000 首位为符号位，0为整数，1为负数，
     *  负数的值为除首位外的后面的值取反+1，便于加减乘除的的运算逻辑统一。
     *  例：-5的二进制表示方式为 ~5 + 1   ~N + 1 等于 -N
     */
}
