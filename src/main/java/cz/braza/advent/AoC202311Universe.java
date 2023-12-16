package cz.braza.advent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AoC202311Universe {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc23_11.txt";
        Scanner vstup = new Scanner(new File(fileName));
        final int EXPANDBY = 1000000;
        final int SIZE = 140;
        int lineCount = 0;
        int emptyLinesCount = 0;
        String[] universe = new String[SIZE];
        List<Integer> emptyRows = new ArrayList<>();
        List<Integer> emptyCols = new ArrayList<>();
        while (vstup.hasNextLine()) {
            // load the universe and expand it vertically (adding lines) in the process
            String line = vstup.nextLine();
            if (!line.contains("#")) {
                // line is empty
                emptyRows.add(lineCount);
                emptyLinesCount++;
                /*
                for (int i = 0; i < EXPANDBY; i++) {
                    universe[lineCount++] = line;
                    emptyLinesCount++;
                }
                 */
            }
            universe[lineCount++] = line;
        }
        System.out.println("Data loaded, added " + emptyLinesCount + " empty lines out of total " + lineCount);
        // we still need to expand the univers horizontally, but we can do it while analyzing "galaxies"
        // int emptyColsCount = 0;
        List<Integer> galaxies = new ArrayList<>();
        for (int col = 0; col < SIZE; col++) {
            boolean hasGalaxy = false;
            for (int row = 0; row < lineCount; row++)
                if (universe[row].charAt(col) == '#') {
                    hasGalaxy = true;
                    System.out.println("Found galaxy at [" + row + "," + col + "]");
                    galaxies.add(1000 * row + col);
                }
            if (!hasGalaxy) {
                emptyCols.add(col);
                // emptyColsCount += EXPANDBY;
            }
        }
        System.out.println("Universe scanned, found " + galaxies.size() + " galaxies.");
        System.out.println("Empty rows: " + emptyRows);
        System.out.println("Empty columns: " + emptyCols);
        long sumOfLengthsP1 = 0;
        long sumOfLengthsP2 = 0;
        for (int left = 0; left < galaxies.size(); left++)
            for (int right = left + 1; right < galaxies.size(); right++) {
                int leftG = galaxies.get(left);
                int rightG = galaxies.get(right);
                sumOfLengthsP1 += getDist(leftG / 1000, rightG / 1000, emptyRows, 2) + getDist(leftG % 1000, rightG % 1000, emptyCols, 2);
                sumOfLengthsP2 += getDist(leftG / 1000, rightG / 1000, emptyRows, EXPANDBY) + getDist(leftG % 1000, rightG % 1000, emptyCols, EXPANDBY);
                //sumOfLengths += Math.abs((leftG / 1000 / EXPANDBY) - (rightG / 1000 / EXPANDBY)) + Math.abs((leftG % (1000 * EXPANDBY)) - (rightG % (1000 * EXPANDBY)));
            }
        System.out.println("Sum of Manhattan distance between galaxies in P1 is " + sumOfLengthsP1);
        System.out.println("Sum of Manhattan distance between galaxies in P2 is " + sumOfLengthsP2);

        // part 2: 60699122878890 -- too high
        //         553224968560 -- too high
        //         553224415344 ok

    }

    public static int getDist(int a, int b, List<Integer> spaces, int expansion) {
        // make a lower:
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        // count spaces between them:
        int count = 0;
        for (int i = a + 1; i < b; i++)
            if (spaces.contains(i))
                count++;
        // return result:
        return b - a + count * (expansion - 1);
    }
}
