package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Aoc202302ColorCubes {
    public static final int[] MAX = { 12, 13, 14};
    public static final String[] COLORS = {"red", "green", "blue"};
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_02.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int sumOfPossible = 0;
        long powerSum = 0;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            String[] data = line.split(": ");
            String[] game = data[0].split(" ");
            int gameId = Integer.parseInt(game[1]);
            String[] subsets = data[1].split("; ");
            boolean possible = true;
            int[] maxes = { 0, 0, 0}; // for part 2
            for (String subset: subsets) {
                String[] cases = subset.split(", ");
                for (String kejs: cases) {
                    String[] dat = kejs.split(" ");
                    int count = Integer.parseInt(dat[0]);
                    String color = dat[1];
                    int colorId = Arrays.asList(COLORS).indexOf(color);
                    if (count > MAX[colorId])
                        possible = false;
                    if (maxes[colorId] < count)
                        maxes[colorId] = count;
                }
            }
            if (possible)
                sumOfPossible += gameId;
            powerSum += maxes[0] * maxes[1] * maxes[2];

        }
        System.out.println("All processed, sum of possible ids is " + sumOfPossible);
        System.out.println("Sum of power of all sets is " + powerSum);
    }
}
