package cz.braza.leetcode;

/*
1863. Sum of All Subset XOR Totals
Easy
Topics
Companies
Hint
The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums.

Note: Subsets with the same elements should be counted multiple times.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.



Example 1:

Input: nums = [1,3]
Output: 6
Explanation: The 4 subsets of [1,3] are:
- The empty subset has an XOR total of 0.
- [1] has an XOR total of 1.
- [3] has an XOR total of 3.
- [1,3] has an XOR total of 1 XOR 3 = 2.
0 + 1 + 3 + 2 = 6
Example 2:

Input: nums = [5,1,6]
Output: 28
Explanation: The 8 subsets of [5,1,6] are:
- The empty subset has an XOR total of 0.
- [5] has an XOR total of 5.
- [1] has an XOR total of 1.
- [6] has an XOR total of 6.
- [5,1] has an XOR total of 5 XOR 1 = 4.
- [5,6] has an XOR total of 5 XOR 6 = 3.
- [1,6] has an XOR total of 1 XOR 6 = 7.
- [5,1,6] has an XOR total of 5 XOR 1 XOR 6 = 2.
0 + 5 + 1 + 6 + 4 + 3 + 7 + 2 = 28
Example 3:

Input: nums = [3,4,5,6,7,8]
Output: 480
Explanation: The sum of all XOR totals for every subset is 480.


Constraints:

1 <= nums.length <= 12
1 <= nums[i] <= 20
 */
/*
https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/
Plán:
Pro N prvků potřebuju 2^N variant, každý prvek tam může a nemusí být.
Mohu udělat cyklus do 2^N, přes bitové and (index prvku, index cyklu) poznám,
jestli ho zařadit do aktuální množiny a xorovat.
 */
public class L1863SubsetXorTotal {
    // Určuje jednak kolik variant budu potřebovat/generovat, jednak také "bit" pro každou variantu
    public static final int[] POWERS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
    public static int subsetXORSum(int[] nums) {
        int totalSum = 0;
        int variants = POWERS[nums.length];
        for (int variantIdx = 0; variantIdx < variants; variantIdx++) {
            int variantXor = 0;
            for (int idx = 0; idx < nums.length; idx++) {
                if ((POWERS[idx] & variantIdx) > 0)
                    variantXor ^= nums[idx];
            }
            totalSum += variantXor;
        }
        return totalSum;
    }

    /*
    Daily 5.4., taken from editorial.
    Each bit that is set in any of the numbers will be in the result 2^(n-1) times (half the cases). If that bit is nowhere set, it wont be there at all.
    That means we gather all the set bits (by ORing all the numbers) and then shift it to the left N-1 times. All done in O(n).
    Runs 0ms, beats 100%, 48 testcases.
    */
    public static int subsetXORSumEditorial(int[] nums) {
        int result = 0;
        for (int num: nums) result |= num;
        return result << (nums.length - 1);
    }

    public static void main(String[] args) {
        int[] toTest = {5, 1, 6};
        System.out.println(subsetXORSum(toTest));
    }

}
