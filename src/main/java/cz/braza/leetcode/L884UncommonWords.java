package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A sentence is a string of single-space separated words where each word consists only of lowercase letters.
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 *
 * Output: ["sweet","sour"]
 *
 * Explanation:
 *
 * The word "sweet" appears only in s1, while the word "sour" appears only in s2.
 *
 * Example 2:
 *
 * Input: s1 = "apple apple", s2 = "banana"
 *
 * Output: ["banana"]
 */
public class L884UncommonWords {
    public static String[] uncommonFromSentences(String s1, String s2) {
        HashMap<String, Integer> mapa = new HashMap<>();
        String[] slova = s1.split(" ");
        for (String slovo: slova)
            mapa.put(slovo, mapa.getOrDefault(slovo, 0) + 1);
        slova = s2.split(" ");
        for (String slovo: slova)
            mapa.put(slovo, mapa.getOrDefault(slovo, 0) + 1);
        ArrayList<String> result = new ArrayList<>();
        for (String key: mapa.keySet())
            if (mapa.get(key) == 1)
                result.add(key);
        String[] dummy = new String[1];
        return result.toArray(dummy);
    }
}
