package cz.braza.advent.a2024;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day25KeysAndLocks {
    public static final String KEYROW = ".....";
    public static final String LOCKROW = "#####";
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        String fileName = "/home/jirka/src/java0/aoc24_25.txt";
        Scanner vstup = new Scanner(new File(fileName));
        List<int[]> keys = new ArrayList<>();
        List<int[]> locks = new ArrayList<>();
        while (vstup.hasNextLine()) {
            String line = vstup.nextLine();
            //System.out.println("Start reading: " + line);
            if (line.equals(KEYROW)) keys.add(readKey(vstup));
            else locks.add(readLock(vstup));
            if (vstup.hasNextLine()) {
                line = vstup.nextLine(); // empty row
                if (!line.isEmpty())
                    System.out.println("Bad line " + line);
            }
        }
        System.out.printf("Read input, we have %d locks and %d keys.%n", locks.size(), keys.size());
        int canFitCount = 0;
        int perfectFitCount = 0;
        for (int[] key: keys)
            for (int[] lock: locks)
                if (!doOverlap(key, lock)) {
                    canFitCount++;
                    if (isPerfectFit(key, lock))
                        perfectFitCount++;
                }

        System.out.printf("Part 1 result: %d, with %d perfect fits!%n", canFitCount, perfectFitCount);
        long endTime = System.currentTimeMillis();
        System.out.printf("Total program run %d ms.%n", endTime - startTime);
    }

    public static int[] readKey(Scanner sc) {
        return readObject(sc, false);
    }

    public static int[] readLock(Scanner sc) {
        return readObject(sc, true);
    }

    public static int[] readObject(Scanner sc, boolean isLock) {
        int[] pins = new int[5];
        // read lines:
        for (int line = 0; line < 5; line++) {
            String txt = sc.nextLine();
            for (int pin = 0; pin < 5; pin++)
                if (txt.charAt(pin) == '#') pins[pin]++;
        }
        // sanity check:
        String endLine = sc.nextLine();
        if (!endLine.equals(isLock ? KEYROW : LOCKROW))
            System.out.println("Found incorrect line.");
        return pins;
    }

    public static boolean doOverlap(int[] key, int[] lock) {
        for (int i = 0; i < 5; i++)
            if (key[i] + lock[i] > 5) return true;
        return false;
    }

    public static boolean isPerfectFit(int[] key, int[] lock) {
        for (int i = 0; i < 5; i++)
            if (key[i] + lock[i] != 5) return false;
        return true;
    }
}
