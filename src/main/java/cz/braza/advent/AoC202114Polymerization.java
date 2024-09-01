package cz.braza.advent;

import cz.braza.educanet.CharFrequency;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AoC202114Polymerization {
    public static String makeStep(String input, Map<String, String> instructions) {
        String result = input.substring(0, 1);
        for (int pos = 1; pos < input.length(); pos++) {
            char first = input.charAt(pos - 1);
            char second = input.charAt(pos);
            String key = "" + first + second;
            String value = instructions.get(key);
            result += value + second;
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String fname = "/home/jirka/src/java0/aoc21_14.txt";
        Scanner sc = new Scanner(new File(fname));
        String input = sc.nextLine();
        sc.nextLine(); // skip empty line
        Map<String, String> instrukce = new HashMap<>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().trim().split(" -> ");
            instrukce.put(line[0], line[1]);
        }
        System.out.println("Input: " + input + " (length " + input.length() + ")");
        System.out.println("Instruction count: " + instrukce.size());
        int rounds = 10;
        for (int round = 0; round < rounds; round++) {
            input = makeStep(input, instrukce);
            System.out.println("Step " + (round + 1) + " length " + input.length());
        }
        CharFrequency counter = new CharFrequency(input);
        System.out.println("Result: " + (counter.highestFrequency() - counter.lowestFrequency()));
    }
}
