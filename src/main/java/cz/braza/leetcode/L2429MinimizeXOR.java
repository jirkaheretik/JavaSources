package cz.braza.leetcode;

/*
Daily 15.1.2025
2429. Minimize XOR
Medium
Topics
Companies
Hint
Given two positive integers num1 and num2, find the positive integer x such that:

x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.

Return the integer x. The test cases are generated such that x is uniquely determined.

The number of set bits of an integer is the number of 1's in its binary representation.



Example 1:

Input: num1 = 3, num2 = 5
Output: 3
Explanation:
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
Example 2:

Input: num1 = 1, num2 = 12
Output: 3
Explanation:
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.


Constraints:

1 <= num1, num2 <= 10^9
 */
public class L2429MinimizeXOR {
    public static final int BITCOUNT = 30;
    /*
    From num2 we only need number of set bits, not even its value. Lets say its `bits`
    From num1 we need a value in binary (lets say array of boolean as set bits)
    Then we need to unset `bits` 1 bits from the left, if available. If not, we need to set
    new bits from the right (those that weren't touched before).
    Runs 1ms, beats 15.1%, 277 testcases (85% runs in 0ms)
     */
    public int minimizeXor(int num1, int num2) {
        // count set bits in num2
        int bits = 0;
        while (num2 > 0) {
            if (num2 % 2 == 1) bits++;
            num2 >>= 1;
        }
        // now get a binary representation of num1:
        boolean[] num1bits = new boolean[BITCOUNT];
        int idx = num1bits.length - 1;
        while (num1 > 0) {
            if (num1 % 2 == 1) num1bits[idx] = true;
            num1 >>= 1;
            idx--;
        }
        // now unset the bits from the left:
        boolean[] workBits = new boolean[BITCOUNT];
        idx = 0;
        while (bits > 0 && idx < num1bits.length) {
            if (num1bits[idx]) { // "unset" => this bit will stay, mark as worked on
                bits--;
                workBits[idx] = true;
            }
            idx++;
        }
        // any bits left? Then we need to SET lowest bits first:
        idx = num1bits.length - 1;
        while (bits > 0) {
            if (!workBits[idx]) { // not touched, we can set it:
                workBits[idx] = true;
                bits--;
            }
            idx--;
        }
        // now we have result bits set in workBits, convert back to base10:
        int result = 0;
        for (boolean val: workBits) {
            result <<= 1;
            if (val) result++;
        }
        return result;
    }

    /*
    Try to compute result as we go...
    Now runs 0ms, beats 100%, 277 testcases
     */
    public int minimizeXorV2(int num1, int num2) {
        int result = 0;
        // count set bits in num2
        int bits = 0;
        while (num2 > 0) {
            if (num2 % 2 == 1) bits++;
            num2 >>= 1;
        }
        // now get a binary representation of num1:
        boolean[] num1bits = new boolean[BITCOUNT];
        int idx = BITCOUNT - 1;
        while (num1 > 0) {
            if (num1 % 2 == 1) num1bits[idx] = true;
            num1 >>= 1;
            idx--;
        }
        // now unset the bits from the left:
        boolean[] workBits = new boolean[BITCOUNT];
        idx = 0;
        while (bits > 0 && idx < BITCOUNT) {
            if (num1bits[idx]) { // "unset" => this bit will stay, mark as worked on
                bits--;
                workBits[idx] = true;
                result += 1 << (BITCOUNT - idx - 1);
            }
            idx++;
        }
        // any bits left? Then we need to SET lowest bits first:
        idx = BITCOUNT - 1;
        while (bits > 0) {
            if (!workBits[idx]) { // not touched, we can set it:
                result += 1 << (BITCOUNT - idx - 1);
                bits--;
            }
            idx--;
        }
        // return result
        return result;
    }

}
