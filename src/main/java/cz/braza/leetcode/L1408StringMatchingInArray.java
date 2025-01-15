package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
1408. String Matching in an Array
Easy
Topics
Companies
Hint
Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.

A substring is a contiguous sequence of characters within a string



Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:

Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
 */
public class L1408StringMatchingInArray {
    /*
    Bruteforce, check all the possibilities, if one string is contained in the other, add to the list.
    Failing testcase, as the word can be contained in multiple different words and added multiple times,
    which is wrong. Changed List to Set, then return a List constructed from the set, which is a bit slow.
    Runs 5ms, beats 41.6%, 67 testcases
     */
    public List<String> stringMatching(String[] words) {
        Set<String> result = new HashSet<>();
        for (int left = 0; left < words.length; left++)
            for (int right = left + 1; right < words.length; right++)
                if (words[right].contains(words[left]))
                    result.add(words[left]);
                else if (words[left].contains(words[right]))
                    result.add(words[right]);
        return new ArrayList<>(result);
    }

    /*
    So make it a list, traverse NxN, but always check it one-way only, so that
    no word can be added multiple times and there is no need for second data structure.
    Runs 4ms, beats 86.2%
     */
    public List<String> stringMatchingListOnly(String[] words) {
        List<String> result = new ArrayList<>();
        for (int outer = 0; outer < words.length; outer++)
            for (int inner = 0; inner < words.length; inner++)
                if (inner != outer && words[inner].contains(words[outer])) {
                    result.add(words[outer]);
                    break;
                }
        return new ArrayList<>(result);
    }
}
