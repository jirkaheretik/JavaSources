package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/*
https://leetcode.com/problems/number-of-arithmetic-triplets/description/
2367. Number of Arithmetic Triplets
Solved
Easy
Topics
Companies
Hint
You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff. A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:

i < j < k,
nums[j] - nums[i] == diff, and
nums[k] - nums[j] == diff.
Return the number of unique arithmetic triplets.



Example 1:

Input: nums = [0,1,4,6,7,10], diff = 3
Output: 2
Explanation:
(1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
(2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
Example 2:

Input: nums = [4,5,6,7,8,9], diff = 2
Output: 2
Explanation:
(0, 2, 4) is an arithmetic triplet because both 8 - 6 == 2 and 6 - 4 == 2.
(1, 3, 5) is an arithmetic triplet because both 9 - 7 == 2 and 7 - 5 == 2.


Constraints:

3 <= nums.length <= 200
0 <= nums[i] <= 200
1 <= diff <= 50
nums is strictly increasing.
 */
public class L2367CountArithmeticTriplets {
    // v1, complete brute force
    // runs 2ms, beats 78.8%
    public int arithmeticTripletsV1(int[] nums, int diff) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (nums[j] - nums[i] == diff)
                    for (int k = j + 1; k < n; k++)
                        if (nums[k] - nums[j] == diff) count++;
        return count;
    }

    // v2 - almost linear (O(n)), but using sets (dynamic collection)
    // first fill up collection, then check if two other numbers are there as well
    // runs 2ms, beats 78.8%
    public int arithmeticTripletsV2(int[] nums, int diff) {
        int count = 0;
        HashSet<Integer> numset = new HashSet<>();
        for (int num: nums) numset.add(num);
        for (int num: nums)
            if (numset.contains(num + diff) && numset.contains(num + 2 * diff)) count++;
        return count;
    }

    // use just the array, strictly increasing, and binarySearch
    // Runs 1ms, beats 96.2%
    public int arithmeticTriplets(int[] nums, int diff) {
        int count = 0;
        for (int num: nums)
            if (Arrays.binarySearch(nums, num + diff) > 0 && Arrays.binarySearch(nums, num + 2 * diff) > 0) count++;
        return count;
    }
}
