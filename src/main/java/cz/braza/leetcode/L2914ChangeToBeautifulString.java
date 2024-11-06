package cz.braza.leetcode;

/*
2914. Minimum Number of Changes to Make Binary String Beautiful
Medium
Topics
Companies
Hint
You are given a 0-indexed binary string s having an even length.

A string is beautiful if it's possible to partition it into one or more substrings such that:

Each substring has an even length.
Each substring contains only 1's or only 0's.
You can change any character in s to 0 or 1.

Return the minimum number of changes required to make the string s beautiful.



Example 1:

Input: s = "1001"
Output: 2
Explanation: We change s[1] to 1 and s[3] to 0 to get string "1100".
It can be seen that the string "1100" is beautiful because we can partition it into "11|00".
It can be proven that 2 is the minimum number of changes needed to make the string beautiful.
Example 2:

Input: s = "10"
Output: 1
Explanation: We change s[1] to 1 to get string "11".
It can be seen that the string "11" is beautiful because we can partition it into "11".
It can be proven that 1 is the minimum number of changes needed to make the string beautiful.
Example 3:

Input: s = "0000"
Output: 0
Explanation: We don't need to make any changes as the string "0000" is beautiful already.


Constraints:

2 <= s.length <= 105
s has an even length.

 */
public class L2914ChangeToBeautifulString {
    /*
    First idea: go through the list of chars, count frequencies, whenever
    we find an ODD number, we need to swap the next, then continue counting.
    Runs 4ms, beats 40%, majority does 3ms
     */
    public int minChanges(String s) {
        int numChanges = 0;
        int lastFreq = 0;
        char lastChar = 'X'; // something else than 0 and 1
        for (char c: s.toCharArray()) {
            if (c == lastChar)
                lastFreq++;
            else {
                if (lastFreq % 2 == 1) {
                    numChanges++;
                    lastFreq = 0;
                } else
                    lastFreq = 1;
                lastChar = c;
            }
        }
        // sanity check
        if (lastFreq % 2 == 1)
            System.out.println("Sum-tin Wong at the end...");
        return numChanges;
    }

    /* Optimized solition, do not count each frequencies, just even/odd
    If the char changes, if odd, we need to +1 needed number of changes.
    And with every char even/odd changes, and that is all we need to know.
    Runs: same 4ms/40%
     */
    public static int minChangesOptim(String s) {
        int numChanges = 0;
        boolean isEven = true;
        boolean isZero = true; // whatever here, actually
        for (char c: s.toCharArray()) {
            if (c == '0' && !isZero || c == '1' && isZero) {
                isZero = !isZero;
                if (!isEven)
                    numChanges++;
            }
            isEven = !isEven;
        }
        return numChanges;
    }

    /*
    Replace longer condition with xor
    Runs 4ms again :-(
     */
    public static int minChangesXor(String s) {
        int numChanges = 0;
        boolean isEven = true;
        boolean isZero = true; // whatever here, actually
        for (char c: s.toCharArray()) {
            if ((c == '0') ^ isZero) {
            //if (c == '0' && !isZero || c == '1' && isZero) {
                isZero = !isZero;
                if (!isEven)
                    numChanges++;
            }
            isEven = !isEven;
        }
        return numChanges;
    }

    /*
    Just for fun obfuscated a bit the last solution
     */
    public static int minChangesXorObfuscated(String s) {
        int count = 0;
        boolean flip1 = true;
        boolean flip2 = true; // whatever here, actually
        for (char c: s.toCharArray()) {
            if ((c == '0') ^ flip2) {
                flip2 ^= true;
                if (!flip1)
                    count++;
            }
            flip1 ^= true;
        }
        return count;
    }

    /*
    Observation: just compare two chars at a time, if they are different, flip is needed
    Runs 3ms, beats 97.8%
     */
    public static int minChangesStringPairs(String s) {
        int count = 0;
        for (int index = 0; index < s.length(); index += 2)
            if (s.charAt(index) != s.charAt(index + 1))
                count++;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(minChangesStringPairs("1001"));
        System.out.println(minChangesStringPairs("111111"));
        System.out.println(minChangesStringPairs("10011111100011101000"));

    }
}
