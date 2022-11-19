package code.highFrequency;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author wxm
 * @created 2022/11/19
 */
public class ChooseWork {

    /**
     * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度，
     * money[i]表示i号工作的收入 给定数组ability，
     * 长度都为M，ability[j]表示j号人的能力，
     * 每一号工作，都可以提供无数的岗位，难度和收入都一样
     * 但是人的能力必须>=这份工作的难度，才能上班。
     * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
     */

    public static int[] chooseWork(int[] hard, int[] money, int[] ability) {
        int len = hard.length;
        Job[] jobs = new Job[hard.length];
        for (int i = 0; i < len; i++) {
            jobs[i] = new Job(hard[i], money[i]);
        }
        // 根据hard递增排序，难度相同再根据money递减排序
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < len; i++) {
            // 剔除掉hard相同，money小于第一个的job，存在无意义
            // 剔除掉hard不同，money小于前一个的job，存在无意义
            // 保证hard难度的递增一定伴随着money的递增
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                pre = jobs[i];
                map.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // 获取小于等于它离它最近的key
            Integer key = map.floorKey(ability[i]);
            ans[i] = key == null ? 0 : map.get(key);
        }
        return ans;
    }

    public static class Job{
        public int hard;
        public int money;

        public Job(int hard,int money){
            this.hard = hard;
            this.money = money;
        }
    }

    // compatator
    public static class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }
}

