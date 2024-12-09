package cz.braza.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
1078. Occurrences After Bigram
Easy
Topics
Companies
Hint
Given two strings first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

Return an array of all the words third for each occurrence of "first second third".



Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
Example 2:

Input: text = "we will we will rock you", first = "we", second = "will"
Output: ["we","rock"]


Constraints:

1 <= text.length <= 1000
text consists of lowercase English letters and spaces.
All the words in text are separated by a single space.
1 <= first.length, second.length <= 10
first and second consist of lowercase English letters.
text will not have any leading or trailing spaces.
 */
public class L1078OccurencesAfterBigram {
    // without words splitting, couple of edge cases/conditions
    // runs 3ms, beats 7.6%
    public String[] findOcurrences(String text, String first, String second) {
        int pos = 0;
        text = " " + text + " "; // to ensure there is always a space after third word and before first
        String lookFor = " " + first + " " + second + " ";
        int length = lookFor.length();
        List<String> result = new ArrayList<>();
        boolean lookAgain = true;
        while (lookAgain) {
            pos = text.indexOf(lookFor, pos);
            if (pos < 0) break;
            int spacePos = text.indexOf(" ", pos + length + 1);
            if (spacePos < 0) break; // string ends with first + second, no more words
            result.add(text.substring(pos + length, spacePos));
            pos++;
        }
        String[] arr = new String[0];
        return result.toArray(arr);
    }

    // split to words, very easy logic
    // runs 0ms, beats 100% (same as 85% others)
    public String[] findOcurrencesSplit(String text, String first, String second) {
        List<String> result = new ArrayList<>();
        String[] words = text.split(" ");
        for (int index = 0; index < words.length - 2; index++)
            if (first.equals(words[index]) && second.equals(words[index + 1]))
                result.add(words[index + 2]);
        String[] arr = new String[0];
        return result.toArray(arr);
    }

}
