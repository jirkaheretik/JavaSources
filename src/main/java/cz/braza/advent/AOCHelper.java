package cz.braza.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intended to summarize useful functions throughout AoC puzzles solving
 */

public class AOCHelper {
    /**
     * Reads filename and returns the content as a two dimensional array of chars
     * @param filename
     * @return
     */
    public static char[][] readFile2CharArray(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            // convert to an array:
            char[][] field = new char[lines.size()][lines.get(0).length()];
            int lineNum = 0;
            for (String line: lines)
                field[lineNum++] = line.toCharArray();
            return field;
        }
        catch (IOException ioe) {
            System.out.println("IOException when reading " + filename);
            return null;
        }
    }

    /**
     * Prints two dimensional array of chars, together with a header
     */
    public static void printField(char[][] field, String label) {
        if (!label.isBlank()) {
            System.out.println(label + ":");
            for (int i = 0; i <= label.length(); i++)
                System.out.print("=");
            System.out.println();
        }
        for (int row = 0; row < field.length; row++) {
            System.out.println(new String(field[row]));
        }
    }

    /**
     * Counts individual character frequencies for a given char[][] parameter
     * Returns a map, with characters as keys and their frequencies as values
     * @param arr
     * @return
     */
    public static Map<Character, Integer> getFrequencies(char[][] arr) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (int r = 0; r < arr.length; r++)
            for (int c = 0; c < arr[r].length; c++)
                freq.put(arr[r][c], freq.getOrDefault(arr[r][c], 0) + 1);
        return freq;
    }

    /**
     * Prints character frequencies, @see getFrequencies()
     * @param arr
     */
    public static void printFrequencies(char[][] arr) {
        Map<Character, Integer> freq = getFrequencies(arr);
        for (var entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + "x");
        }
    }


}
