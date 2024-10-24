package cz.braza.leetcode;

import java.util.Arrays;

/*
45. Jump Game II
Medium
Topics
Companies
You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].



Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 */
public class L45JumpGame2 {
    /*
    First try, make another array with how fast can i jump there
    42 ms, beats 15.4 %, most (72 %) does it in 0ms time :-(
     */
    public static int jump(int[] nums) {
        int toReach = nums.length - 1;
        int[] jumps = new int[toReach + 1];
        for (int index = 0; index < toReach; index++) {
            for (int jump = 1; jump <= nums[index] && index + jump <= toReach; jump++)
                if (jumps[index + jump] == 0 || jumps[index + jump] > jumps[index] + 1)
                    jumps[index + jump] = jumps[index] + 1;
        }
        return jumps[toReach];
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
