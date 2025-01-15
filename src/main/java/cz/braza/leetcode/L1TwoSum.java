package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        for (int first = 0; first < nums.length; first++)
            for (int second = first + 1; second < nums.length; second++)
                if (nums[first] + nums[second] == target)
                    return new int[]{first, second};
        return null; // should not get there if any valid answer exists
    }

    /*
    Use hashtable to store value-index pairs, then process array once again
    Runs 4ms, beats 60.7%, 63 testcases
     */
    public static int[] twoSumHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx++)
            map.put(nums[idx], idx);
        for (int idx = 0; idx < nums.length; idx++) {
            int lookForVal = target - nums[idx];
            if (map.containsKey(lookForVal) && idx != map.get(lookForVal))
                return new int[]{idx, map.get(lookForVal)};
        }
        return null; // should not get there if any valid answer exists
    }

}
