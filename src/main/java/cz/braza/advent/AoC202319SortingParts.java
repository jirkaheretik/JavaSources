package cz.braza.advent;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.IntStream;

public class AoC202319SortingParts {

    /**
     * From a string like x=256 or m=1234 get the numerical value and return it
     * @param part
     * @return
     */
    public static int getValue(String part) {
        String[] data = part.split("=");
        return Integer.parseInt(data[1]);
    }
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_19.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long sumOfRating = 0;
        boolean readingWorkflow = true;
        Map<String, String> sorting = new HashMap<>();
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (line.isBlank()) {
                if (readingWorkflow)
                    readingWorkflow = false;
                else
                    System.out.println("EOF found probably?!");
            } else {
                if (readingWorkflow) {
                    String key = line.substring(0, line.indexOf('{'));
                    String value = line.substring(line.indexOf('{') + 1, line.length() - 1);
                    //System.out.println("Key '" + key + "' with " + value);
                    sorting.put(key, value);
                } else {
                    // parse and process a part:
                    // remove the curly braces and split into parts by a comma:
                    String[] parts = line.substring(1, line.length() - 1).split(",");
                    int[] xmas = {getValue(parts[0]), getValue(parts[1]), getValue(parts[2]), getValue(parts[3])};
                    if (accepted(sorting, xmas)) sumOfRating += IntStream.of(xmas).sum();
                    //System.out.println(line + ",x = " + x + ", m = " + m + ", a = " + a + ", s = " + s + ", sum: " + (x + m + a + s));
                }
            }
        }
        System.out.println("Part 1: Sum of ratings: " + sumOfRating);
    }

    public static boolean accepted(Map<String, String> map, int[] xmas) {
        String current = "in";
        while (true) {
            System.out.println("DBG: " + current);
            String[] rules = map.get(current).split(",");
            for (String rule: rules) {
                if (rule.indexOf(':') >= 0) {
                    String[] conds = rule.split(":");
                    String cond = conds[0];
                    int value = Integer.parseInt(cond.substring(2));
                    String varname = cond.substring(0, 1);
                    char relation = cond.charAt(1);
                    int myValue = xmas["xmas".indexOf(varname)];
                    if ((relation == '>' && myValue > value) || (relation == '<' && myValue < value)) {
                        if ("A".equals(conds[1])) return true;
                        if ("R".equals(conds[1])) return false;
                        current = conds[1];
                        break; // break the for loop
                    }
                } else {
                    if ("A".equals(rule)) return true;
                    if ("R".equals(rule)) return false;
                    current = rule;
                }
            }
        }
    }
}
