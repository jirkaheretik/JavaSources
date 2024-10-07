package cz.braza.leetcode;
// https://leetcode.com/problems/sentence-similarity-iii/

/**
 * You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.
 *
 * Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be separated from existing words by spaces.
 *
 * For example,
 *
 * s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in s1.
 * s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into s1, it is not separated from "Frog" by a space.
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 *
 * Output: true
 *
 * Explanation:
 *
 * sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 *
 * Example 2:
 *
 * Input: sentence1 = "of", sentence2 = "A lot of words"
 *
 * Output: false
 *
 * Explanation:
 *
 * No single sentence can be inserted inside one of the sentences to make it equal to the other.
 *
 * Example 3:
 *
 * Input: sentence1 = "Eating right now", sentence2 = "Eating"
 *
 * Output: true
 *
 * Explanation:
 *
 * sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
 */
public class L1813SentenceSimilarity {
    public static boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] shorter = sentence1.split(" ");
        String[] longer = sentence2.split(" ");
        // found last common index from the start:
        int startIndex = 0;
        while (startIndex < shorter.length && startIndex < longer.length && shorter[startIndex].equals(longer[startIndex]))
            startIndex++;
        int endIndex = 0;
        while (endIndex < shorter.length && endIndex < longer.length && shorter[shorter.length - 1 - endIndex].equals(longer[longer.length - 1 - endIndex]))
            endIndex++;
        //System.out.println("End of processing");
        //System.out.println("S1 length: " + shorter.length + ", S2 length: " + longer.length + ", sum: " + (startIndex + endIndex));
        return (startIndex + endIndex >= shorter.length || startIndex + endIndex >= longer.length);
    }

    public static boolean areSentencesSimilarWrong(String sentence1, String sentence2) {
        // make first sentence the shorter one:
        if (sentence1.length() > sentence2.length())
            return areSentencesSimilar(sentence2, sentence1);
        String[] shorter = sentence1.split(" ");
        String[] longer = sentence2.split(" ");
        boolean hadArbitrary = false;
        int longerIndex = 0;
        for (int index = 0; index < shorter.length; index++) {
            if (longerIndex >= longer.length) return !hadArbitrary;
            if (shorter[index].equals(longer[longerIndex])) {
                System.out.println("skipping same word " + shorter[index]);
                longerIndex++;
                continue;
            } else {
                if (hadArbitrary) {
                    System.out.println("Already skipped one arbitrary section. Now looking for " + shorter[index] + " and found " + longer[longerIndex] + " instead, returning false then.");
                    return false; // at least two blocks that are not the same
                }
                hadArbitrary = true;
                while (longerIndex < longer.length && !shorter[index].equals(longer[longerIndex])) {
                    System.out.println("ARB: looking for " + shorter[index] + ", skipping different word " + longer[longerIndex]);
                    longerIndex++;
                }
                if (longerIndex >= longer.length) {
                    System.out.println("ARB: run out of words while looking for " + shorter[index] + ", so false is the result");
                    return false; // not found the rest
                } else {
                    System.out.println("ARB out: found the match!");
                    longerIndex++; // must have found the shorter word again, back on track, go on
                }
            }
        }
        System.out.println("Got through the list. Had arbitrary: " + (hadArbitrary ? "YES" : "NO") + ", end of longer: " + longerIndex + ", length of longer: " + longer.length);
        return !hadArbitrary || longerIndex == longer.length;
    }

    public static void test(String s1, String s2) {
        System.out.println("'" + s1 + "' vs '" + s2 + "': " + areSentencesSimilar(s1, s2));
    }

    public static void main(String[] args) {
        test("My Haley", "My name is Haley");
        test("My name is Haley", "My Haley");
        test("of", "A lot of words");
        test("Eating", "Eating right now");
        test("c h p Ny", "c BDQ r h p Ny");
        test("Jfa jfvmGU bKSSX uQ AmTzbBW EF jdc ft Z g VcM oNlI jeX q mNG YnUgGSnejt Y", "Y ggUFOmtf woKuTtO W uwJZ Zan wgm zprl Kgn mAY xLlCH phA UIVKIohfw al g m");
        test("Y ggUFOmtf woKuTtO W uwJZ Zan wgm zprl Kgn mAY xLlCH phA UIVKIohfw al g m", "Jfa jfvmGU bKSSX uQ AmTzbBW EF jdc ft Z g VcM oNlI jeX q mNG YnUgGSnejt Y");
        test("A", "a A b A");
    }
}
