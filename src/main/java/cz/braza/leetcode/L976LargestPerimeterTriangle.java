package cz.braza.leetcode;

import java.util.Arrays;

/*
976. Largest Perimeter Triangle
Solved
Easy
Topics
Companies
Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.



Example 1:

Input: nums = [2,1,2]
Output: 5
Explanation: You can form a triangle with three side lengths: 1, 2, and 2.
Example 2:

Input: nums = [1,2,1,10]
Output: 0
Explanation:
You cannot use the side lengths 1, 1, and 2 to form a triangle.
You cannot use the side lengths 1, 1, and 10 to form a triangle.
You cannot use the side lengths 1, 2, and 10 to form a triangle.
As we cannot use any three side lengths to form a triangle of non-zero area, we return 0.


Constraints:

3 <= nums.length <= 10^4
1 <= nums[i] <= 10^6
 */
public class L976LargestPerimeterTriangle {
    /*
    First attempt, while driving a car:
    sort array, then go from the highest value, and we are looking for next 2 values to be greater, otherwise it is not a triangle
    Stop when any length is zero, then no triangle can be formed.
    Runs 8ms, beats 66.3%
     */
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int idx = nums.length - 1; idx > 1; idx--)
            if (nums[idx - 2] == 0) break;
            else
            if (nums[idx] < nums[idx - 1] + nums[idx - 2])
                return nums[idx] + nums[idx - 1] + nums[idx - 2];
        return 0;
    }

    /*
    Update:
    Drop the "zero" test, as such values cannot be. No change,
    runs 8ms, beats 66.3%
     */
    public int largestPerimeterFindZero(int[] nums) {
        Arrays.sort(nums);
        for (int idx = nums.length - 1; idx > 1; idx--)
            if (nums[idx] < nums[idx - 1] + nums[idx - 2])
                return nums[idx] + nums[idx - 1] + nums[idx - 2];
        return 0;
    }

}
