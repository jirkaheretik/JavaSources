package cz.braza.leetcode;

public class L167TwoSumSorted {
    public static int[] twoSum(int[] nums, int target) {
        for (int first = 0; first < nums.length; first++) {
            for (int second = first + 1; second < nums.length; second++) {
                if (nums[first] + nums[second] == target)
                    return new int[]{first + 1, second + 1};
            }
        }
        return null; // should not get there if any valid answer exists
    }
}
