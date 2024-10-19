package cz.braza.leetcode;

import java.util.Arrays;

/*
1545. Find Kth Bit in Nth Binary String
Medium
Topics
Companies
Hint
Given two positive integers n and k, the binary string Sn is formed as follows:

S1 = "0"
Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).

For example, the first four strings in the above sequence are:

S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
Return the kth bit in Sn. It is guaranteed that k is valid for the given n.



Example 1:

Input: n = 3, k = 1
Output: "0"
Explanation: S3 is "0111001".
The 1st bit is "0".
Example 2:

Input: n = 4, k = 11
Output: "1"
Explanation: S4 is "011100110110001".
The 11th bit is "1".


Constraints:

1 <= n <= 20
1 <= k <= 2n - 1
 */
public class L1545KthBitInBinaryString {
    public static final boolean[] S1 = {false}; //EVIL :-(
    public static final int[] POWERS = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576};

    // first working version, together with getSn, reverse, invert
    // 25ms, beats 53 %
    public static char findKthBit(int n, int k) {
        return getSn(n)[k - 1] ? '1' : '0';
    }

    public static boolean[] getSn(int n) {
        /*
        FAILED (changing values):
        if (n == 1) return S1;
         */
        if (n == 1) {
            boolean[] result = new boolean[1];
            result[0] = false;
            return result;
        }
        boolean[] snm1 = getSn(n - 1);
        boolean[] result = new boolean[2 * snm1.length + 1];
        System.arraycopy(snm1, 0, result, 0, snm1.length);
        result[snm1.length] = true;
        System.arraycopy(reverse(invert(snm1)), 0, result, snm1.length + 1, snm1.length);
        return result;
    }

    public static boolean[] reverse(boolean[] arr) {
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            boolean dummy = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = dummy;
        }
        return arr;
    }

    public static boolean[] invert(boolean[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = !arr[i];
        return arr;
    }

    // next version to speed up by not manipulating whole arrays
    // nice, 15 ms, 53.1%
    public static char findKthBitInPlace(int n, int k) {
        boolean[] result = new boolean[POWERS[n] - 1];
        result[0] = false;
        int endIndex = 0;
        for (int step = 1; step < n; step++) {
            // add 1 at the end:
            result[++endIndex] = true;
            // copy/revert the first part
            // indices 0-endIndex(excluded) invert to indices endIndex+1 onwards, update endIndex
            for (int i = endIndex - 1; i >= 0; i--)
                result[++endIndex] = !result[i];
        }
        // System.out.println(Arrays.toString(result));
        return result[k - 1] ? '1' : '0';
    }

    /*
    Next plan (version 3)
    This time try to find out the bit value without the need of the array at all,
    halving the interval.
    Make this recursive with a flip
    Result: 0ms, beats 100%
     */
    public static char findKthBitNoArray(int n, int k) {
        return findKthBitNoArray(n, k - 1, false) ? '1' : '0';
    }
    public static boolean findKthBitNoArray(int n, int k, boolean flip) {
        if (k == 0) return flip;
        int half = POWERS[n - 1] - 1;
        if (k == half) return !flip; // 1 in the middle
        if (k < half) return findKthBitNoArray(n - 1, k, flip);
        return findKthBitNoArray(n - 1, 2 * half - k, !flip);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getSn(4)));
//        System.out.println(findKthBit(4, 11));
//        System.out.println(findKthBitInPlace(4, 11));
        char[] res = new char[15];
        for (int k = 0; k < 15; k++)
            res[k] = findKthBitNoArray(4, k + 1);
        System.out.println(Arrays.toString(res));
        System.out.println(Arrays.toString(getSn(4)));
    }

}
