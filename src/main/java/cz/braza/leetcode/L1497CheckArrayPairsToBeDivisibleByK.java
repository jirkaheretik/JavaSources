package cz.braza.leetcode;

/*
1497. Check If Array Pairs Are Divisible by k
Medium
Topics
Companies
Hint
Given an array of integers arr of even length n and an integer k.

We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.

Return true If you can find a way to do that or false otherwise.



Example 1:

Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
Example 2:

Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).
Example 3:

Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.


Constraints:

arr.length == n
1 <= n <= 105
n is even.
-109 <= arr[i] <= 109
1 <= k <= 105
 */
public class L1497CheckArrayPairsToBeDivisibleByK {
    /*
    Use the trick from L3184 = traverse the array and divide it to groups by
    modulo k, then [0] has to be even and [i]==[k-i] for any i in <1; k/2)
    Watch out for Java doing -1 % 3 == -1, not 2 :-(
    Runs 4ms, beats 99.7%
     */
    public boolean canArrange(int[] arr, int k) {
        int[] freqs = new int[k];
        for (int num: arr)
            freqs[((num % k) + k) % k]++;
        if (freqs[0] % 2 == 1) return false;
        for (int i = 1; i <= k / 2; i++)
            if (freqs[i] != freqs[k - i]) return false;
        return true;
    }
}