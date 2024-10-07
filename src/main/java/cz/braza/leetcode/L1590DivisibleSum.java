package cz.braza.leetcode;

/**
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 *
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 *
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 */
public class L1590DivisibleSum {
    public static int minSubarray(int[] nums, int p) {
        // count sum:
        long sum = 0;
        for (int i: nums)
            sum += i;
        if (sum % p == 0) return 0;
        int remainder = (int) (sum % p);
        for (int i: nums)
            if (i % p == remainder) return 1;
        int minlength = 2;
        while (minlength < nums.length) {
            long prefixSum = 0;
            int last = minlength;
            for (int i = 0; i < minlength; i++)
                prefixSum += nums[i];
            if (prefixSum % p == remainder) return minlength;
            while (last < nums.length) {
                prefixSum += nums[last] - nums[last - minlength];
                if (prefixSum % p == remainder) return minlength;
                last++;
            }
            minlength++; // try again with higher
        }
        return -1; // if we haven't found a solution, return -1 as impossible
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        System.out.println(minSubarray(nums, 6));
        int[] nums2 = {6, 3, 5, 2};
        System.out.println(minSubarray(nums2, 9));
        int[] nums3 = {1, 2, 3};
        System.out.println(minSubarray(nums3, 3));
    }
}
