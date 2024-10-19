package cz.braza.leetcode;

/*
2605. Form Smallest Number From Two Digit Arrays
Easy
Topics
Companies
Hint
Given two arrays of unique digits nums1 and nums2, return the smallest number that contains at least one digit from each array.


Example 1:

Input: nums1 = [4,1,3], nums2 = [5,7]
Output: 15
Explanation: The number 15 contains the digit 1 from nums1 and the digit 5 from nums2. It can be proven that 15 is the smallest number we can have.
Example 2:

Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
Output: 3
Explanation: The number 3 contains the digit 3 which exists in both arrays.


Constraints:

1 <= nums1.length, nums2.length <= 9
1 <= nums1[i], nums2[i] <= 9
All digits in each array are unique.
 */
public class L2605SmallestNumber {
    public int minNumber(int[] nums1, int[] nums2) {
        // map to boolean for easier check (true means such a digit is in the original array
        boolean[] digits1 = new boolean[10];
        boolean[] digits2 = new boolean[10];
        for (int num: nums1)
            digits1[num] = true;
        for (int num: nums2)
            digits2[num] = true;
        int minDigit1 = 10;
        int minDigit2 = 10;
        // finding minimum, as well as common digit (if any):
        for (int index = 1; index < 10; index++)
            if (digits1[index] && digits2[index])
                return index; // must be the smallest
            else {
                if (digits1[index] && minDigit1 > index)
                    minDigit1 = index;
                if (digits2[index] && minDigit2 > index)
                    minDigit2 = index;
            }
        return minDigit1 > minDigit2 ? 10 * minDigit2 + minDigit1 : 10 * minDigit1 + minDigit2;
    }
}
