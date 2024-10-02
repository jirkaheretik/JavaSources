package cz.braza.leetcode;

import java.util.Arrays;

public class L1TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        for (int first = 0; first < nums.length; first++) {
            for (int second = 0; second < nums.length; second++) {
                if (first == second) continue;
                if (nums[first] + nums[second] == target)
                    return new int[]{first, second};
            }
        }
        return null; // should not get there if any valid answer exists
    }
}
