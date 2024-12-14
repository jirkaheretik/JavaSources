package cz.braza.advent.a2024;

import java.io.File;
import java.util.Scanner;

public class Day13ClawMachines {
    public static void main(String[] args) throws Exception {
        String fileName = "/home/jirka/src/java0/aoc24_13.txt";
        Scanner vstup = new Scanner(new File(fileName));
        /* Example:
        System.out.println(getMinTokensNeeded(94, 34, 22, 67, 8400, 5400));
        System.out.println(getMinTokensNeeded(26, 66, 67, 21, 12748, 12176));
        System.out.println(getMinTokensNeeded(17, 86, 84, 37, 7870, 6450));
        System.out.println(getMinTokensNeeded(69, 23, 27, 71, 18541, 10279));
         */
        long totalTokens = 0;
        long totalTokensP2 = 0;
        while (vstup.hasNext()) {
            int[] aVals = getVals(vstup.nextLine());
            int[] bVals = getVals(vstup.nextLine());
            int[] priceVals = getVals(vstup.nextLine());
            vstup.nextLine(); // skip delimiter
            totalTokens += getMinTokensNeededEq(aVals[0], aVals[1], bVals[0], bVals[1], priceVals[0], priceVals[1]);
            totalTokensP2 += getMinTokensNeededEq(aVals[0], aVals[1], bVals[0], bVals[1], 10_000_000_000_000L + priceVals[0], 10_000_000_000_000L + priceVals[1]);
            System.out.printf("Tokens now P1: %d, P2: %d %n", totalTokens, totalTokensP2);
        }
        System.out.println("Part 1 tokens needed: " + totalTokens);
        System.out.println("Part 2 tokens needed: " + totalTokensP2);
    }

    public static long getMinTokensNeeded(int ax, int ay, int bx, int by, long pricex, long pricey) {
        long minTokens = Long.MAX_VALUE;
        for (long a = 0; a <= (pricex / ax); a++) {
            if ((pricex - a * ax) % bx != 0) continue; // this is not it
            long b = (pricex - a * ax) / bx;
            // sanity check:
            if (a * ax + b * bx != pricex)
                System.out.printf("Sum-tin-wong, a = [%d, %d] %dx, b = [%d,%d] %dx, price at [%d,%d]%n", ax, ay, a, bx, by, b, pricex, pricey);
            // check Y part:
            if (a * ay + b * by == pricey) {
                if (a <= 100 && b <= 100) {
                    if (3 * a + b < minTokens) {
                        minTokens = 3 * a + b;
                        // break in P2:
                        if (pricex > 1_000_000_000_000L) break;
                    }
                } else {
                    System.out.printf("Found a possible solution for P2? [%d,%d]%n", a, b);
                }
            }
        }

        return minTokens < Long.MAX_VALUE ? minTokens : 0;
    }

    public static long getMinTokensNeededEq(int ax, int ay, int bx, int by, long pricex, long pricey) {
        //System.out.printf("Run with %d,%d - %d,%d, price at %d,%d%n", ax, ay, bx, by, pricex, pricey);
        long cit = (pricey * ax - ay * pricex);
        long jme = (by * ax - bx * ay);
        //System.out.printf("Cit %d, jme %d, modulo %d%n", cit, jme, cit % jme);
        if (cit % jme != 0) return 0;
        long citA = pricex - (cit / jme) * bx;
        //System.out.printf("2it %d, jme %d, modulo %d%n", citA, ax, citA % ax);
        if (citA % ax != 0) return 0;
        return cit / jme + 3 * citA / ax;
    }

    public static int[] getVals(String line) {
        String[] vals = line.split(": ")[1].split(", ");
        int[] result = new int[2];
        result[0] = Integer.parseInt(vals[0].substring(2));
        result[1] = Integer.parseInt(vals[1].substring(2));
        return result;
    }
}
