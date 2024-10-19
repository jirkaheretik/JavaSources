package cz.braza.leetcode;

/*
670. Maximum Swap
Medium
Topics
Companies
You are given an integer num. You can swap two digits at most once to get the maximum valued number.

Return the maximum valued number you can get.



Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.


Constraints:

0 <= num <= 108
 */
public class L670MaximumSwap {
    public int maximumSwap(int num) {
        if (num == 0) return 0;
        // split into digits into array:
        int pozic = (int)Math.log10(num) + 1;
        int[] cifry = new int[pozic];
        int pozice = cifry.length - 1;
        while (num > 0) {
            cifry[pozice--] = num % 10;
            num /= 10;
        }
        // try to find a position to swap:
        for (int i = 0; i < cifry.length; i++) {
            int indexOfMaxValue = 0;
            for (int j = cifry.length - 1; j > i; j--)
                if (cifry[j] > cifry[i] && (indexOfMaxValue == 0 || cifry[indexOfMaxValue] < cifry[j]))
                    indexOfMaxValue = j;
            // now the actual swap:
            if (indexOfMaxValue > 0) {
                int dummy = cifry[indexOfMaxValue];
                cifry[indexOfMaxValue] = cifry[i];
                cifry[i] = dummy;
                break;
            }
        }
        // return the resulting number:
        int result = 0;
        for (int cifra : cifry) {
            result *= 10;
            result += cifra;
        }
        return result;
    }
}
