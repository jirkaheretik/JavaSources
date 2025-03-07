package cz.braza.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
Daily 6.2.2025

1726. Tuple with Same Product
Medium
Topics
Companies
Hint
Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.



Example 1:

Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
Example 2:

Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)


Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 104
All elements in nums are distinct.
 */
public class L1726TupleWithSameProduct {
    /*
    Map of products (a * b). If there are at least two, each combination is worth 8 in the result.
    Runs 173ms, beats 89.7%, 37 testcases
     */
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> products = new HashMap<>();
        // fill in map of product frequencies:
        for (int left = 0; left < nums.length - 1; left++)
            for (int right = left + 1; right < nums.length; right++)
                products.put(nums[left] * nums[right], products.getOrDefault(nums[left] * nums[right], 0) + 1);
        // compute result - if at least two different ways to get product, count 8 for each combination
        final int[] result = {0};
        products.forEach((k, v) -> result[0] += v > 1 ? (v * (v - 1) / 2) : 0);
        return 8 * result[0];
    }

    /*
    Different way (imperative programming) to compute the result from hash map
    Runs 170ms, beats 91.7%
     */
    public int tupleSameProductMap2(int[] nums) {
        Map<Integer, Integer> products = new HashMap<>();
        // fill in map of product frequencies:
        for (int left = 0; left < nums.length - 1; left++)
            for (int right = left + 1; right < nums.length; right++)
                products.put(nums[left] * nums[right], products.getOrDefault(nums[left] * nums[right], 0) + 1);
        // compute result - if at least two different ways to get product, count 8 for each combination
        int result = 0;
        for (int productFreq: products.values())
            if (productFreq > 1)
                result += (productFreq - 1) * productFreq / 2;
        return 8 * result;
    }
}
