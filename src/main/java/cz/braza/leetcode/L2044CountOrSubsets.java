package cz.braza.leetcode;

/*

Code
Testcase
Test Result
Test Result
2044. Count Number of Maximum Bitwise-OR Subsets
Medium
Topics
Companies
Hint
Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.

The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).



Example 1:

Input: nums = [3,1]
Output: 2
Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
- [3]
- [3,1]
Example 2:

Input: nums = [2,2,2]
Output: 7
Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
Example 3:

Input: nums = [3,2,1,5]
Output: 6
Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
- [3,5]
- [3,1,5]
- [3,2,5]
- [3,2,1,5]
- [2,5]
- [2,1,5]


Constraints:

1 <= nums.length <= 16
1 <= nums[i] <= 105
 */
public class L2044CountOrSubsets {
    public static final int[] POWERS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};

    public int countMaxOrSubsets(int[] nums) {
        int maxCount = 0;
        int maxVal = 0;
        int variants = POWERS[nums.length];
        for (int variantIdx = 0; variantIdx < variants; variantIdx++) {
            int sumOr = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                if ((POWERS[idx] & variantIdx) > 0)
                    sumOr |= nums[idx];
            }
            if (sumOr > maxVal) {
                maxVal = sumOr;
                maxCount = 1;
            } else if (sumOr == maxVal)
                maxCount++;
        }
        return maxCount;

    }
}
