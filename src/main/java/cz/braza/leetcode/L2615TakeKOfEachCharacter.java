package cz.braza.leetcode;

import java.util.Arrays;

/*
2516. Take K of Each Character From Left and Right
Medium
Topics
Companies
Hint
You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.



Example 1:

Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation:
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
Example 2:

Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.


Constraints:

1 <= s.length <= 105
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length
 */
public class L2615TakeKOfEachCharacter {

    /* Bullshit, this correctly works for k==2 only, which also can be a good exercise.
    Test case 95/142 fails, needing a completely different approach.
     */
    public static int takeCharactersIfOnlyTwo(String s, int k) {
        if (k == 0) return 0;
        char[] LOOKFOR = {'a', 'b', 'c'};
        int[][] POSITIONS = new int[LOOKFOR.length][4]; // in each row, position of first and second from the left and first and secod from the right
        // fill in positions first:
        for (int idx = 0; idx < LOOKFOR.length; idx++) {
            String look = "" + LOOKFOR[idx];
            int left = s.indexOf(look);
            int right = s.lastIndexOf(look);
            if (left == right) return -1; // either there is no such character or just one
            POSITIONS[idx][0] = left;
            POSITIONS[idx][3] = s.length() - 1 - right;
            POSITIONS[idx][1] = s.indexOf(look, left + 1);
            POSITIONS[idx][2] = s.length() - 1 - s.lastIndexOf(look, right - 1);
        }
        System.out.println("DBG Positions:");
        for (int[] row: POSITIONS)
            System.out.println(Arrays.toString(row));
        System.out.println("DBG End.");
        /* for each letter, we can use three variants:
        1. - take both two letters from the left (using [1])
        2. - take both two letter from the right (using [2])
        3. - take one from the left and one from the right (using [0] and [3])
        So there are N^3 combinations, where N is number of chars we are looking for (3)
        Evaluate each combination and find max(left1, left2, left3...) + max(right1, right2, right3...) and find minimum of those
         */
        int totalMin = Integer.MAX_VALUE;
        int variants = (int)Math.pow(LOOKFOR.length, 3);
        for (int variant = 0; variant < variants; variant++) {
            int leftMin = 0;
            int rightMin = 0;
            int variantToProcess = variant; // we will change variantToProcess during the cycle...
            for (int idx = 0; idx < LOOKFOR.length; idx++) {
                int ourVariant = variantToProcess % 3;
                // next:
                variantToProcess /= 3;
                // use the variant:
                switch (ourVariant) {
                    case 0: if (leftMin < POSITIONS[idx][1] + 1) leftMin = POSITIONS[idx][1] + 1; break;
                    case 1: if (rightMin < POSITIONS[idx][2] + 1) rightMin = POSITIONS[idx][2] + 1; break;
                    case 2:
                        if (leftMin < POSITIONS[idx][0] + 1) leftMin = POSITIONS[idx][0] + 1;
                        if (rightMin < POSITIONS[idx][3] + 1) rightMin = POSITIONS[idx][3] + 1;
                }
            }
            System.out.printf("Variant %d, min left %d, min right %d%n", variant, leftMin, rightMin);
            if (totalMin > leftMin + rightMin)
                totalMin = leftMin + rightMin;
        }
        return totalMin;
    }

    /*
    Funnily easy implementation: go through the characters from the left
    and count frequencies. Once we have at least k of every char, we stop,
    no need to go further. We note current number as a boundary for total
    minimum. Then we try to remove a character back to the left, one by one,
    and whenever any of the frequencies drops below the threshold, we need
    to find that char from the right side - so we are adding from the right
    until all chars have at least k occurrences. Once we are back at the
    beginning, we must have found optimal solution somewhere.
    Runs 10ms, beats 92.7%
     */
    public static int takeCharacters(String s, int k) {
        if (k == 0) return 0;
        int aFreq = 0, bFreq = 0, cFreq = 0;
        char[] chars = s.toCharArray();
        for (char c: chars) {
            if (c == 'a') aFreq++;
            else if (c == 'b') bFreq++;
            else if (c == 'c') cFreq++;
            if (aFreq >= k && bFreq >= k && cFreq >= k) break; // we are done here, do not go any further
        }
        if (aFreq <k || bFreq < k || cFreq < k) return -1; // not enough characters
        int minLength = aFreq + bFreq + cFreq;
        int rightIndex = 0; // how many we took from the right
        int leftIndex = minLength - 1; // current pos of last char from the left
        // now go back, removing frequencies, and if anything drops below a treshold, add characters from the right
        while (leftIndex >= 0) {
            // remove a character and update left index:
            char c = chars[leftIndex--];
            if (c == 'a') aFreq--;
            else if (c == 'b') bFreq--;
            else if (c == 'c') cFreq--;
            // did we drop a "needed" character? then find an update from the right:
            while (aFreq < k || bFreq < k || cFreq < k) {
                char r = chars[chars.length - ++rightIndex];
                if (r == 'a') aFreq++;
                else if (r == 'b') bFreq++;
                else if (r == 'c') cFreq++;
            }
            if (leftIndex + 1 + rightIndex < minLength)
                minLength = leftIndex + 1 + rightIndex;
        }
        return minLength;
    }

    public static void main(String[] args) {
        System.out.println(takeCharacters("aabaaaacaabc", 2));
    }
}
