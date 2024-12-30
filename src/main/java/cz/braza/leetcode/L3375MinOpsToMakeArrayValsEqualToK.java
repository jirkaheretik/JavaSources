package cz.braza.leetcode;

import java.util.Arrays;

/*
3375. Minimum Operations to Make Array Values Equal to K
Attempted
Easy
Topics
Companies
Hint
You are given an integer array nums and an integer k.

An integer h is called valid if all values in the array that are strictly greater than h are identical.

For example, if nums = [10, 8, 10, 8], a valid integer is h = 9 because all nums[i] > 9 are equal to 10, but 5 is not a valid integer.

You are allowed to perform the following operation on nums:

Select an integer h that is valid for the current values in nums.
For each index i where nums[i] > h, set nums[i] to h.
Return the minimum number of operations required to make every element in nums equal to k. If it is impossible to make all elements equal to k, return -1.



Example 1:

Input: nums = [5,2,5,4,5], k = 2

Output: 2

Explanation:

The operations can be performed in order using valid integers 4 and then 2.

Example 2:

Input: nums = [2,1,2], k = 2

Output: -1

Explanation:

It is impossible to make all the values equal to 2.

Example 3:

Input: nums = [9,7,5,3], k = 1

Output: 4

Explanation:

The operations can be performed using valid integers in the order 7, 5, 3, and 1.



Constraints:

1 <= nums.length <= 100
1 <= nums[i] <= 100
1 <= k <= 100
 */
public class L3375MinOpsToMakeArrayValsEqualToK {
    /*
    Total pain and dumb feeling. 4+ solutions before at least got it right, looks horrible.
    Basically we sort the array first, if min is lower than k, return -1 as we cannot fix the array.
    Then we start at the end and need to jump by different values.
    First run 179ms beating 5%, later I realized it was because of dbg print messages :-/
    Runs 3ms, beats 78.2%
     */
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums[0] < k) return -1;
        int currentIdx = nums.length - 1;
        int current = nums[currentIdx];
        while (currentIdx > 0 && nums[currentIdx - 1] == current)
            currentIdx--;
        int count = 0;
        while (currentIdx > 0 && current > k) {
            count++;
            currentIdx--;
            current = nums[currentIdx];
            while (currentIdx > 0 && nums[currentIdx - 1] == current)
                currentIdx--;
            current = nums[currentIdx];
            System.out.printf("%d. pick %d%n", count, current);
        }
        if (current > k) {
            count++;
            System.out.printf("%d. last pick of %d%n", count, k);
        }
        return count;
        //return current == k ? count : count + 1;
    }

    /*
    Much cleaner now, count skips (aka different elements), in the end update whether min == k or not.
    Runs 3ms, beats 78.2% again, no change.
     */
    public int minOperationsSimplified(int[] nums, int k) {
        Arrays.sort(nums);
        if (nums[0] < k) return -1;
        int currentIdx = nums.length - 1;
        // count different elements
        int count = 0;
        while (currentIdx > 0)
            if (nums[currentIdx] != nums[--currentIdx])
                count++;
        return nums[0] == k ? count : count + 1;
    }
}
