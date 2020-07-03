package c.p.t.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName twoSum
 * @Description TODO 两数之和
 *          给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *          你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * @Author User
 * @DATE 2020/6/30 14:28
 * @Version 1.0
 **/
public class TwoSum1 {

    /**
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            maps.put( target - nums[i],i);
        }
        for(int k =0;k<nums.length;k++){
            if(maps.containsKey(nums[k])){ //查找到差值
                ret[1] = maps.get(nums[k]);
                ret[0] = k;
                if(ret[1]!=ret[0]){
                    return ret;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TwoSum1 solution = new TwoSum1();
        int[] array = new int[]{3,3};
        solution.twoSum(array,6);
    }
}
