package cz.braza.leetcode;

/**
 * A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
 *
 * Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [6,0,8,2,1,5]
 * Output: 4
 * Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
 * Example 2:
 *
 * Input: nums = [9,8,1,0,1,9,4,0,4,1]
 * Output: 7
 * Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5 * 104
 */
public class L952MaxWidthRamp {
    public static int maxWidthRamp(int[] nums) {
        // sanity check first (guard against TLE):
        int bumps = 0;
        for (int i = 1; i < nums.length; i++)
            if (nums[i] >= nums[i - 1]) {
                bumps++;
            }
        if (bumps == 0) return 0;
        // here we go with actual algorithm:
        int maxWidth = 0;
        int minValue = Integer.MAX_VALUE;
        for (int start = 0; start < nums.length - maxWidth; start++) {
            if (nums[start] >= minValue) continue; // this will not help us
            for (int end = nums.length - 1; end > start; end--)
                if (nums[end] >= nums[start]) {
                    if (end - start > maxWidth) {
                        maxWidth = end - start;
                        minValue = nums[start];
                    }
                }
        }
        return maxWidth;
    }

    public static int maxWidthRampWithStack(int[] nums) {
        /**
         * Desc: traverse left-right once, build stack of indices of numbers in decreasing order
         * Then go from right, and pop stack until there is a less-than-or-equals value, compute j - i and update max width
         */
        return 0;
    }

    public static void main(String[] args) {
        int[] test = {6,0,8,2,1,5};
        System.out.println(maxWidthRamp(test));
        int[] test2 = {9,8,1,0,1,9,4,0,4,1};
        System.out.println(maxWidthRamp(test2));
    }
}
