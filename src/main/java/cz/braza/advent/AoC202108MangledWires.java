package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class AoC202108MangledWires {

    // Find a single char that is in first string but not the other
    private static String findDiff(String longer, String shorter) {
        String result = "";
        for (int i = 0; i < longer.length(); i++) {
            char c = longer.charAt(i);
            if (!shorter.contains("" + c))
                result += c;
        }
        return result;
    }

    // For a given input (10 strings split by spaces) find a mapping, that is a string like 'cadgbfe'
    private static String findMapping(String input) {
        // split to parts:
        String[] bits = input.split(" ");
        // order each member alphabetically
        for (int i = 0; i < bits.length; i++)
            bits[i] = order(bits[i]);
        // order by length, then alphabetically
        Arrays.sort(bits, Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        String[] mapping = new String[7]; // itermediate mapping for each of the wires a-g
        mapping[0] = findDiff(bits[1], bits[0]); // diff between 7 and 1 is wire A
        String ctyrkaZbytek = findDiff(bits[2], bits[0]); // diff between 4 and 1 are wires b, d (do not know their order yet)
        // find number 3 (bits[3], bits[4] or bits[5]) that has only 2char diff from bits[1] (number 7)
        String trojkaZbytek = "";
        for (int i = 3; i <= 5; i++) {
            trojkaZbytek = findDiff(bits[i], bits[1]);
            if (trojkaZbytek.length() == 2)
                break;
        }
        // char in ctyrkaZbytek and trojkaZbytek is wire D (mapping[3]):
        mapping[3] = "" + (ctyrkaZbytek.contains("" + trojkaZbytek.charAt(0)) ? trojkaZbytek.charAt(0) : trojkaZbytek.charAt(1));
        // then wire B is the rest of ctyrkaZbytek and wire G is the rest of trojkaZbytek:
        mapping[1] = findDiff(ctyrkaZbytek, mapping[3]);
        mapping[6] = findDiff(trojkaZbytek, mapping[3]);
        // one of chars in bits[0] together with A, B, D and G forms a digit (and is therefore wire F), the other is wire C:
        String dummy = order(mapping[0] + mapping[1] + mapping[3] + mapping[6] + bits[0].charAt(0));
        if (dummy.equals(bits[3]) || dummy.equals(bits[4]) || dummy.equals(bits[5])) {
            mapping[5] = "" + bits[0].charAt(0);
            mapping[2] = "" + bits[0].charAt(1);
        } else {
            mapping[5] = "" + bits[0].charAt(1);
            mapping[2] = "" + bits[0].charAt(0);
        }
        // rest is wire E:
        mapping[4] = findDiff(bits[9], mapping[0] + mapping[1] + mapping[2] + mapping[3] + mapping[5] + mapping[6]);
        // sanity check: all the lengths MUST be 1, build result:
        String result = "";
        for (String part: mapping) {
            if (part.length() != 1)
                System.out.println("Error in mapping procedure, found: " + part.toUpperCase());
            result += part;
        }

        return result;
    }

    /**
     * returns the same string with single chars ordered alphabetically
     * @param input
     * @return
     */
    private static String order(String input) {
        char[] bits = input.toCharArray();
        Arrays.sort(bits);
        return new String(bits);
    }

    private static int getWiredValue(String on) {
        return switch (on) {
            case "acdeg" -> 2;
            case "acdfg" -> 3;
            case "abdfg" -> 5;
            case "abdefg" -> 6;
            case "abcdfg" -> 9;
            case "abcefg" -> 0;
            case "cf" -> 1;
            case "bcdf" -> 4;
            case "acf" -> 7;
            default -> logProblem(on);
        };
    }

    private static int logProblem(String xxx) {
        //System.out.println("Err in getWiredValue(" + xxx + ")");
        return 9999;
    }

    /**
     * Having an input (first parameter, wires lit up) and wiring (mapping to the correct one),
     * return the right wires that SHOULD be lit up (and we can therefore recognize the letter)
     * @param from mangled wires lit up
     * @param by mapping
     * @return correct wires lit up, ordered alphabetically, @see getWiredValue()
     */
    private static String transform(String from, String by) {
        String result = "";
        for (char c: from.toCharArray()) {
            result += "abcdefg".charAt(by.indexOf(c));
        }
        return order(result);
    }

    // For a given input and wiring, returns a digit value
    private static int findValue(String connection, String wiring) {
        return switch (connection.length()) {
            case 2 -> 1;
            case 3 -> 7;
            case 4 -> 4;
            case 7 -> 8;
            case 5, 6 -> getWiredValue(transform(connection, wiring));
            default -> -1;
        };
    }

    private static int findSum(String[] input, String wiring) {
        int sum = 0;
        for (String bit: input)
            sum = sum * 10 + findValue(order(bit), wiring);
        return sum;
    }
    public static void main(String[] args) throws Exception {
        // TESTS (for order() function):
        //System.out.println(order("gfbacde"));
        //System.out.println(order("acbdfg"));
        //System.out.println(order("dgac"));

        String fileName = "/home/jirka/src/java0/aoc21_08.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int sumOfEasy = 0;
        long sumTotal = 0;
        while (vstup.hasNextLine()) {
            String[] line = vstup.nextLine().split(" \\| ");
            String input = line[0].trim();
            String[] output = line[1].trim().split(" ");
            for (String single: output)
                if ((single.length() != 6) && (single.length() != 5)) {
                    sumOfEasy++;
                }
            String wiring = findMapping(input);
            int smallSum = findSum(output, wiring);
            if (smallSum < 9999) {
                sumTotal += smallSum;
                // System.out.println("Line processed ok.");
            } else {
                System.out.println("ERR Line: " + input  + ", wiring found: " + wiring);
            }
        }
        System.out.println("Part 1: Total easy numbers: " + sumOfEasy);
        System.out.println("Part 2: Sum of outputs: " + sumTotal);

        // TESTS only (for findMapping)
        //System.out.println(findMapping("dcga cadgbfe gecba cbfde eda cdbea gbadfe fegcba bedgca da"));
        //System.out.println(findMapping("fe ecf fdbagec dcgfab defbca efbcga daceg cfdea bfed fdbca"));
        //System.out.println(findMapping("fbg cgafe bf bfdc ebdcag fgcdba gbdecaf bcfag badgc gdefab"));
    }
}
