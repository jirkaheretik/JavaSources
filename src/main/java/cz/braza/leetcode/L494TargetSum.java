package cz.braza.leetcode;

/*
494. Target Sum
Medium
Topics
Companies
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.



Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1


Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */
public class L494TargetSum {
    /*
    Easy recursive solution, just try + and - in each place
    Runs 353ms, beats 35.4%, 140 testcases
    # easier to get countWays2 runs slower, possibly due to one more recursion level
    Runs 564ms, beats 32.7%
     */
    public int findTargetSumWays(int[] nums, int target) {
        return countWays(nums, 0, 0, target);
    }

    public static int countWays(final int[] nums, final int current, final int index, final int target) {
        // if we are at the end, return count
        if (index == nums.length - 1)
            return (nums[index] == 0 && current == target) ? 2 : (current + nums[index] == target || current - nums[index] == target) ? 1 : 0;
        return countWays(nums, current + nums[index], index + 1, target) + countWays(nums, current - nums[index], index + 1, target);
    }

    public static int countWays2(final int[] nums, final int current, final int index, final int target) {
        // if we are at the end, return count
        if (index == nums.length) return current == target ? 1 : 0;
        return countWays(nums, current + nums[index], index + 1, target) + countWays(nums, current - nums[index], index + 1, target);
    }
}
