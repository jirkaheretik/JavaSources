package cz.braza.leetcode;

/*
2490. Circular Sentence
Easy
Topics
Companies
Hint
A sentence is a list of words that are separated by a single space with no leading or trailing spaces.

For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.

A sentence is circular if:

The last character of a word is equal to the first character of the next word.
The last character of the last word is equal to the first character of the first word.
For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.

Given a string sentence, return true if it is circular. Otherwise, return false.
 */
public class L2490CircularSentence {
    /*
    Three "tramway" solutions to compare results.
    First: split into words, for each word compare first char with previous last, fail fast
    Runs 1ms, beats 96.8%
     */
    public static boolean isCircularSentenceWords(String sentence) {
        // save splitting if ends do not match:
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) return false;
        String[] words = sentence.split(" ");
        char last = words[0].charAt(words[0].length() - 1);
        for (int index = 1; index < words.length; index++)
            if (words[index].charAt(0) != last) return false;
            else last = words[index].charAt(words[index].length() - 1);
        // return true if we get here:
        return true;
    }

    /*
    Second "tramway" solution:
    Split into array of chars, whenever we find a space, compare adjacent chars, fail fast
    Runs 1ms, beats 96.8%
     */
    public static boolean isCircularSentenceChars(String sentence) {
        // save splitting if ends do not match:
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) return false;
        char[] znaky = sentence.toCharArray();
        for (int index = 1; index < znaky.length; index++)
            if (znaky[index] == ' ')
                if (znaky[index - 1] != znaky[index + 1])
                    return false;
        return true;
    }

    /*
    Third: same as 2nd, though using s.charAt() instead of array of chars (in place)
    Runs 1ms, beats 96.8%
     */
    public static boolean isCircularSentenceInPlace(String sentence) {
        // save splitting if ends do not match:
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) return false;
        for (int index = 1; index < sentence.length(); index++)
            if (sentence.charAt(index) == ' ')
                if (sentence.charAt(index - 1) != sentence.charAt(index + 1))
                    return false;
        return true;
    }

    public static boolean isCircularSentence(String s) {
        return isCircularSentenceInPlace(s);
    }

    public static void main(String[] args) {
        System.out.println(isCircularSentence("leetcode exercises sound delightful"));
        System.out.println(isCircularSentence("Leetcode ec cool"));
    }
}
