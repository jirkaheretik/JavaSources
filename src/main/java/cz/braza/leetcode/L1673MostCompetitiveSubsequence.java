package cz.braza.leetcode;

import java.util.Arrays;
import java.util.Stack;

/*
1673. Find the Most Competitive Subsequence
Medium
Topics
Companies
Hint
Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.

An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.

We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.



Example 1:

Input: nums = [3,5,2,6], k = 2
Output: [2,6]
Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
Example 2:

Input: nums = [2,4,3,3,5,4,9,6], k = 4
Output: [2,3,3,4]


Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 109
1 <= k <= nums.length

HINT: In lexicographical order, the elements to the left have higher priority than those that come after. Can you think of a strategy that incrementally builds the answer from left to right?
 */
/// TODO:
/// Iterativní řešení. 1. verze TLE, pak jsem okoukal Stack a dělal to za 70ms, pak jsem to
/// sám přepsal na pole a zrychlil tím na 7ms při zachování Complexity.
public class L1673MostCompetitiveSubsequence {
    // Quite easy with a simple trick with indices, but leads to TLE for 10^5 items and k = 5*10^4
    public int[] mostCompetitive(int[] nums, int k) {
        int[] result = new int[k];
        int index = 0;
        for (int position = 0; position < k; position++) {
            int lowestIndex = index;
            for (int i = lowestIndex + 1; i <= nums.length - k + position; i++)
                if (nums[i] < nums[lowestIndex])
                    lowestIndex = i;
            result[position] = nums[lowestIndex];
            index = lowestIndex + 1;
        }
        return result;
    }

    public int[] mostCompetitiveWithStack(int[] nums, int k) {
        int[] res = new int[k];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < stack.peek() && nums.length - i > k - stack.size())
                stack.pop();
            if (stack.size() < k)
                stack.push(nums[i]);
        }
        for (int i = k-1; i>=0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public int[] mostCompetitiveWithArrayStack(int[] nums, int k) {
        int[] result = new int[k];
        int stack = 0;
        for (int idx = 0; idx < nums.length; idx++) {
            while (stack > 0 && nums[idx] < result[stack - 1] && nums.length - idx > k - stack) {
                stack--;
            }
            if (stack < k)
                result[stack++] = nums[idx];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {71,18,52,29,55,73,24,42,66,8,80,2};
        int k1 = 3;
        int[] nums2 = {2,4,3,3,5,4,9,6};
        int k2 = 4;
        L1673MostCompetitiveSubsequence sol = new L1673MostCompetitiveSubsequence();
        System.out.println(Arrays.toString(sol.mostCompetitiveWithArrayStack(nums1, k1)));
        //System.out.println(Arrays.toString(sol.mostCompetitive(nums2, k2)));
    }
}
