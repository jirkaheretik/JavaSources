package cz.braza.leetcode;

/*
485. Max Consecutive Ones
Easy
Topics
Companies
Hint
Given a binary array nums, return the maximum number of consecutive 1's in the array.



Example 1:

Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 2


Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class L485MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentMax = 0;
        for (int val: nums)
            if (val == 1) {
                currentMax++;
                if (currentMax > maxCount)
                    maxCount = currentMax;
            } else
                currentMax = 0;
        return maxCount;
    }

}
