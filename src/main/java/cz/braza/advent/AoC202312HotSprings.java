package cz.braza.advent;

import java.io.File;
import java.util.Scanner;

public class AoC202312HotSprings {
    public static void main(String[] args) throws Exception {
        /* *** Testing:
        System.out.println("1: ???.### 1,1,3: " + countVariants("???.###", "1,1,3"));
        System.out.println("4: .??..??...?##. 1,1,3: " + countVariants(".??..??...?##.", "1,1,3"));
        System.out.println("10: ?###???????? 3,2,1: " + countVariants("?###????????", "3,2,1"));
         */
        long sumOfCombinations = 0;
        long sumOfCombinationsP2 = 0;
        String fileName = "/home/jirka/src/java0/aoc23_12.txt";
        Scanner vstup = new Scanner(new File(fileName));
        while (vstup.hasNextLine()) {
            String line = "";
            try {
                line = vstup.nextLine();
                String[] data = line.split(" ");
                sumOfCombinations += countVariants(data[0], data[1]);
                String p2data = data[0];
                String p2eval = data[1];
                for (int i = 0; i < 4; i++) {
                    p2data += "?" + data[0];
                    p2eval += "," + data[1];
                }
                sumOfCombinationsP2 += countVariants(p2data, p2eval);
                System.out.print(".");
            } catch (ArrayIndexOutOfBoundsException aob) {
                System.out.println("ERR for line: " + line);
            }
        }
        System.out.println();
        System.out.println("Part 1: " + sumOfCombinations);
        System.out.println("Part 2: " + sumOfCombinationsP2);
        // correct result part 1: 7173
        // works well for example input (21 for part 1, 525152 for part 2)
        // part 2: ???
    }

    public static int countVariants(String pattern, String eval) {
        int pos = pattern.indexOf("?");
        if (pos < 0)
            return evaluateVariant(pattern, eval);
        else if (partialEvaluate(pattern, eval)) {
            String prefix = pattern.substring(0, pos);
            String postfix = pattern.substring(pos + 1);
            return countVariants(prefix + "." + postfix, eval) + countVariants(prefix + "#" + postfix, eval);
        } else
            // fails partial evaluation
        return 0;
    }

    public static boolean partialEvaluate(String pattern, String eval) {
        String result = "";
        boolean inHash = false;
        int countHash = 0;
        for (char c: pattern.toCharArray()) {
            if (c == '.') {
                if (inHash) {
                    inHash = false;
                    result += "," + countHash;
                    countHash = 0;
                }
            } else if (c == '#') {
                inHash = true;
                countHash++;
            } else {
                // question mark?
                break;
            }
        }
        boolean out = result.isEmpty() || eval.startsWith(result.substring(1));
        //if (!out)
        //    System.out.println("DBG eval fail for string " + pattern + " and digits " + eval + ", failing result " + result.substring(1));
        return out;
    }

    public static int evaluateVariant(String pattern, String eval) {
        if (pattern.contains("?")) {
            System.out.println("Tried to evaluate not fully developed string '" + pattern + "', returning 0.");
            return 0;
        }
        String result = "";
        boolean inHash = false;
        int countHash = 0;
        pattern += "."; // stopping character
        for (char c: pattern.toCharArray()) {
            if (c == '.') {
                if (inHash) {
                    inHash = false;
                    result += "," + countHash;
                    countHash = 0;
                }
            } else {
                inHash = true;
                countHash++;
            }
        }
        return (result.length() > 1 && eval.equals(result.substring(1))) ? 1 : 0;
    }
}
