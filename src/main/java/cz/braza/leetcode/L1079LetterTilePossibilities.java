package cz.braza.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Daily 17.2.2025

1079. Letter Tile Possibilities
Medium
Topics
Companies
Hint
You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.



Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1


Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
 */
public class L1079LetterTilePossibilities {
    public static final int[] FAKT = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    /*
    From Editorial - creating possible strings and counting their permutations. Sorting chars at start allows for easier checking.

    Still I cannot shake off the feeling this could be computed WITHOUT constructing all the strings

    Runs 7ms, beats 58.9%, 86 testcases
     */
    public int numTilePossibilities(String tiles) {
        Set<String> seen = new HashSet<>();

        // Sort characters to handle duplicates efficiently
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        String sortedTiles = new String(chars);

        // Find all unique sequences and their permutations
        return generateSequences(sortedTiles, "", 0, seen) - 1;
    }

    private static int countPermutations(String seq) {
        // Count frequency of each character
        int[] charCount = new int[26];
        for (char ch : seq.toCharArray()) charCount[ch - 'A']++;

        // Calculate permutations using factorial formula
        int total = FAKT[seq.length()];
        for (int count : charCount) total /= FAKT[count];
        return total;
    }

    private static int generateSequences(String tiles, String current, int pos, Set<String> seen) {
        if (pos >= tiles.length())
            // If new sequence found, count its unique permutations
            if (seen.add(current)) return countPermutations(current);
            else return 0;

        // Try including and excluding current character
        return generateSequences(tiles, current, pos + 1, seen) +
               generateSequences(tiles, current + tiles.charAt(pos), pos + 1, seen);
    }
}
