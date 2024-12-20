package cz.braza.advent.a2024;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day19TowelPatterns {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc24_19.txt";
        Scanner vstup = new Scanner(new File(fileName));
        String[] available = vstup.nextLine().split(", ");
        vstup.nextLine(); // empty line
        String[] patterns = new String[400];
        int index = 0;
        int shortestPattern = 1000;
        int longestPattern = 0;
        int shortestTowel = 1000;
        int longestTowel = 0;
        int notB = 0;
        int hasB = 0;
        for (String t: available) {
            if (t.length() < shortestTowel)
                shortestTowel = t.length();
            if (t.length() > longestTowel)
                longestTowel = t.length();
            if (t.length() == 1) System.out.println(t);
            if (t.contains("b")) hasB++;
            else if (t.length() > 1) notB++;

        }
        System.out.printf("Patterns with b: %d, longer patterns that can be removed: %d%n", hasB, notB);
        int alreadyPossible = 0;
        while (vstup.hasNextLine()) {
            patterns[index] = vstup.nextLine();
            if (patterns[index].length() < shortestPattern)
                shortestPattern = patterns[index].length();
            else if (patterns[index].length() > longestPattern)
                longestPattern = patterns[index].length();
            if (!patterns[index].contains("b")) alreadyPossible++;
            index++;
        }
        System.out.printf("Processed 400 records. Already possible: %d. Available patterns: %d in lengths of %d to %d, while expected results are in lengths of %d to %d.%n", alreadyPossible, available.length, shortestTowel, longestTowel, shortestPattern, longestPattern);
        int possibleCount = 0;
        long combinationSum = 0;
        HashMap<String, Long> memo = new HashMap<>();
        for (String pattern: patterns) {
            long comb = possibleCount(pattern, available, memo);
            System.out.printf("%d possibilities for pattern %s%n", comb, pattern);
            combinationSum += comb;
            if (comb > 0)
                possibleCount++;
            else
                System.out.println("Impossible pattern " + pattern);
        }
        System.out.println("Part 1: " + possibleCount);
        System.out.println("Part 2: " + combinationSum);
        System.out.println("Our memo hashmap has " + memo.size() + " entries.");
    }

    /*
    For part 1 only, gets true/false whether possible or not
     */
    public static boolean possible(String pattern, String[] towels) {
        for (String t: towels)
            if (pattern.indexOf(t) == 0)
                if (pattern.equals(t)) return true;
                else if (possible(pattern.substring(t.length()), towels)) return true;
        return false; // no pattern matching the beginning of the target pattern
    }

    public static long possibleCount(String pattern, String[] towels, HashMap<String, Long> memo) {
        if (memo.containsKey(pattern)) return memo.get(pattern);
        long res = 0;
        for (String t: towels)
            if (pattern.indexOf(t) == 0)
                if (pattern.equals(t)) res += 1;
                else
                    res += possibleCount(pattern.substring(t.length()), towels, memo);
        memo.put(pattern, res);
        return res; // no pattern matching the beginning of the target pattern
    }
}
