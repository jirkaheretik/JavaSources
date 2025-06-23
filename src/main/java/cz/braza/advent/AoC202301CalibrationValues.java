package cz.braza.advent;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class AoC202301CalibrationValues {
    public static String[] DIGITS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static int firstValue(String line) {
        int[] results = new int[20];
        for (int i = 0; i < DIGITS.length; i++)
            results[i] = line.indexOf(DIGITS[i]);
        int minimum = line.length();
        int minIndex = -1;
        for (int i = 0; i < results.length; i++)
            if (results[i] >= 0 && results[i] < minimum) {
                minimum = results[i];
                minIndex = i;
            }
        return minIndex % 10;
    }
    public static int lastValue(String line) {
        int[] results = new int[20];
        for (int i = 0; i < DIGITS.length; i++)
            results[i] = line.lastIndexOf(DIGITS[i]);
        int maximum = -1;
        int maxIndex = -1;
        for (int i = 0; i < results.length; i++)
            if (results[i] > maximum) {
                maximum = results[i];
                maxIndex = i;
            }
        return maxIndex % 10;
    }
    public static int getP2Value(String line) {
        return 10 * firstValue(line) + lastValue(line);
    }
    public static int getValue(String line) {
        int vysledek = 0;
        // find first:
        for (int i = 0; i < line.length(); i++) {
            int c = (int) line.charAt(i);
            if (c >= 48 && c <= 59) {
                vysledek += 10 * (c - 48);
                break;
            }
        }
        // find last:
        for (int i = line.length() - 1; i >= 0; i--) {
            int c = (int) line.charAt(i);
            if (c >= 48 && c <= 59) {
                vysledek += (c - 48);
                break;
            }
        }
        return vysledek;
    }

    public static void saySolution() throws Exception {
        int sum = 0;
        int lineCount = 0;
        String cisla = "0123456789";
        for (String line : java.nio.file.Files.readAllLines(Paths.get("/home/jirka/src/java0/aoc23_01.txt"))) {
            String cislaZRadku = "";
            String dvojciferne = "";
            lineCount++;

            for (char c : line.toCharArray()) {
                if (cisla.contains(String.valueOf(c))) {
                    cislaZRadku += c;
                }
            }
            dvojciferne += "" + cislaZRadku.charAt(0) + cislaZRadku.charAt(cislaZRadku.length()-1);
            sum = sum + Integer.parseInt("" + cislaZRadku.charAt(0) + cislaZRadku.charAt(cislaZRadku.length()-1));
            //System.out.println(lineCount + ". " + dvojciferne + " out of " + cislaZRadku);
        }
        System.out.println("Součet: " + sum);
    }
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_01.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int calibration = 0;
        int calibrationPart2 = 0;
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            calibration += getValue(line);
            calibrationPart2 += getP2Value(line);
        }
        System.out.println("Part 1: sum of calibration values is " + calibration);
        System.out.println("Part 2: sum of calibration values is " + calibrationPart2);
        saySolution();
    }
}
