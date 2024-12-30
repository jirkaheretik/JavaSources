package cz.braza.leetcode;

import java.math.BigInteger;
import java.util.Arrays;

/*
1639. Number of Ways to Form a Target String Given a Dictionary
Hard
Topics
Companies
Hint
You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.



Example 1:

Input: words = ["acca","bbbb","caca"], target = "aba"
Output: 6
Explanation: There are 6 ways to form target.
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
Example 2:

Input: words = ["abba","baab"], target = "bab"
Output: 4
Explanation: There are 4 ways to form target.
"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 1000
All strings in words have the same length.
1 <= target.length <= 1000
words[i] and target contain only lowercase English letters.
 */
public class L1639Ways2BuildStringFromDictionary {
    /*
    First attempt is recursive dfs(), takes long, but also does not
    work properly, as it runs out of long.

    Got getWords() from Editorial, updated to use less parameters,
    uses memoization.
    Runs 19ms, beats 99.6%
     */
    public static int numWays(String[] words, String target) {
        int tLength = target.length();
        int wLength = words[0].length();
        // count word frequencies at ith positions
        int[][] frequencies = new int[wLength][26];
        for (String word: words)
            for (int idx = 0; idx < word.length(); idx++)
                frequencies[idx][word.charAt(idx) - 'a']++;
        // memoizace:
        int[][] memo = new int[wLength][tLength];
        for (int i = 0; i < wLength; i++) {
            Arrays.fill(memo[i], -1);
        }
        // dfs
        // long[] variants = new long[1];
        return (int) getWords(target, 0, 0, memo, frequencies);
        /*
        long tmp = dfs(frequencies, target, 0, 0);
        BigInteger tmpBI = dfsBI(frequencies, target, 0, 0);
        System.out.printf("Full result %d.%nFull BI res %s.%n%s.%n", tmp, tmpBI.toString(), tmpBI.mod(BigInteger.valueOf(1_000_000_007)).toString());

         */
        //return (int)(tmp % 1_000_000_007);
        //return (int)(dfs(frequencies, target, 0, 0) % 1_000_000_007);
        //return (int)(variants[0] % 1_000_000_007);
    }

    private static long getWords(String target, int wordsIndex, int targetIndex, int[][] dp, int[][] charFrequency) {
        int MOD = 1000000007;
        int wLength = charFrequency.length;

        // If the target is fully matched
        if (targetIndex == target.length()) return 1;

        // If we have no more columns in the words or not enough columns left to match
        // the target
        if (wordsIndex == wLength || wLength - wordsIndex < target.length() - targetIndex) return 0;

        // If already computed, return the stored result
        if (dp[wordsIndex][targetIndex] != -1) return dp[wordsIndex][targetIndex];

        long countWays = 0;
        int curPos = target.charAt(targetIndex) - 'a';

        // Don't match any character of the target with the current word column
        countWays += getWords(target, wordsIndex + 1, targetIndex, dp, charFrequency);

        // Match the current character of the target with the current word column
        countWays += charFrequency[wordsIndex][curPos] * getWords(target, wordsIndex + 1, targetIndex + 1, dp, charFrequency);

        // Store the result in dp and return the answer
        dp[wordsIndex][targetIndex] = (int) (countWays % MOD);

        return dp[wordsIndex][targetIndex];
    }


    public static long dfs(final int[][] frequencies, final String target, int fIdx, int tIdx) {
        // if not enough letters in words, return 0
        if (target.length() - tIdx > frequencies.length - fIdx) return 0;
        // if we are out of frequencies or out of target, return 1 (since we did not return 0 above)
        if (fIdx >= frequencies.length || tIdx >= target.length()) return 1;
        // we can now either choose letter at current index (if non zero freq)
        long res = frequencies[fIdx][target.charAt(tIdx) - 'a'] > 0 ?
                frequencies[fIdx][target.charAt(tIdx) - 'a'] * dfs(frequencies, target, fIdx + 1, tIdx + 1)
                : 0;
        // or skip it
        res += dfs(frequencies, target, fIdx + 1, tIdx);
        return res;
    }

    public static BigInteger dfsBI(final int[][] frequencies, final String target, int fIdx, int tIdx) {
        // if not enough letters in words, return 0
        if (target.length() - tIdx > frequencies.length - fIdx) return BigInteger.ZERO;
        // if we are out of frequencies or out of target, return 1 (since we did not return 0 above)
        if (fIdx >= frequencies.length || tIdx >= target.length()) return BigInteger.ONE;
        // we can now either choose letter at current index (if non zero freq)
        BigInteger res = BigInteger.ZERO;
        if (frequencies[fIdx][target.charAt(tIdx) - 'a'] > 0)
            res = res.add(dfsBI(frequencies, target, fIdx + 1, tIdx + 1).multiply(BigInteger.valueOf(frequencies[fIdx][target.charAt(tIdx) - 'a'])));
        // or skip it
        res = res.add(dfsBI(frequencies, target, fIdx + 1, tIdx));
        return res;
    }

    public static void main(String[] args) {
        // example:
        String[] w = {"acca","bbbb","caca"};
        System.out.println(numWays(w, "aba"));
        // test case 50:
        String[] words = {"cabbaacaaaccaabbbbaccacbabbbcb","bbcabcbcccbcacbbbaacacaaabbbac","cbabcaacbcaaabbcbaabaababbacbc","aacabbbcaaccaabbaccacabccaacca","bbabbaabcaabccbbabccaaccbabcab","bcaccbbaaccaabcbabbacaccbbcbbb","cbbcbcaaaacacabbbabacbaabbabaa","cbbbbbbcccbabbacacacacccbbccca","bcbccbccacccacaababcbcbbacbbbc","ccacaabaaabbbacacbacbaaacbcaca","bacaaaabaabccbcbbaacacccabbbcb","bcbcbcabbccabacbcbcaccacbcaaab","babbbcccbbbbbaabbbacbbaabaabcc","baaaacaaacbbaacccababbaacccbcb","babbaaabaaccaabacbbbacbcbababa","cbacacbacaaacbaaaabacbbccccaca","bcbcaccaabacaacaaaccaabbcacaaa","cccbabccaabbcbccbbabaaacbacaaa","bbbcabacbbcabcbcaaccbcacacccca","ccccbbaababacbabcaacabaccbabaa","caaabccbcaaccabbcbcaacccbcacba","cccbcaacbabaacbaaabbbbcbbbbcbb","cababbcacbabcbaababcbcabbaabba","aaaacacaaccbacacbbbbccaabcccca","cbcaaaaabacbacaccbcbcbccaabaac","bcbbccbabaccabcccacbbaacbbcbba","cccbabbbcbbabccbbabbbbcaaccaab","acccacccaabbcaccbcaaccbababacc","bcacabaacccbbcbbacabbbbbcaaaab","cacccaacbcbccbabccabbcbabbcacc","aacabbabcaacbaaacaabcabcaccaab","cccacabacbabccbccaaaaabbcacbcc","cabaacacacaaabaacaabababccbaaa","caabaccaacccbaabcacbcbbabccabc","bcbbccbbaaacbaacbccbcbababcacb","bbabbcabcbbcababbbbccabaaccbca","cacbbbccabaaaaccacbcbabaabbcba","ccbcacbabababbbcbcabbcccaccbca","acccabcacbcbbcbccaccaacbabcaab","ccacaabcbbaabaaccbabcbacaaabaa","cbabbbbcabbbbcbccabaabccaccaca","acbbbbbccabacabcbbabcaacbbaacc","baaababbcabcacbbcbabacbcbaaabc","cabbcabcbbacaaaaacbcbbcacaccac"};
        System.out.println(words.length);
        System.out.println(numWays(words, "acbaccacbbaaabbbabac"));
    }
}
