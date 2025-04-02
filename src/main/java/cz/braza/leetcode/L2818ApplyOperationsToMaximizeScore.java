package cz.braza.leetcode;

import java.util.*;

/*
Daily 29.3.2025 - https://leetcode.com/problems/apply-operations-to-maximize-score/
2818. Apply Operations to Maximize Score
Hard
Topics
Companies
Hint
You are given an array nums of n positive integers and an integer k.

Initially, you start with a score of 1. You have to maximize your score by applying the following operation at most k times:

Choose any non-empty subarray nums[l, ..., r] that you haven't chosen previously.
Choose an element x of nums[l, ..., r] with the highest prime score. If multiple such elements exist, choose the one with the smallest index.
Multiply your score by x.
Here, nums[l, ..., r] denotes the subarray of nums starting at index l and ending at the index r, both ends being inclusive.

The prime score of an integer x is equal to the number of distinct prime factors of x. For example, the prime score of 300 is 3 since 300 = 2 * 2 * 3 * 5 * 5.

Return the maximum possible score after applying at most k operations.

Since the answer may be large, return it modulo 109 + 7.



Example 1:

Input: nums = [8,3,9,3,8], k = 2
Output: 81
Explanation: To get a score of 81, we can apply the following operations:
- Choose subarray nums[2, ..., 2]. nums[2] is the only element in this subarray. Hence, we multiply the score by nums[2]. The score becomes 1 * 9 = 9.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 1, but nums[2] has the smaller index. Hence, we multiply the score by nums[2]. The score becomes 9 * 9 = 81.
It can be proven that 81 is the highest score one can obtain.
Example 2:

Input: nums = [19,12,14,6,10,18], k = 3
Output: 4788
Explanation: To get a score of 4788, we can apply the following operations:
- Choose subarray nums[0, ..., 0]. nums[0] is the only element in this subarray. Hence, we multiply the score by nums[0]. The score becomes 1 * 19 = 19.
- Choose subarray nums[5, ..., 5]. nums[5] is the only element in this subarray. Hence, we multiply the score by nums[5]. The score becomes 19 * 18 = 342.
- Choose subarray nums[2, ..., 3]. Both nums[2] and nums[3] have a prime score of 2, but nums[2] has the smaller index. Hence, we multipy the score by nums[2]. The score becomes 342 * 14 = 4788.
It can be proven that 4788 is the highest score one can obtain.


Constraints:

1 <= nums.length == n <= 10^5
1 <= nums[i] <= 10^5
1 <= k <= min(n * (n + 1) / 2, 10^9)
 */
public class L2818ApplyOperationsToMaximizeScore {
    private static final int MOD = 1_000_000_007;
    /*
    Sieve of Eratosthenes to get the primes, then count factors for each number, then
    monotonic stack to find left-right limits for each number, then process numbers
    in descending order
    Runs 408ms, beats 43%
    // drop Collections.max() and use 316 only:
    Runs 283ms, beats 90.5%, 870 testcases
     */
    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] primeScores = new int[n];

        // Find the maximum element in nums to determine the range for prime generation
        //int maxElement = Collections.max(nums);

        // Get all prime numbers up to maxElement using the Sieve of Eratosthenes
        List<Integer> primes = getPrimes(316); // max value is 10^5, sqrt is 316.x, which is enough for us.

        // Calculate the prime score for each number in nums
        for (int index = 0; index < n; index++) {
            int num = nums.get(index);

            // Iterate over the generated primes to count unique prime factors
            for (int prime : primes) {
                if (prime * prime > num) break; // Stop early if prime^2 exceeds num
                if (num % prime != 0) continue; // Skip if the prime is not a factor

                primeScores[index]++; // Increment prime score for the factor
                while (num % prime == 0) num /= prime; // Remove all occurrences of this factor
            }

            // If num is still greater than 1, it is a prime number itself
            if (num > 1) primeScores[index]++;
        }

        // Initialize next and previous dominant index arrays
        int[] nextDominant = new int[n];
        int[] prevDominant = new int[n];
        Arrays.fill(nextDominant, n);
        Arrays.fill(prevDominant, -1);

        // Stack to store indices for a monotonic decreasing prime score
        Stack<Integer> decPrimeScore = new Stack<>();

        // Calculate the next and previous dominant indices for each number
        for (int index = 0; index < n; index++) {
            while ( !decPrimeScore.isEmpty() && primeScores[decPrimeScore.peek()] < primeScores[index])
                nextDominant[decPrimeScore.pop()] = index;

            if (!decPrimeScore.isEmpty()) prevDominant[index] = decPrimeScore.peek();

            decPrimeScore.push(index);
        }

        // Sort elements in decreasing order based on their values
        List<int[]> sortedArray = new ArrayList<>();
        for (int index = 0; index < n; index++)
            sortedArray.add(new int[] { nums.get(index), index });
        sortedArray.sort((a, b) -> Integer.compare(b[0], a[0])); // Sort in descending order

        long score = 1;
        int processingIndex = 0;

        // Process elements while there are operations left
        while (k > 0) {
            int[] element = sortedArray.get(processingIndex++);
            int num = element[0];
            int index = element[1];

            // Calculate the number of operations to apply on the current element
            long subArrays = (long) (nextDominant[index] - index) * (index - prevDominant[index]);
            long operations = k < subArrays ? k : subArrays;

            // Update the score by raising the element to the power of operations
            score = (score * power(num, operations)) % MOD;

            // Reduce the remaining operations count
            k -= operations;
        }

        return (int) score;
    }

    // Helper function to compute the power of a number modulo MOD
    private long power(long base, long exponent) {
        long res = 1;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent /= 2;
        }

        return res;
    }

    // Function to generate prime numbers up to a given limit using the Sieve of Eratosthenes
    private List<Integer> getPrimes(int limit) {
        boolean[] isComplex = new boolean[limit + 1];
        List<Integer> primes = new ArrayList<>();

        for (int number = 2; number <= limit; number++) {
            if (isComplex[number]) continue;

            primes.add(number);

            for (long multiple = (long) number * number; multiple <= limit; multiple += number)
                isComplex[(int) multiple] = true;
        }

        return primes;
    }
}
