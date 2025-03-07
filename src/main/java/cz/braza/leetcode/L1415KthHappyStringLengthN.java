package cz.braza.leetcode;

/*
1415. The k-th Lexicographical String of All Happy Strings of Length n
Medium
Topics
Companies
Hint
A happy string is a string that:

consists only of letters of the set ['a', 'b', 'c'].
s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.

Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.

Return the kth string of this list or return an empty string if there are less than k happy strings of length n.



Example 1:

Input: n = 1, k = 3
Output: "c"
Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
Example 2:

Input: n = 1, k = 4
Output: ""
Explanation: There are only 3 happy strings of length 1.
Example 3:

Input: n = 3, k = 9
Output: "cab"
Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"


Constraints:

1 <= n <= 10
1 <= k <= 100
 */
public class L1415KthHappyStringLengthN {
    /*
    Happy strings:
    3 for length 1
    Cn for length n, where Cn = 2 * C(n-1)
    That is, there are always two variants of length n+1 for any happy string with length n

    Former (and non working) ideas:
    - base3 value, replace 012 with abc (does not take into account "happiness" of a string) = no repeating chars
     */
    public static final int[] LENGTHS = {1, 3, 6, 12, 24, 48, 96, 192, 384, 768, 1538};
    public static final int[] TWOTON = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
    public static final char[] LETTERS = {'a', 'b', 'c'};
    // for i in range(1, 11):
    //    print(str(i) + ": " + str(3 * (2 ** (i-1)) ))
    /*
    After some hurdles, implementation ok: first, if k is too much, return an empty string and do not go any further
    we have precomputed number of cases per each n
    Now we need to construct actual happy string of length n
    - first third starts with a, second third start with b, while last third starts with letter c
    - next we alternate lower/upper, since we cannot use the same char
    - but NOTE whether we go up or down depends on the letter, not just number

    Runs 1ms, beats 96.9%, 345 testcases (update 19.2.2025 when it is daily - beating 99.1% now :-)
     */
    public static String getHappyString(int n, int k) {
        k--; // to make it zero based
        if (k >= LENGTHS[n]) return ""; // there are not that many happy strings of length n
        char[] letters = new char[n];
        int idx = 0;
        // first letter, three possibilities:
        int letterIdx = k / (LENGTHS[n] / 3);
        letters[idx++] = LETTERS[letterIdx];
        k %= (LENGTHS[n--] / 3);
        while (n > 0) {
            //System.out.printf("k %d, n %d, divide by %d, ", k, n, TWOTON[n - 1]);
            int move = k / TWOTON[n - 1];
            k %= TWOTON[--n];
            /*
            New letter is not based just on `move`, but also on current letter
            for moving "down", it is a=>b, b=>a, c=>a, while for moving "up" we get a=>c, b=>c, c=>b
             */
            if (move == 0 && letterIdx == 1 || move == 1 && letterIdx != 1)
                letterIdx++;
            letterIdx++;
            letterIdx %= 3;
            letters[idx++] = LETTERS[letterIdx];
            //System.out.printf("k after: %d, n after %d, move %d, letter idx %d.%n", k, n, move, letterIdx);
        }
        return new String(letters);
    }

    /*
    Test case: for a given n, it writes out all the happy strings ordered, to check if that works
     */
    public static void main(String[] args) {
        int n = 3;
        for (int k = 1; k <= LENGTHS[n]; k++)
            System.out.println(k + ". " + getHappyString(n, k));
    }

}
