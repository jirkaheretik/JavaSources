package cz.braza.leetcode;

/*
55. Jump Game
Medium
Topics
Companies
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 */
public class L55JumpGame {
    /*
    Straightforward 1st try, linear
    after fixing bugs, 2ms, beats 80 %
     */
    public static boolean canJump(int[] nums) {
        int canReach = 0;
        int toReach = nums.length - 1;
        for (int index = 0; index < toReach; index++) {
            if (index > canReach) return false;
            int current = index + nums[index];
            if (current > canReach) canReach = current;
            if (canReach >= toReach) return true;
        }
        return canReach >= toReach;
    }

    /*
    Next proposal, using Math.max instead of conditional assignment
    Same running time 2ms, same beat 80 %, significant drop in memory beats % (15 % instead of 91.5 %)
     */
    public boolean canJumpMax(int[] nums) {
        int canReach = 0;
        int toReach = nums.length - 1;
        for (int index = 0; index < toReach; index++) {
            if (index > canReach) return false;
            canReach = Math.max(canReach, index + nums[index]);
            if (canReach >= toReach) return true;
        }
        return canReach >= toReach;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }

}
