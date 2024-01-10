package cz.braza.advent.a2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day16_AuntSue {
    public static final String SUE = "children: 3, cats: 7, samoyeds: 2, pomeranians: 3, akitas: 0, vizslas: 0, goldfish: 5, trees: 3, cars: 2, perfumes: 1, ";
    public static final String[] TRAITS = {"children", "cats", "samoyeds", "pomeranians", "akitas", "vizslas", "goldfish", "trees", "cars", "perfumes"};
    public static final ArrayList<String> TTS = new ArrayList<>();
    public static final int[] VALUES = {3, 7, 2, 3, 0, 0, 5, 3, 2, 1};
    public static void main(String[] args) throws FileNotFoundException {
        for (String t: TRAITS) TTS.add(t);
        String fileName = "/home/jirka/src/java0/aoc15_16.txt";
        Scanner vstup = new Scanner(new File(fileName));
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            if (!line.startsWith("Sue ")) continue;
            int colPos = line.indexOf(": ");
            int sueId = Integer.parseInt(line.substring(4, colPos));
            String[] traits = line.substring(colPos + 2).split(", ");
            boolean failsP1 = false;
            boolean failsP2 = false;
            for (String trait : traits) {
                if (!SUE.contains(trait + ","))
                    failsP1 = true;
                if (!compare(trait))
                    failsP2 = true;
            }
            if (!failsP1)
                System.out.println("Part 1: Seems Sue " + sueId + " is ok with line " + line);
            if (!failsP2)
                System.out.println("Part 2: Seems Sue " + sueId + " is ok with line " + line);
        }

    }
    public static boolean compare(String trait) {
        String[] data = trait.split(": ");
        String key = data[0];
        int value = Integer.parseInt(data[1].trim());
        int index = TTS.indexOf(key);
        int auntiesVal = VALUES[index];
        return key.equals("cats") || key.equals("trees") ? auntiesVal < value : ( key.equals("pomeranians") || key.equals("goldfish") ? auntiesVal > value : auntiesVal == value );
    }
}
