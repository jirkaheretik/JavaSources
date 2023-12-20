package cz.braza.advent;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AoC202313Symmetry {

    public static final boolean DBG = false;
    public static int FINDCOUNT = 0;

    public static int findAnotherSymmetry(List<String> pattern, int value) {
        int lineLength = pattern.get(0).length();
        for (int i = 0; i < pattern.size() * lineLength; i++) {
            ArrayList<String> copy = new ArrayList<>(pattern);
            int col = i % lineLength;
            int row = i / lineLength;
            String line = copy.get(row);
            try {
                String another = line.substring(0, col) +
                        (line.charAt(col) == '.' ? '#' : '.') +
                        (col < pattern.size() - 1 ? line.substring(col + 1) : "");
                if (DBG) {
                    System.out.println("Replaced " + line + " with " + another);
                }
                copy.add(row, another);
            } catch (Exception ex) {
                System.out.println("Sum tin wong. line: " + line + ", len: " + line.length() + ", i=" + i+ ", col=" + col+ ", pattern size: " + pattern.size());
                System.out.println("Pattern number: " + FINDCOUNT);
                print(pattern, -1, 0);
                throw new RuntimeException("BUBU");
            }
            int result = getSymmetryNumber(copy, 100);
            if (result > 0 && result != value) {
                System.out.println("Found a smudge at [" + col + "," + row + "] with result " + result + " instead of " + value);
                print(copy, -1, 0);
                FINDCOUNT++;
                return result;
            }
        }
        // if we get here, we have not found a single smudge
        System.out.println("Fail to find a smudge:\n" + pattern);
        return 0;
    }

    public static int getSymmetryNumber(List<String> pattern, int multiplier) {
        if (multiplier < 1) return 0; // to back out when we are looking for smudges
        outer:
        for (int linenum = 0; linenum < pattern.size() - 1; linenum++)
            if (pattern.get(linenum).equals(pattern.get(linenum + 1))) {
                for (int i = 0; i < Math.min(linenum, pattern.size() - linenum - 2); i++) {
                    if (!pattern.get(linenum - i - 1).equals(pattern.get(linenum + 2 + i)))
                        continue outer;
                }
                // now the symmetry should be it (linenum)
                print(pattern, linenum, multiplier);
                if (linenum + 1 < pattern.size())
                    return (linenum + 1) * multiplier;
            }
        // if we are here, we have not found symmetry
        return getSymmetryNumber(flip(pattern), multiplier / 100);
    }

    public static List<String> flip(List<String> pattern) {
        int POCET = pattern.get(0).length();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < POCET; i++) {
            String line = "";
            for (String s : pattern)
                line += s.charAt(i);
            result.add(line);
        }
        return result;
    }

    public static void print(List<String> pattern, int linenum, int multiplier) {
        if (!DBG) return;
        if (multiplier > 0) System.out.println("Found symmetry at line " + linenum + " and multiplier " + multiplier);
        int line = 0;
        for (String row : pattern) {
            System.out.println(row);
            if (line == linenum)
                System.out.println("----------------");
            line++;
        }
    }

    public static void main(String[] args) throws Exception {
        String filename = "/home/jirka/src/java0/aoc23_13.txt";
        List<String> lines = Files.readAllLines(Paths.get(filename));
        lines.add("");
        List<String> pattern = new ArrayList<>();
        int sum = 0;
        int sumP2 = 0;
        for (String line : lines) {
            if (line.isBlank()) {
                int value = getSymmetryNumber(pattern, 100);
                sum += value;
                int p2 = findAnotherSymmetry(pattern, value);
                sumP2 += p2;
                pattern.clear();
            } else
                pattern.add(line);
        }
        System.out.println("Part 1: Sum of all patterns: " + sum);
        System.out.println("Part 2: Sum of after-smudges pattern detection: " + sumP2);
    }
}
