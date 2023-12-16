package cz.braza.advent;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AoC202304Scratchcards {
    public static final int[] POINTS = { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

    public static int processLine(String line) {
        return POINTS[getHitCount(line)];
    }

    public static int getHitCount(String line) {
        String[] main = line.split(": ");
        String[] numbers = main[1].split(" \\| ");
        String winningNumbers = " " + numbers[0] + " "; // OUT: numbers[0].replace("  ", " 0")
        String[] myNumbers = numbers[1].split(" ");
        int hitCount = 0;
        for (String num: myNumbers) {
            if (num.isBlank())
                continue;
            num = " " + num.trim() + " ";
            if (winningNumbers.contains(num)) {
                hitCount++;
            }
        }
        return hitCount;
    }

    public static int processLine2(String line) {
        return 42;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_04.txt";
        Scanner vstup = new Scanner(new File(fileName));
        int part1 = 0;
        int[] cards = new int[220];
        Arrays.fill(cards, 1);
        cards[0] = 0; // revert this back just in case
        int part2 = 0;
        int cardNumber = 0;
        // List<String> lines = Files.readAllLines(Paths.get(fileName));
        while (vstup.hasNextLine()) {
            cardNumber++;
            String line = vstup.nextLine();
            int hits = getHitCount(line);
            part1 += POINTS[hits];
            for (int i = 0; i < hits; i++)
                cards[cardNumber + i + 1] += cards[cardNumber];
        }

        System.out.println("Part 1: sum of points is " + part1);
        System.out.println("Part 2: sum of cards is " + IntStream.of(cards).sum());

    }
}
