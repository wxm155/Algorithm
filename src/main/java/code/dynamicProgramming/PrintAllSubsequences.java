package code.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2022/07/29
 */
public class PrintAllSubsequences {

    /**
     * 给定一个字符串，打印所有的子序列
     */

    public static List<String> subs(String s) {
        char[] charArray = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process(charArray, 0, ans, path);
        return ans;
    }

    /**
     * 每次移动到一个位置时，出现要当前字符和不要当前字符两种情况，最终生成树的叶子节点就是结果集
     *
     * @param arr
     * @param index
     * @param ans
     * @param path
     */
    public static void process(char[] arr, int index, List<String> ans, String path) {
        if (index == arr.length) {
            ans.add(path);
            return;
        }
        // 不要index位置的字符
        process(arr, index + 1, ans, path);
        // 要index位置的字符
        process(arr, index + 1, ans, path + arr[index]);
    }


    // 不允许出现重复值
    public static List<String> subs2(String s) {
        char[] charArray = s.toCharArray();
        Set<String> ans = new HashSet<>();
        String path = "";
        process2(charArray,0,ans,path);
        List<String> res = new ArrayList<>();
        for (String an : ans) {
            res.add(an);
        }
        return res;
    }

    public static void process2(char[] arr, int index, Set<String> ans,String path){
        if (index == arr.length){
            ans.add(path);
            return;
        }
        process2(arr,index + 1,ans,path);
        process2(arr,index + 1,ans,path + arr[index]);
    }


    public static void main(String[] args) {
        String test = "acc";
        List<String> subs = subs(test);
        System.out.println(subs);

        List<String> subs2 = subs2(test);
        System.out.println(subs2);
    }
}
