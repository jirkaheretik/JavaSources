package cz.braza.advent.a2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day03FindMulInstructions {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/home/jirka/src/java0/aoc24_03.txt";
        Scanner vstup = new Scanner(new File(fileName));
        long mulSum = 0;
        StringBuilder sb = new StringBuilder();
        while (vstup.hasNextLine())
            sb.append(vstup.nextLine());
        int pos = 0;
        while ((pos = sb.indexOf("mul(", pos)) >= 0) {
            int end = sb.indexOf(")", pos);
            if (end - pos > 11 || end - pos < 7) {
                System.out.println("Bad mul string: " + sb.substring(pos, end + 1));
                pos += 4;
                continue;
            }

            String[] vals = sb.substring(pos + 4, end).split(",");
            try {
                int left = Integer.parseInt(vals[0]);
                int right = Integer.parseInt(vals[1]);
                if (left >= 1000 || right >= 1000)
                    System.out.println("Too high values");
                else
                    mulSum += left * right;
            } catch (Exception e) {
                System.out.println("Corrupted content" + sb.substring(pos, end + 1));
            }
            pos = end;
        }
        System.out.println("Part 1 result: " + mulSum);
        // now part 2:
        pos = 0;
        mulSum = 0;
        int dontPos;
        while ((dontPos = sb.indexOf("don't()")) >= 0) {
            int doPos = sb.indexOf("do()", dontPos);
            sb.delete(dontPos, doPos);
        }
        while ((pos = sb.indexOf("mul(", pos)) >= 0) {
            int end = sb.indexOf(")", pos);
            if (end - pos > 11 || end - pos < 7) {
                // System.out.println("Bad mul string: " + sb.substring(pos, end + 1));
                pos += 4;
                continue;
            }

            String[] vals = sb.substring(pos + 4, end).split(",");
            try {
                int left = Integer.parseInt(vals[0]);
                int right = Integer.parseInt(vals[1]);
                if (left < 1000 && right < 1000)
                    mulSum += left * right;
            } catch (Exception e) {
                // System.out.println("Corrupted content" + sb.substring(pos, end + 1));
            }
            pos = end;
        }
        System.out.println("Part 2 result: " + mulSum);
    }
}
